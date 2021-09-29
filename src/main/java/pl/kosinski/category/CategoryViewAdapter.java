package pl.kosinski.category;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryViewAdapter implements CategoryService{

    private CategoryRepository categoryRepository;

    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        Category category =  new Category();
        if (categoryDto.getId() != null) {
            category = categoryRepository.findById(categoryDto.getId()).get();
            category.setName(category.getName());
            category = categoryRepository.save(category);
        } else {
            category.setName(categoryDto.getName());
            category = categoryRepository.save(category);
        }
        return mapEntityToDto(category);
    }

    @Override
    public CategoryDto readCategory(long id) {
        return mapEntityToDto(categoryRepository.findById(id).get());
    }

    @Override
    public List<CategoryDto> findAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        for (Category category : categoryList) {
            CategoryDto categoryDto = mapEntityToDto(category);
            categoryDtoList.add(categoryDto);
        }
        return categoryDtoList;
    }

    @Override
    public void deleteCategory(long id) {
        categoryRepository.deleteById(id);
    }

    private CategoryDto mapEntityToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        return categoryDto;
    }

}

