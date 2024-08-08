package com.fiap.tc.adapters.repository.output;

import br.com.six2six.fixturefactory.Fixture;
import com.fiap.tc.adapters.driven.infrastructure.outputs.CategoryOutputAdapter;
import com.fiap.tc.adapters.driven.infrastructure.persistence.repositories.CategoryRepository;
import com.fiap.tc.adapters.driven.infrastructure.persistence.entities.CategoryEntity;
import com.fiap.tc.core.domain.exception.BadRequestException;
import com.fiap.tc.core.domain.exception.NotFoundException;
import com.fiap.tc.core.domain.requests.CategoryRequest;
import com.fiap.tc.fixture.FixtureTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryOutputAdapterTests extends FixtureTest {

    public static final UUID RANDOM_UUID = UUID.randomUUID();
    public static final UUID ID_CATEGORY = UUID.randomUUID();

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryOutputAdapter categoryOutputAdapter;

    private CategoryEntity categoryEntity;

    private CategoryRequest request;

    private Pageable pageable;

    @BeforeEach
    public void setUp() {
        categoryEntity = Fixture.from(CategoryEntity.class).gimme("valid");
        request = Fixture.from(CategoryRequest.class).gimme("valid");
        pageable = Mockito.mock(Pageable.class);
    }

    @Test
    public void deleteTest() {

        when(categoryRepository.findByUuid(RANDOM_UUID)).thenReturn(Optional.of(categoryEntity));

        categoryOutputAdapter.delete(RANDOM_UUID);

        verify(categoryRepository).delete(any());
    }

    @Test
    public void loadTest() {

        when(categoryRepository.findByUuid(RANDOM_UUID)).thenReturn(Optional.of(categoryEntity));

        var category = categoryOutputAdapter.load(RANDOM_UUID);

        assertNotNull(category);
        verify(categoryRepository).findByUuid(RANDOM_UUID);
    }


    @Test
    public void listTest() {
        final var categoryEntities = new PageImpl<CategoryEntity>(List.of(categoryEntity));
        when(categoryRepository.findAll(Mockito.any(Pageable.class))).thenReturn(categoryEntities);

        var categories = categoryOutputAdapter.list(pageable);

        assertEquals(1, categories.getSize());
        verify(categoryRepository).findAll(Mockito.any(Pageable.class));
    }

    @Test
    public void categoryNotFoundTest() {

        when(categoryRepository.findByUuid(RANDOM_UUID)).thenReturn(Optional.empty());

        var assertThrows = Assertions.assertThrows(NotFoundException.class,
                () -> categoryOutputAdapter.load(RANDOM_UUID));

        assertTrue(assertThrows.getMessage().contains("not found"));
    }

    @Test
    public void saveTest() {

        when(categoryRepository.findByName(request.getName())).thenReturn(Optional.empty());
        when(categoryRepository.save(Mockito.any())).thenReturn(categoryEntity);

        var category = categoryOutputAdapter.saveOrUpdate(request.getName(),
                request.getDescription(), request.getActive());

        assertNotNull(category);
        verify(categoryRepository).findByName(request.getName());
        verify(categoryRepository).save(Mockito.any());
    }

    @Test
    public void updateTest() {

        when(categoryRepository.findByName(request.getName())).thenReturn(Optional.of(categoryEntity));
        when(categoryRepository.save(categoryEntity)).thenReturn(categoryEntity);

        var category = categoryOutputAdapter.saveOrUpdate(request.getName(),
                request.getDescription(), request.getActive());

        assertNotNull(category);
        verify(categoryRepository).findByName(request.getName());
        verify(categoryRepository).save(Mockito.any());
    }

    @Test
    public void updateCategoryWithIdTest() {
        when(categoryRepository.findByUuid(ID_CATEGORY)).thenReturn(Optional.of(categoryEntity));
        when(categoryRepository.findByName(request.getName())).thenReturn(Optional.empty());
        when(categoryRepository.save(categoryEntity)).thenReturn(categoryEntity);

        var category = categoryOutputAdapter.update(ID_CATEGORY, request.getName(),
                request.getDescription(), request.getActive());

        assertNotNull(category);
        verify(categoryRepository).findByName(request.getName());
        verify(categoryRepository).findByUuid(ID_CATEGORY);
        verify(categoryRepository).save(Mockito.any());
    }

    @Test
    public void launchBadRequestExceptionOnUpdateCategoryWithIdWhenCategoryAlreadyExistsTest() {
        when(categoryRepository.findByUuid(ID_CATEGORY)).thenReturn(Optional.of(categoryEntity));
        when(categoryRepository.findByName(request.getName())).thenReturn(Optional.of(categoryEntity));


        var assertThrows = Assertions.assertThrows(BadRequestException.class, () -> categoryOutputAdapter.update(ID_CATEGORY, request.getName(),
                request.getDescription(), request.getActive()));


        assertTrue(assertThrows.getMessage().contains("already exists"));
    }

    @Test
    public void launchNotFoundExceptionOnUpdateCategoryWithIdWhenCategoryNotExistsTest() {
        when(categoryRepository.findByUuid(ID_CATEGORY)).thenReturn(Optional.empty());

        var assertThrows = Assertions.assertThrows(NotFoundException.class, () -> categoryOutputAdapter.update(ID_CATEGORY, request.getName(),
                request.getDescription(), request.getActive()));


        assertTrue(assertThrows.getMessage().contains("not found"));
    }

}