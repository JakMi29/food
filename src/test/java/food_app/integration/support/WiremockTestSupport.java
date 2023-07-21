package food_app.integration.support;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public interface WiremockTestSupport {


   default void stubForMexicanMonthMeals(WireMockServer wireMockServer) {
        wireMockServer.stubFor(
                get(urlPathMatching("https://the-mexican-food-db.p.rapidapi.com"))
                        .willReturn(aResponse()
                                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                .withBodyFile("wiremock/meals.json")));
    }
    default void stubForMexicanMonthRecipe(WireMockServer wireMockServer) {
        wireMockServer.stubFor(
                get(urlPathMatching("https://the-mexican-food-db.p.rapidapi.com/1"))
                        .willReturn(aResponse()
                                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                .withBodyFile("wiremock/meal_recipe.json")));
    }

}
