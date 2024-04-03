package de.seven.search.adapter.primary;

import de.seven.search.adapter.primary.model.SearchDTO;
import de.seven.search.application.model.SearchCriteria;
import de.seven.search.application.service.SearchService;
import de.seven.search.domain.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
@AllArgsConstructor
public class SearchController {
    private final SearchService searchService;

    @PostMapping("/")
    public List<Product> search(@RequestBody SearchCriteria searchCriteria) {
        return searchService.searchProducts(searchCriteria);
    }

    @PostMapping("/filter")
    public List<Product> searchWithFilter(@RequestBody SearchDTO searchDTO) {
        return searchService.searchProducts(searchDTO.getSearchCriteria(), searchDTO.getFilterCriteria());
    }


}
