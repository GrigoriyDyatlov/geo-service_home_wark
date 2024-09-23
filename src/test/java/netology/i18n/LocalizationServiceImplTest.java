package netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

public class LocalizationServiceImplTest {
    public LocalizationServiceImpl localizationService = new LocalizationServiceImpl();

    @Test
    public void localeTestRUS() {
        String expected = "Добро пожаловать";

        String result = localizationService.locale(Country.RUSSIA);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void localeTestUSA() {
        String expected = "Welcome";

        String result = localizationService.locale(Country.USA);

        Assertions.assertEquals(expected, result);
    }
}
