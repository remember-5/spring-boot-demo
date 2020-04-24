package com.remember.encrypt.starter.config;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>响应体数据处理，防止数据类型为String时再进行JSON数据转换，那么产生最终的结果可能被双引号包含...</p>
 * @author wangjiahao
 * @version 2018/9/5
 */
//@Slf4j
//@Configuration
public class HttpConverterConfig implements WebMvcConfigurer {

//    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(){
//        return new MappingJackson2HttpMessageConverter(){
//            @Override
//            protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
//                if(object instanceof String){
//                    Charset charset = this.getDefaultCharset();
//                    StreamUtils.copy((String)object, charset, outputMessage.getBody());
//                }else{
//                    super.writeInternal(object, type, outputMessage);
//                }
//            }
//        };
//    }

//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        MappingJackson2HttpMessageConverter converter = mappingJackson2HttpMessageConverter();
//        converter.setSupportedMediaTypes(new LinkedList<MediaType>(){{
//            add(MediaType.TEXT_HTML);
//            add(MediaType.APPLICATION_JSON);
//        }});
//        converters.add(new StringHttpMessageConverter());
//        converters.add(converter);
//    }

    /**
     * 允许跨域配置
     */
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        log.info("跨域已设置");
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedMethods("*")
//                .allowedHeaders("*")
//                .allowCredentials(true)
//                .maxAge(3600);
//    }
}
