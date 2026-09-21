package za.ac.cput.communitystoreplatform.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import za.ac.cput.communitystoreplatform.domain.Category;
import za.ac.cput.communitystoreplatform.repository.CategoryRepository;
import za.ac.cput.communitystoreplatform.service.impl.CategoryService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    @Mock
    private CategoryRepository repository;

    private CategoryService service;
    private Category category;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        service = new CategoryService(repository);

        category = new Category.Builder()
                .setCategoryId(1)
                .setCategoryName("Electronics")
                .setDescription("Electronic products")
                .build();
    }

    @Test
    void create() {
        when(repository.save(category)).thenReturn(category);

        Category result = service.create(category);

        assertNotNull(result);
        assertEquals("Electronics", result.getCategoryName());
        verify(repository).save(category);
    }

    @Test
    void update() {
        when(repository.save(category)).thenReturn(category);

        Category result = service.update(category);

        assertNotNull(result);
        assertEquals(1, result.getCategoryId());
        verify(repository).save(category);
    }

    @Test
    void read() {
        when(repository.findById(1))
                .thenReturn(Optional.of(category));

        Category result = service.read(1);

        assertNotNull(result);
        assertEquals(1, result.getCategoryId());
        assertEquals("Electronics", result.getCategoryName());
    }

    @Test
    void readNotFound() {
        when(repository.findById(99))
                .thenReturn(Optional.empty());

        Category result = service.read(99);

        assertNull(result);
    }

    @Test
    void getAll() {
        List<Category> categories = Arrays.asList(category);

        when(repository.findAll()).thenReturn(categories);

        List<Category> result = service.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Electronics",
                result.get(0).getCategoryName());

        verify(repository).findAll();
    }

    @Test
    void findByCategoryName() {
        when(repository.findByCategoryName("Electronics"))
                .thenReturn(Optional.of(category));

        Category result =
                service.findByCategoryName("Electronics");

        assertNotNull(result);
        assertEquals("Electronics",
                result.getCategoryName());
    }

    @Test
    void findByCategoryNameNotFound() {
        when(repository.findByCategoryName("Unknown"))
                .thenReturn(Optional.empty());

        Category result =
                service.findByCategoryName("Unknown");

        assertNull(result);
    }

    @Test
    void delete() {
        doNothing().when(repository).deleteById(1);

        service.delete(1);

        verify(repository).deleteById(1);
    }
}