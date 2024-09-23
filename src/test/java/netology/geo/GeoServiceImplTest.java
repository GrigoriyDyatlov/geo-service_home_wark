package netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

import java.util.stream.Stream;

public class GeoServiceImplTest {

    public static Stream<Arguments> byIpTests() {
        return Stream.of(
                Arguments.of("172.199", "Moscow", Country.RUSSIA),
                Arguments.of("172.188", "Moscow", Country.RUSSIA),
                Arguments.of("96.199", "New York", Country.USA),
                Arguments.of("96.169", "New York", Country.USA));
    }

    @MethodSource
    @ParameterizedTest
    public void byIpTests(String ip, String city, Country country) {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location expect = new Location(city, country, null, 0);

        Location result = geoService.byIp(ip);

        Assertions.assertEquals(expect.getCountry(), result.getCountry());


    }
}
