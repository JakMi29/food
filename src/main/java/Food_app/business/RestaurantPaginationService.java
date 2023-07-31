package Food_app.business;

import Food_app.infrastructure.database.entity.RestaurantEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RestaurantPaginationService {
    private final RestaurantService restaurantService;

    public Page<RestaurantEntity> paginate(int pageNumber, int pageSize) {

        Sort sort = Sort.by("name").ascending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return restaurantService.findAll(pageable);

    }
}
