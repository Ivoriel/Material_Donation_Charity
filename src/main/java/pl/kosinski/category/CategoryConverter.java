package pl.kosinski.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter implements Converter<String, CategoryDto> {

    @Autowired
    private CategoryService categoryService;

    public CategoryDto convert(String source) {
        return categoryService.findCategory(Long.parseLong(source));
    }

}
