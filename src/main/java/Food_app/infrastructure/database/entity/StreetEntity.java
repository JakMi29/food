package Food_app.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="name")
@Entity
@Table(name = "street")
public class StreetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "street_id")
    private Integer streetId;

    @Column(name = "name")
    private String name;
}
