package pl.kosinski.category;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CategoryViewAdapterTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void checkIfCategoryIsSavedAndRetrievedWhenCalled() {
        // Tests both save and read methods
        // given Category not present in db
        CategoryViewAdapter adapter = new CategoryViewAdapter(categoryRepository);
        CategoryDto category = new CategoryDto();
        category.setName("test_category");
        // when Category is saved to db
        adapter.saveCategory(category);
        category.setId(1L);
        // then Category should be retrievable when called by id
        assertEquals(category, adapter.findCategory(1));
    }

    @Test
    public void checkIfListOfCategoriesPresentInDbCanBeRetrieved() {
        // given Categories are present in db
        CategoryViewAdapter adapter = new CategoryViewAdapter(categoryRepository);
        List<CategoryDto> categoryList = new ArrayList<>();
        CategoryDto category1 = new CategoryDto();
        category1.setName("test_1");
        adapter.saveCategory(category1);
        category1.setId(1L);
        categoryList.add(category1);
        CategoryDto category2 = new CategoryDto();
        category2.setName("test_2");
        adapter.saveCategory(category2);
        category2.setId(2L);
        categoryList.add(category2);
        // when call to find all categories is executed
        // then all categories should be retrieved
        assertEquals(categoryList, adapter.findAllCategories());
    }

    @Test
    public void checkIfCategoryPresentInDbIsDeleted() {
        // given Category is present in db
        CategoryViewAdapter adapter = new CategoryViewAdapter(categoryRepository);
        CategoryDto category1 = new CategoryDto();
        category1.setName("test_1");
        adapter.saveCategory(category1);
        category1.setId(1L);
        CategoryDto category2 = new CategoryDto();
        category2.setName("test_2");
        adapter.saveCategory(category2);
        category2.setId(2L);
        assertEquals(category1, adapter.findCategory(1));
        // when Category is deleted from db
        adapter.deleteCategory(1);
        // then Category should not be retrievable
        assertEquals(category2, adapter.findCategory(2));
        assertThrows(NoSuchElementException.class, () -> adapter.findCategory(category1.getId()));
    }
}