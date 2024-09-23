package netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;

import java.util.Map;

public class MessageSenderImplTests {
@Mock
GeoServiceImpl geoService;
@Mock
LocalizationServiceImpl localizationService;

    @Test
    public void sendTestWithMockitoRussiaIp() {
        String ip = "172.0.32.11";
        GeoService geoServiceImpl = Mockito.mock(GeoServiceImpl.class);
Mockito.when(geoServiceImpl.byIp(ip)).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
LocalizationService localizationServiceImpl = Mockito.mock(LocalizationServiceImpl.class);
Mockito.when(localizationServiceImpl.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");


        Map<String, String> header = Map.of(
                MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        MessageSenderImpl messageSender = new MessageSenderImpl(geoServiceImpl, localizationServiceImpl);


        String result = messageSender.send(header);

        Assertions.assertEquals("Добро пожаловать", result);

    }

    @Test
    public void sendTestWithMockitoUSAIp() {
        String ip = "96.44.183.149";
        GeoServiceImpl geoServiceImpl = Mockito.spy(new GeoServiceImpl());
        LocalizationServiceImpl localizationServiceImpl = Mockito.spy(new LocalizationServiceImpl());

        Map<String, String> header = Map.of(
                MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        MessageSenderImpl messageSender = new MessageSenderImpl(geoServiceImpl, localizationServiceImpl);


        String result = messageSender.send(header);

        Assertions.assertEquals("Welcome", result);

    }

}

