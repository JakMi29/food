package Food_app.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "name")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "restaurant")
@NamedEntityGraph(name = "Restaurant.meals", attributeNodes = @NamedAttributeNode("meals"))
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Integer restaurantId;

    @Column(name = "name")
    private String name;

    @Column(name = "food_category")
    private String foodCategory;

    @Column(name = "phone")
    private String phone;

    @Column(name = "description")
    private String description;

    @Column(name = "email")
    private String email;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_owner_id")
    private RestaurantOwnerEntity restaurantOwner;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private Set<MealEntity> meals;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private Set<OrderEntity> orders;


}
