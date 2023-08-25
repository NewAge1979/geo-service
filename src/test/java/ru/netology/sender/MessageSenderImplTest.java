package ru.netology.sender;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageSenderImplTest {

    private final GeoService geoServiceMock = Mockito.mock(GeoService.class);
    private final LocalizationService localizationServiceMock = Mockito.mock(LocalizationService.class);
    private final MessageSenderImpl messageSender = new MessageSenderImpl(geoServiceMock, localizationServiceMock);

    @Test
    void russianIpTest() {
        String russianIp = "172.0.0.1";

        Mockito.when(geoServiceMock.byIp(russianIp)).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        Mockito.when(localizationServiceMock.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        HashMap<String, String> russianMap = new HashMap<>();
        russianMap.put(MessageSenderImpl.IP_ADDRESS_HEADER, russianIp);

        String result = messageSender.send(russianMap);
        String etalon = "Добро пожаловать";

        assertEquals(etalon, result);
    }

    @Test
    void englishIpTest() {
        String englishIp = "96.0.0.1";

        Mockito.when(geoServiceMock.byIp(englishIp)).thenReturn(new Location("Washington", Country.USA, null, 0));
        Mockito.when(localizationServiceMock.locale(Country.USA)).thenReturn("Welcome");

        HashMap<String, String> englishMap = new HashMap<>();
        englishMap.put(MessageSenderImpl.IP_ADDRESS_HEADER, englishIp);

        String result = messageSender.send(englishMap);
        String etalon = "Welcome";

        assertEquals(etalon, result);
    }
}