package Food_app.business;

import Food_app.domain.FoodApiMeal;
import Food_app.domain.FoodApiMealDetails;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
@AllArgsConstructor
public class FoodApiService {
    private static final String API_HOST = "the-mexican-food-db.p.rapidapi.com";
    private static final String API_KEY = "1161ae7580mshcdc822605bf1e10p1c7a93jsn3749bb484ee0";

    public List<FoodApiMeal> getFoodMealPage(int pageNumber) {
        List<FoodApiMeal> list = getFoodApiMealsList();
        int startIndex = (pageNumber) * 5;
        int endIndex = Math.min(startIndex + 5, Objects.requireNonNull(list).size());
        return new ArrayList<>(list.subList(startIndex, endIndex));
    }

    private List<FoodApiMeal> getFoodApiMealsList() {
        WebClient client = WebClient.builder()
                .baseUrl("https://the-mexican-food-db.p.rapidapi.com")
                .defaultHeader("X-RapidAPI-Host", API_HOST)
                .defaultHeader("X-RapidAPI-Key", API_KEY)
                .build();


        List<FoodApiMeal> response = client
                .get()
                .uri("/")
                .retrieve()
                .bodyToFlux(FoodApiMeal.class)
                .collectList()
                .block();
        return response;


    }

    public FoodApiMealDetails getFoodApiMealDetails(Integer id) {
        WebClient client = WebClient.builder()
                .baseUrl("https://the-mexican-food-db.p.rapidapi.com")
                .defaultHeader("X-RapidAPI-Host", API_HOST)
                .defaultHeader("X-RapidAPI-Key", API_KEY)
                .build();

        return client
                .get()
                .uri("/%s".formatted(id))
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(jsonNode -> {
                    FoodApiMealDetails mealDetails = new FoodApiMealDetails();
                    mealDetails.setId(jsonNode.get("id").asInt());
                    mealDetails.setTitle(jsonNode.get("title").asText());
                    mealDetails.setDifficulty(jsonNode.get("difficulty").asText());
                    mealDetails.setPortion(jsonNode.get("portion").asText());
                    mealDetails.setTime(jsonNode.get("time").asText());
                    mealDetails.setDescription(jsonNode.get("description").asText());
                    mealDetails.setIngredients(parseStringList(jsonNode.get("ingredients")));
                    mealDetails.setMethod(parseMethodMap(jsonNode.get("method")));
                    mealDetails.setImage(jsonNode.get("image").asText());
                    return mealDetails;
                })
                .block();


    }

    private List<String> parseStringList(JsonNode node) {
        List<String> list = new ArrayList<>();
        if (node.isArray()) {
            for (JsonNode element : node) {
                list.add(element.asText());
            }
        }
        return list;
    }

    private Map<Integer, String> parseMethodMap(JsonNode node) {
        Map<Integer, String> map = new LinkedHashMap<>();

        if (node.isArray()) {
            for (int i = 0; i < node.size(); i++) {
                JsonNode element = node.get(i);
                String step = element.get("Step " + (i + 1)).asText();
                map.put(i + 1, step);
            }
        }

        return map;
    }
}
