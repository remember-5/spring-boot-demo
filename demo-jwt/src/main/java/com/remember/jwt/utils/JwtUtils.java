package com.remember.jwt.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.Base64Utils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangjiahao
 * @date 2020/5/27
 */
public class JwtUtils {

    public static final String JWT_SECERT = "7786df7fc3a34e26a61c034d5ec5236d";

    public static void main(String[] args) {
        String jwt = createJWT("123", "13361928119", 1000L * 60 * 60);
        System.err.println(jwt);
//        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMzM2MTkyODExOSIsImFjY291bnROdW0iOiIxMzM2MTkyODExOSIsImV4cCI6MTU5MDY0MDM5MywiaWF0IjoxNTkwNjQwMzMzLCJqdGkiOiIxMjMifQ.gfErYy8qXShdwz71YhPgL5IIQ7aRB1KlDe7g_f-iNus";
        Claims claims = parseJWT(jwt);
        System.out.println(claims.getId());//jwt
        System.out.println(claims.getIssuedAt());//Mon Feb 05 20:50:49 CST 2018
        System.out.println(claims.getSubject());//{id:100,name:xiaohong}
        System.out.println(claims.getIssuer());//null
        System.out.println(claims.get("uid", String.class));//DSSFAWDWADAS...
    }

    /**
     * 签发JWT
     *
     * @param id        全局唯一的id
     * @param subject   可以是JSON数据 尽可能少
     * @param ttlMillis 失效时间
     * @return String
     */
    public static String createJWT(String id, String subject, long ttlMillis) {
        //指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        //创建payload的私有声明（根据特定的业务需要添加，如果要拿这个做验证，一般是需要和jwt的接收方提前沟通好验证方式的）
        Map<String, Object> claims = new HashMap<>(10);
        claims.put("accountNum", "13361928119");
        //生成签名的时候使用的秘钥secret,这个方法本地封装了的，一般可以从本地配置文件中读取，切记这个秘钥不能外露哦。它就是你服务端的私钥，在任何场景都不应该流露出去。一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
        SecretKey secretKey = generalKey();
        JwtBuilder builder = Jwts.builder()
                // 如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setClaims(claims)
                // 设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。这里其实就是new一个JwtBuilder，设置jwt的body
                .setId(id)
                // sub(Subject)：代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
                .setSubject(subject)
                // 签发者
//                .setIssuer("user")
                // 签发时间
                .setIssuedAt(now)
                // 签名算法以及密匙
                .signWith(signatureAlgorithm, secretKey);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date expDate = new Date(expMillis);
            // 过期时间
            builder.setExpiration(expDate);
        }
        return builder.compact();
    }


//    public static SecretKey generalKey() {
//        byte[] encodedKey = Base64Utils.decode(JWT_SECERT.getBytes(StandardCharsets.UTF_8));
//        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
//    }

    /**
     * 解密jwt
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) {
        SecretKey key = generalKey();  // 签名秘钥，和生成的签名的秘钥一模一样
        Claims claims = Jwts.parser()  // 得到DefaultJwtParser
                .setSigningKey(key)         // 设置签名的秘钥
                .parseClaimsJws(jwt).getBody(); // 设置需要解析的jwt
        return claims;
    }

    /**
     * 由字符串生成加密key
     *
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64Utils.decode(JWT_SECERT.getBytes(StandardCharsets.UTF_8));
        System.out.println(Base64Utils.encodeToUrlSafeString(encodedKey));
        // 根据给定的字节数组使用AES加密算法构造一个密钥，使用 encodedKey中的始于且包含 0 到前 leng 个字节这是当然是所有。（后面的文章中马上回推出讲解Java加密和解密的一些算法）
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }
}
