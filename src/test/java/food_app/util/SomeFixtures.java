package food_app.util;

import Food_app.api.dto.*;
import Food_app.domain.*;
import Food_app.infrastructure.database.entity.*;
import Food_app.infrastructure.security.RoleEntity;
import Food_app.infrastructure.security.User;
import Food_app.infrastructure.security.UserEntity;
import lombok.experimental.UtilityClass;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.Set;

@UtilityClass
public class SomeFixtures {
    static public AddressEntity someAddressEntity() {
        return AddressEntity.builder()
                .addressId(1)
                .country("Poland")
                .city("Wroclaw")
                .postalCode("52-132")
                .street(StreetEntity.builder()
                        .streetId(1)
                        .name("Witosa")
                        .build())
                .houseNumber("4f/12")
                .build();
    }

    static public Address someAddress() {
        return Address.builder()
                .addressId(2)
                .country("Poland")
                .city("Wroclaw")
                .postalCode("52-132")
                .street(Street.builder()
                        .streetId(1)
                        .name("Witosa")
                        .build())
                .houseNumber("4f/12")
                .build();
    }

    static public OrderDetailsDTO someOrderDetailsDto() {
        return OrderDetailsDTO.builder()
                .price("12")
                .complete(false)
                .orderNumber("12345")
                .customer("customerName")
                .restaurant("restaurantName")
                .receivedDateTime("12.12.30 19:00")
                .completedDateTime("12.12.30 19:10")
                .meals(someOrderDTOList())
                .build();
    }

    static public Customer someCustomer() {
        return Customer.builder()
                .userName("userName")
                .name("name")
                .email("email")
                .customerId(1)
                .address(someAddress())
                .orders(
                        Set.of(someOrder(), someOrder2())
                )
                .phone("+48 654 645 456")
                .build();
    }

    static public CustomerEntity someCustomerEntity() {
        return CustomerEntity.builder()
                .userName("userName")
                .name("name")
                .email("email")
                .customerId(1)
                .address(someAddressEntity())
                .orders(someOrderEntities())
                .phone("+48 654 645 456")
                .build();
    }

    static public CustomerEntity someCustomerEntityWithoutOrders() {
        return CustomerEntity.builder()
                .userName("userName")
                .name("name")
                .email("email")
                .customerId(1)
                .address(someAddressEntity())
                .phone("+48 654 645 456")
                .build();
    }

    static public CustomerDTO someCustomerDTO() {
        return CustomerDTO.builder()
                .name("John")
                .userName("Johny")
                .build();
    }

    static public List<OrderMealDTO> someOrderDTOList() {
        return List.of(
                OrderMealDTO.builder()
                        .price("12")
                        .quantity(1)
                        .name("name")
                        .image("images/63.png")
                        .build()
        );
    }

    static public MealDTO someMealDTO() {
        return MealDTO.builder()
                .name("name")
                .price("123")
                .image("")
                .description("description")
                .category("category")
                .build();
    }

    static public CreateMealDTO someCreateMealDTO() throws IOException {
        return CreateMealDTO.builder()
                .name("name")
                .price("123")
                .image(createTestMultipartFile())
                .description("description")
                .category("category")
                .build();
    }

    private MultipartFile createTestMultipartFile() throws IOException {
        Path imagePath = Paths.get("src/test/resources/63.png");
        byte[] imageBytes = Files.readAllBytes(imagePath);
        return new MockMultipartFile("test-image.jpg", "test-image.jpg", "image/jpeg", imageBytes);
    }

    static public Restaurant someRestaurant() {
        return Restaurant.builder()
                .restaurantId(1)
                .foodCategory("All")
                .restaurantOwner(someRestaurantOwner())
                .name("Flavors of est")
                .email("esta@gmail.com")
                .address(someAddress())
                .phone("+48 987 789 789")
                .meals(someMeals())
                .orders(someOrders())
                .description("In our restaurant you can eat delicious and cheap. Not only traditional Polish dishes but also delicious Italian pizza.")
                .build();

    }

    static public RestaurantEntity someRestaurantEntity() {
        return RestaurantEntity.builder()
                .restaurantId(1)
                .foodCategory("All")
                .address(someAddressEntity())
                .restaurantOwner(someRestaurantOwnerEntity())
                .name("Flavors of est")
                .email("esta@gmail.com")
                .meals(someMealEntities())
                .phone("+48 987 789 789")
                .description("In our restaurant you can eat delicious and cheap. Not only traditional Polish dishes but also delicious Italian pizza.")
                .build();
    }

    static public Restaurant someExistingRestaurant() {
        return Restaurant.builder()
                .restaurantId(1)
                .foodCategory("All")
                .address(someAddress())
                .restaurantOwner(someRestaurantOwner())
                .name("Flavors of est")
                .email("esta@gmail.com")
                .phone("+48 987 789 789")
                .description("In our restaurant you can eat delicious and cheap. Not only traditional Polish dishes but also delicious Italian pizza.")
                .build();
    }

    static public RestaurantDTO someRestaurantDTO() {
        return RestaurantDTO.builder()
                .name("Flavors of est")
                .phone("+48 987 789 789")
                .foodCategory("All")
                .orders(someOrdersDTO())
                .meals(someMealsDTO())
                .build();
    }

    private static List<RestaurantOrderDTO> someOrdersDTO() {
        return List.of(
                RestaurantOrderDTO.builder()
                        .complete(false)
                        .orderNumber("orderNumber")
                        .customer(someCustomer())
                        .price("12")
                        .build()
        );
    }

    private static List<MealDTO> someMealsDTO() {
        return List.of(MealDTO.builder()
                        .name("name")
                        .category("category")
                .build());
    }

    static public Meal someMeal() {
        return Meal.builder()
                .mealId(1)
                .image("images/63.png")
                .restaurant(someRestaurant())
                .description("traditional Polish bigos")
                .category("appetizer")
                .name("pierogi")
                .price(BigDecimal.valueOf(16))
                .build();
    }

    static public Meal someMeal2() {
        return Meal.builder()
                .mealId(2)
                .image("images/63.png")
                .description("fresh lemonade")
                .category("drinks")
                .name("lemonade")
                .price(BigDecimal.valueOf(16))
                .build();
    }

    static public Set<Meal> someMeals() {
        return Set.of(Meal.builder()
                .mealId(1)
                .name("bigos")
                .image("images/image.png")
                .description("description")
                .category("category")
                .price(BigDecimal.ONE)
                .build());
    }
    static public Set<MealEntity> someMealEntities() {
        return Set.of(MealEntity.builder()
                .mealId(1)
                .name("bigos")
                .image("images/image.png")
                .description("description")
                .category("category")
                .price(BigDecimal.ONE)
                .build());
    }


    static public MealEntity someMealEntity() {
        return MealEntity.builder()
                .mealId(1)
                .image("images/63.png")
                .description("traditional Polish bigos")
                .category("appetizer")
                .name("pierogi")
                .price(BigDecimal.valueOf(16))
                .build();
    }

    static public RestaurantOwner someRestaurantOwner() {
        return RestaurantOwner.builder()
                .phone("phone")
                .email("email")
                .user(someUser())
                .restaurantOwnerId(1)
                .userName("userName")
                .name("James")
                .surname("Davis")
                .build();
    }

    static public RestaurantOwnerEntity someRestaurantOwnerEntity() {
        return RestaurantOwnerEntity.builder()
                .phone("phone")
                .email("email")
                .user(someUserEntity())
                .restaurantOwnerId(1)
                .userName("userName")
                .name("James")
                .surname("Davis")
                .build();
    }

    static public User someUser() {
        return User.builder()
                .id(1)
                .userName("ownerJames")
                .active(true)
                .email("james@gmail.com")
                .password("test")
                .active(true)
                .role(someRoleEntity())
                .build();
    }

    static public UserEntity someUserEntity() {
        return UserEntity.builder()
                .id(1)
                .userName("ownerJames")
                .active(true)
                .email("james@gmail.com")
                .active(true)
                .password("test")
                .role(someRoleEntity())
                .build();
    }

    static public RoleEntity someRoleEntity() {
        return RoleEntity.builder()
                .role("CUSTOMER")
                .id(1)
                .build();
    }


    public static Order someOrder() {
        return Order.builder()
                .orderId(1)
                .price(new BigDecimal("12"))
                .orderNumber("12345")
                .receivedDateTime(OffsetDateTime.of(
                        12, 12, 12, 12, 12, 12, 12, ZoneOffset.UTC))
                .complete(true)
                .completedDateTime(OffsetDateTime.of(
                        12, 12, 12, 12, 16, 12, 12, ZoneOffset.UTC))
                .restaurant(orderRestaurant())
                .customer(orderCustomer())
                .orderMeals(someOrderMeals())
                .build();
    }

    public static OrderEntity someOrderEntity() {
        return OrderEntity.builder()
                .orderId(1)
                .price(new BigDecimal("12"))
                .orderNumber("12345")
                .receivedDateTime(OffsetDateTime.of(
                        12, 12, 12, 12, 12, 12, 12, ZoneOffset.UTC))
                .complete(true)
                .completedDateTime(OffsetDateTime.of(
                        12, 12, 12, 12, 16, 12, 12, ZoneOffset.UTC))
                .restaurant(someRestaurantEntity())
                .customer(someCustomerEntityWithoutOrders())
                .orderMeals(someOrderMealEntities())
                .build();
    }

    public static Order someOrder2() {
        return Order.builder()
                .orderId(2)
                .price(new BigDecimal("15"))
                .orderNumber("1234")
                .receivedDateTime(OffsetDateTime.of(
                        12, 11, 12, 12, 12, 12, 12, ZoneOffset.UTC))
                .complete(false)
                .completedDateTime(OffsetDateTime.of(
                        12, 11, 12, 12, 16, 12, 12, ZoneOffset.UTC))
                .restaurant(orderRestaurant())
                .customer(orderCustomer())
                .orderMeals(someOrderMeals())
                .build();
    }

    public static OrderMeal someOrderMeal() {
        return OrderMeal.builder()
                .order(Order.builder()
                        .orderNumber("12345")
                        .build())
                .meal(
                        Meal.builder()
                                .name("name")
                                .mealId(1)
                                .category("category")
                                .description("description")
                                .price(new BigDecimal("12"))
                                .image("image")
                                .build())
                .orderMealId(1)
                .quantity(1)
                .price(new BigDecimal("12"))
                .build();
    }

    public static OrderMealEntity someOrderMealEntity() {
        return OrderMealEntity.builder()
                .order(OrderEntity.builder()
                        .orderNumber("12345")
                        .restaurant(someRestaurantEntity())
                        .customer(someCustomerEntity())
                        .build())
                .meal(
                        MealEntity.builder()
                                .name("name")
                                .mealId(1)
                                .category("category")
                                .description("description")
                                .price(new BigDecimal("12"))
                                .image("image")
                                .build())
                .orderMealId(1)
                .quantity(1)
                .price(new BigDecimal("12"))
                .build();
    }

    public static Set<OrderMealEntity> someOrderMealEntities() {
        return Set.of(
                OrderMealEntity.builder()
                        .order(OrderEntity.builder()
                                .orderNumber("12345")
                                .build())
                        .meal(
                                MealEntity.builder()
                                        .name("name")
                                        .mealId(1)
                                        .category("category")
                                        .description("description")
                                        .price(new BigDecimal("12"))
                                        .image("image")
                                        .build())
                        .orderMealId(1)
                        .quantity(1)
                        .price(new BigDecimal("12"))
                        .build()
        );
    }

    public static Set<OrderMeal> someOrderMeals() {
        return Set.of(
                OrderMeal.builder()
                        .order(Order.builder()
                                .orderNumber("12345")
                                .build())
                        .meal(
                                Meal.builder()
                                        .name("name")
                                        .mealId(1)
                                        .category("category")
                                        .description("description")
                                        .price(new BigDecimal("12"))
                                        .image("image")
                                        .build())
                        .orderMealId(1)
                        .quantity(1)
                        .price(new BigDecimal("12"))
                        .build()
        );
    }

    public static Restaurant orderRestaurant() {
        return Restaurant.builder()
                .name("name")
                .foodCategory("category")
                .restaurantId(1)
                .build();
    }

    public static Customer orderCustomer() {
        return Customer.builder()
                .name("name")
                .customerId(1)
                .surname("surname")
                .phone("+48 678 567 456")
                .email("email")
                .build();
    }

    public static Set<Order> someOrders() {
        return Set.of(someOrder());
    }

    public static Set<OrderEntity> someOrderEntities() {
        return Set.of(someOrderEntity());
    }

    public static CreateCustomerDTO someCreateCustomerDTO() {
        return CreateCustomerDTO.builder()
                .name("name")
                .surname("surname")
                .city("city")
                .phone("+48 765 876 345")
                .email("email@gmail.com")
                .userName("userName")
                .homeNumber("houseNumber")
                .postalCode("postalCode")
                .street("street")
                .country("country")
                .city("city")
                .password("password")
                .build();
    }

    public static Street someStreet() {
        return Street.builder()
                .name("name")
                .streetId(1)
                .build();
    }

    public static RestaurantStreet someRestaurantStreet() {
        return RestaurantStreet.builder()
                .restaurantStreetId(1)
                .street(someStreet())
                .restaurant(someRestaurant())
                .build();
    }

    public static StreetDTO someStreetDto() {
        return StreetDTO.builder()
                .name("name")
                .build();
    }

    public static OrderMealDTO someOrderMealDTO() {
        return OrderMealDTO.builder()
                .image("images/63.png")
                .name("name")
                .quantity(1)
                .price("12")
                .build();
    }

    public static FoodApiMealDetails someFoodApiDetails() {
        return FoodApiMealDetails.builder()
                .image("image")
                .time("title")
                .difficulty("difficulty")
                .portion("portion")
                .time("time")
                .description("description")
                .ingredients(List.of("string"))
                .method(Map.of(1, "string"))
                .build();
    }

    public static CreateRestaurantOwnerDTO someCreateRestaurantOwner() {
        return CreateRestaurantOwnerDTO.builder()
                .name("name")
                .surname("surname")
                .userName("userName")
                .phone("+48 654 654 645")
                .city("city")
                .country("country")
                .postalCode("postalCode")
                .email("email")
                .street("street")
                .homeNumber("homeNumber")
                .password("password")
                .restaurantName("restaurantName")
                .restaurantEmail("restaurantEmail")
                .restaurantPhone("+48 765 654 435")
                .build();
    }


    public static RestaurantStreetEntity someRestaurantStreetEntity() {
        return RestaurantStreetEntity.builder()
                .street(someStreetEntity())
                .restaurant(someRestaurantEntity())
                .restaurantStreetId(1)
                .build();
    }

    public static StreetEntity someStreetEntity() {
        return StreetEntity.builder()
                .name("street")
                .streetId(1)
                .build();
    }

    public static RestaurantMenuDTO someRestaurantMenu() {
        return RestaurantMenuDTO.builder()
                .address("address")
                .phone("phone")
                .name("name")
                .description("description")
                .foodCategory("foodCategory")
                .meals(someMealsDTO())
                .build();
    }
}
