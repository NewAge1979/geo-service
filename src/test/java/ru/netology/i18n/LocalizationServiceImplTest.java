package ru.netology.i18n;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocalizationServiceImplTest {

    @ParameterizedTest
    @CsvSource({"RUSSIA,Добро пожаловать", "USA,Welcome"})
    void localeTest(String country, String message) {
        LocalizationService localizationService = new LocalizationServiceImpl();
        String result = localizationService.locale(Country.valueOf(country));
        assertEquals(message, result);
    }
}