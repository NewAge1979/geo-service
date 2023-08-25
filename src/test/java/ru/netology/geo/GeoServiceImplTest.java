package ru.netology.geo;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GeoServiceImplTest {

    @ParameterizedTest
    @CsvSource({"172.0.0.1,RUSSIA", "96.0.0.1,USA"})
    void byIpTest(String ip, String county) {
        GeoService geoService = new GeoServiceImpl();
        String result = geoService.byIp(ip).getCountry().toString();
        assertEquals(county, result);
    }
}