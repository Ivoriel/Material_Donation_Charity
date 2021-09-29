package pl.kosinski.category;

import java.util.List;

public interface CategoryService {

    CategoryDto saveCategory (CategoryDto categoryDto);

    CategoryDto findCategory(long id);

    List<CategoryDto> findAllCategories ();

    void deleteCategory (long id);
}
