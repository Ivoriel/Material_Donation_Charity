package pl.kosinski.category;

import java.util.List;

public interface CategoryService {

    CategoryDto saveCategory (CategoryDto categoryDto);

    CategoryDto readCategory (long id);

    List<CategoryDto> findAllCategories ();

    void deleteCategory (long id);
}
