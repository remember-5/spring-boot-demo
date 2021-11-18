package com.remember.redis.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@SpringBootTest
class GeoHashServiceTest {


    @Resource
    private GeoHashService geoHashService;

    @Test
    void saveGeoHash() {
        geoHashService.saveGeoHash();
    }


    @Test
    void getGeoHash() {
        geoHashService.getGeoHash();
    }


    @Test
    void geoDist() {
        geoHashService.geoDist();
    }


    @Test
    void getGeoRadius() {
        geoHashService.getGeoRadius();
    }
}