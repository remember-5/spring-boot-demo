## 实现方式
### springboot提供的redis锁
see https://blog.csdn.net/qq_18244417/article/details/115987665

`pom.xml` add 
```xml
<dependencyss>
    <!--   分布式锁 start    -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-integration</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.integration</groupId>
        <artifactId>spring-integration-redis</artifactId>
    </dependency>
    <!--   分布式锁  end   -->
</dependencyss>
```

使用

```java
import org.springframework.integration.redis.util.RedisLockRegistry;

import javax.annotation.Resource;

public class MyTest {
    @Resource
    RedisLockRegistry redisLockRegistry;
    // 锁测试
    public void test(String lockKey) {
        // 获取锁
        Lock lock = redisLockRegistry.obtain(lockKey);
        // 加锁
        lock.lock();
        try {
            // 此处是你的代码逻辑，处理需要加锁的一些事务
        } catch (Exception e) {
        } finally {
            // 配合解锁逻辑
            lock.unlock();
        }
    }
}
```


### redisson
redisson实现redis的连接和锁的使用,官方仓库 https://github.com/redisson/redisson

springboot starter https://github.com/redisson/redisson/tree/master/redisson-spring-boot-starter
`pom.xml` add 这里要注意一下版本的问题，具体请查看官方文档说明
```xml
<dependencys>
    <!-- redisson 分布式锁-->
    <dependency>
        <groupId>org.redisson</groupId>
        <artifactId>redisson-spring-boot-starter</artifactId>
        <version>3.16.8</version>
    </dependency>
</dependencys>
```

```java
import org.redisson.api.RedissonClient;

import javax.annotation.Resource;

public class MyTest {
    @Resource
    RedissonClient redissonClient;

    // 锁测试
    public void test(String lockKey) {
        // 获取锁
        Lock lock = redissonClient.getLock("mylock");
        // 加锁
        lock.lock();
        try {
            // 此处是你的代码逻辑，处理需要加锁的一些事务
        } catch (Exception e) {
        } finally {
            // 配合解锁逻辑
            lock.unlock();
        }
    }
}
```
