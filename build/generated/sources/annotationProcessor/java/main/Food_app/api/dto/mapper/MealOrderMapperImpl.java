package Food_app.api.dto.mapper;

import Food_app.api.dto.OrderMealDTO;
import Food_app.domain.OrderMeal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-22T14:43:39+0200",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.7 (JetBrains s.r.o.)"
)
@Component
public class MealOrderMapperImpl implements MealOrderMapper {

    @Override
    public OrderMealDTO map(OrderMeal meal) {
        if ( meal == null ) {
            return null;
        }

        OrderMealDTO.OrderMealDTOBuilder orderMealDTO = OrderMealDTO.builder();

        orderMealDTO.price( bigDecimalMapper( meal.getPrice() ) );
        orderMealDTO.quantity( meal.getQuantity() );

        return orderMealDTO.build();
    }
}
