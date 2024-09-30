package com.fiap.tc.infrastructure.gateways;

import br.com.six2six.fixturefactory.Fixture;
import com.fiap.tc.domain.exceptions.BadRequestException;
import com.fiap.tc.domain.exceptions.NotFoundException;
import com.fiap.tc.fixture.FixtureTest;
import com.fiap.tc.infrastructure.persistence.entities.CategoryEntity;
import com.fiap.tc.infrastructure.persistence.repositories.CategoryRepository;
import com.fiap.tc.infrastructure.presentation.requests.CategoryRequest;
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
public class CategoryGatewayTests extends FixtureTest {

    public static final UUID RANDOM_UUID = UUID.randomUUID();
    public static final UUID ID_CATEGORY = UUID.randomUUID();

    @Mock
    private CategoryRepository repository;

    @InjectMocks
    private CategoryGateway categoryGateway;

    private CategoryEntity categoryEntity;

    private CategoryEntity categorySnackEntity;

    private CategoryRequest request;

    private Pageable pageable;

    @BeforeEach
    public void setUp() {
        categoryEntity = Fixture.from(CategoryEntity.class).gimme("valid");
        categorySnackEntity = Fixture.from(CategoryEntity.class).gimme("valid");
        categorySnackEntity.setName("Snack");

        pageable = Mockito.mock(Pageable.class);
        request = Fixture.from(CategoryRequest.class).gimme("valid");
    }

    @Test
    public void deleteTest() {

        when(repository.findByUuid(RANDOM_UUID)).thenReturn(Optional.of(categoryEntity));

        categoryGateway.delete(RANDOM_UUID);

        verify(repository).delete(any());
    }

    @Test
    public void loadTest() {

        when(repository.findByUuid(RANDOM_UUID)).thenReturn(Optional.of(categoryEntity));

        var category = categoryGateway.load(RANDOM_UUID);

        assertNotNull(category);
        verify(repository).findByUuid(RANDOM_UUID);
    }


    @Test
    public void listTest() {
        final var categoryEntities = new PageImpl<>(List.of(categoryEntity));
        when(repository.findAll(Mockito.any(Pageable.class))).thenReturn(categoryEntities);

        var categories = categoryGateway.list(pageable);

        assertEquals(1, categories.getSize());
        verify(repository).findAll(Mockito.any(Pageable.class));
    }

    @Test
    public void categoryNotFoundTest() {

        when(repository.findByUuid(RANDOM_UUID)).thenReturn(Optional.empty());

        var assertThrows = Assertions.assertThrows(NotFoundException.class,
                () -> categoryGateway.load(RANDOM_UUID));

        assertTrue(assertThrows.getMessage().contains("not found"));
    }

    @Test
    public void saveTest() {

        when(repository.findByName(request.getName())).thenReturn(Optional.empty());
        when(repository.save(Mockito.any())).thenReturn(categoryEntity);

        var category = categoryGateway.register(request.getName(),
                request.getDescription());

        assertNotNull(category);
        verify(repository).findByName(request.getName());
        verify(repository).save(Mockito.any());
    }

    @Test
    public void updateTest() {

        when(repository.findByName(request.getName())).thenReturn(Optional.of(categoryEntity));
        when(repository.save(categoryEntity)).thenReturn(categoryEntity);

        var category = categoryGateway.register(request.getName(), request.getDescription());

        assertNotNull(category);
        verify(repository).findByName(request.getName());
        verify(repository).save(Mockito.any());
    }

    @Test
    public void updateCategoryWithSameNamesWhenCategoryByUuidExistsTest() {
        categoryEntity.setName("drink");
        request.setName("drink");
        when(repository.findByUuid(ID_CATEGORY)).thenReturn(Optional.of(categoryEntity));
        when(repository.findByName(request.getName())).thenReturn(Optional.empty());
        when(repository.save(categoryEntity)).thenReturn(categoryEntity);

        var category = categoryGateway.update(ID_CATEGORY, request.getName(), request.getDescription());

        assertNotNull(category);
        verify(repository).findByName(request.getName());
        verify(repository).findByUuid(ID_CATEGORY);
        verify(repository).save(Mockito.any());
    }

    @Test
    public void updateCategoryWithDiffNamesTest() {
        when(repository.findByUuid(ID_CATEGORY)).thenReturn(Optional.of(categorySnackEntity));
        when(repository.findByName(request.getName())).thenReturn(Optional.empty());
        when(repository.save(Mockito.any())).thenReturn(categoryEntity);

        var category = categoryGateway.update(ID_CATEGORY, request.getName(), request.getDescription());

        assertNotNull(category);
        verify(repository).findByName(request.getName());
        verify(repository).findByUuid(ID_CATEGORY);
        verify(repository).save(Mockito.any());
    }

    @Test
    public void launchBadRequestExceptionOnUpdateCategoryWithIdWhenCategoryAlreadyExistsTest() {
        when(repository.findByUuid(ID_CATEGORY)).thenReturn(Optional.of(categorySnackEntity));
        when(repository.findByName(request.getName())).thenReturn(Optional.of(categoryEntity));


        var assertThrows = Assertions.assertThrows(BadRequestException.class,
                () -> categoryGateway.update(ID_CATEGORY, request.getName(), request.getDescription()));


        assertTrue(assertThrows.getMessage().contains("already exists"));
    }

    @Test
    public void launchNotFoundExceptionOnUpdateCategoryWithIdWhenCategoryNotExistsTest() {
        when(repository.findByUuid(ID_CATEGORY)).thenReturn(Optional.empty());

        var assertThrows = Assertions.assertThrows(NotFoundException.class,
                () -> categoryGateway.update(ID_CATEGORY, request.getName(), request.getDescription()));

        assertTrue(assertThrows.getMessage().contains("not found"));
    }

}