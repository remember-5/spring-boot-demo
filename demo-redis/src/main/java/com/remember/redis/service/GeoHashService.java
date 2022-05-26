package com.remember.redis.service;

import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * redis geo hash template
 *
 * @author wangjiahao
 * @date 2021/9/24
 */
@Component
public class GeoHashService {

    private final RedisTemplate<String, Object> redisTemplate;

    public GeoHashService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public final String KEY = "home:geo";

    /**
     * save geo hash data
     * GEOADD key longitude latitude member [longitude latitude member …]
     *
     * @see "http://redisdoc.com/geo/geoadd.html"
     */
    public void saveGeoHash() {
        String[][] array = {
                {"121.686009", "31.200382", "金宇"},
                {"121.271036", "31.339226", "永胜公寓"},
                {"121.437856", "31.222863", "江苏路500"},
                {"121.947222", "30.903604", "滴水湖"}
        };

        for (String[] strings : array) {
            double x = Double.parseDouble(strings[0]);
            double y = Double.parseDouble(strings[1]);
            Point point = new Point(x, y);
            Long aLong = redisTemplate.opsForGeo().add(KEY, point, strings[2]);
        }
    }

    /**
     * 从键里面返回所有给定位置元素的位置（经度和纬度）。
     * GEOPOS key member [member …]
     *
     * @see "http://redisdoc.com/geo/geopos.html"
     */
    public void getGeoHash() {
        List<Point> data = redisTemplate.opsForGeo().position(KEY, "永胜公寓", "滴水湖");
        for (Point datum : data) {
            System.err.println(datum);
        }
    }


    /**
     * 返回两个给定位置之间的距离。
     * GEODIST key member1 member2 [unit]
     *
     * @see "http://redisdoc.com/geo/geodist.html"
     */
    public void geoDist() {
        Distance distance = redisTemplate.opsForGeo().distance(KEY, "永胜公寓", "滴水湖", Metrics.KILOMETERS);
        System.err.println(distance);
        Distance distance1 = redisTemplate.opsForGeo().distance(KEY, "永胜公寓", "江苏路500", Metrics.KILOMETERS);
        System.err.println(distance1);
    }


    /**
     * 以给定的经纬度为中心， 返回键包含的位置元素当中， 与中心的距离不超过给定最大距离的所有位置元素。
     * GEORADIUS key longitude latitude radius m|km|ft|mi [WITHCOORD] [WITHDIST] [WITHHASH] [ASC|DESC] [COUNT count]
     * <p>
     * includeCoordinates：返回结果包含坐标信息
     * includeDistance：返回结果包含具中心坐标距离信息
     * sortAscending：按照距离升序排序
     * sortDescending：按照距离降序排序
     * limit：返回结果数量限制
     * </p>
     *
     * @see "http://redisdoc.com/geo/georadius.html"
     */
    public void getGeoRadius() {
        // 这个是自定义一个的点
        double x = 121.497064;
        double y = 31.242597;

        Circle circle = new Circle(new Point(x, y), new Distance(30, RedisGeoCommands.DistanceUnit.KILOMETERS));
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().includeCoordinates().sortAscending().limit(30);
        GeoResults<RedisGeoCommands.GeoLocation<Object>> radius = redisTemplate.opsForGeo().radius(KEY, circle, args);
        radius.forEach(r -> {
            r.getContent().getName();
            r.getContent().getPoint().getX();
            r.getContent().getPoint().getY();
            r.getDistance().getValue();
            r.getDistance().getMetric();
        });

        System.err.println(radius);


        // 这个是在zset内取
        RedisGeoCommands.GeoRadiusCommandArgs args1 = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().includeCoordinates().sortAscending().limit(10);
        GeoResults<RedisGeoCommands.GeoLocation<Object>> radius1 = redisTemplate.opsForGeo().radius(KEY, "江苏路500", new Distance(30, Metrics.KILOMETERS), args1);
        System.err.println(radius1);
    }


}
