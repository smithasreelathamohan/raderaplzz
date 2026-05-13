package systementor.cidemo1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CountryServiceTest {

        @Mock
        CountryApiClient countryApiClient;

        @InjectMocks
        CountryService countryService;

        private static final Country LATVIA = new Country(
                        "Latvia",
                        "Republic of Latvia",
                        "Latvia",
                        "Latvian Republic",
                        "Riga",
                        "Europe",
                        "Northern Europe",
                        Map.of(
                                        "lav", "Latvian",
                                        "eng", "English"),
                        List.of("EST", "LTU", "RUS", "BLR"),
                        1829000,
                        64559.0,
                        "https://goo.gl/maps/iQpUkH7ghq31ZtXe9");

        @Test
        void getSortedLanguagesReturnsAlphabeticallySortedLanguages() {
                when(countryApiClient.fetchCountryByName("Latvia"))
                                .thenReturn(LATVIA);

                var result = countryService.getSortedLanguages("Latvia");

                assertEquals(List.of("English", "Latvian"), result);
        }

        @Test
        void getSortedBordersReturnsAlphabeticallySortedBorders() {
                when(countryApiClient.fetchCountryByName("Latvia"))
                                .thenReturn(LATVIA);

                var result = countryService.getSortedBorders("Latvia");

                assertEquals(List.of("BLR", "EST", "LTU", "RUS"), result);
        }

        @Test
        void getRegionInfoReturnsRegionAndSubregion() {
                when(countryApiClient.fetchCountryByName("Latvia"))
                                .thenReturn(LATVIA);

                var result = countryService.getRegionInfo("Latvia");

                assertEquals("Europe / Northern Europe", result);
        }

        @Test
        void isHighlyPopulatedReturnsFalseForPopulationLessThan10Million() {
                when(countryApiClient.fetchCountryByName("Latvia"))
                                .thenReturn(LATVIA);

                var result = countryService.isHighlyPopulated("Latvia");

                assertFalse(result);
        }

        @Test
        void getPopulationDensityReturnsCorrectDensity() {
                when(countryApiClient.fetchCountryByName("Latvia"))
                                .thenReturn(LATVIA);

                var result = countryService.getPopulationDensity("Latvia");

                assertEquals(28.33, result, 0.01);
        }

        @Test
        void getGoogleMapsUrlReturnsCorrectUrl() {
                when(countryApiClient.fetchCountryByName("Latvia"))
                                .thenReturn(LATVIA);

                var result = countryService.getGoogleMapsUrl("Latvia");

                assertEquals("https://goo.gl/maps/iQpUkH7ghq31ZtXe9", result);
        }
}
