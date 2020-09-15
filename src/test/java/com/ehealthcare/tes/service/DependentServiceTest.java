package com.ehealthcare.tes.service;

import com.ehealthcare.tes.domain.Dependent;
import com.ehealthcare.tes.domain.Enrollee;
import com.ehealthcare.tes.repository.DependentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DependentServiceTest {

    @Mock
    DependentRepository dependentRepository;

    @InjectMocks
    DependentService dependentService;

    @Test
    public void testSaveDependent() {
        // Given
        when(dependentRepository.save(any())).thenReturn(createDependent());

        // When/ Then
        assertThat(dependentService.save(any()))
                .isNotNull()
                .extracting(Dependent::getId, Dependent::getName)
                .contains(1, "Dependent1");
    }

    @Test
    public void testFindById_Return_Dependent() {
        // Given
        when(dependentRepository.findById(anyInt())).thenReturn(Optional.of(createDependent()));

        // When/ Then
        assertThat(dependentService.findById(anyInt()))
                .isPresent()
                .get()
                .extracting(Dependent::getId, Dependent::getName)
                .contains(1, "Dependent1");
    }

    @Test
    public void testFindById_Return_Empty() {
        // Given
        when(dependentRepository.findById(anyInt())).thenReturn(Optional.empty());

        // When/ Then
        assertThat(dependentService.findById(anyInt()))
                .isNotPresent()
                .isNotNull();

    }

    @Test
    public void testUpdateDependent() {
        // Given
        when(dependentRepository.save(any())).thenReturn(createDependent());

        // When/ Then
        assertThat(dependentService.update(any()))
                .isNotNull()
                .extracting(Dependent::getId, Dependent::getName)
                .contains(1, "Dependent1");
    }

    @Test
    public void testFindAll() {
        // Given
        when(dependentRepository.findAll()).thenReturn(getAllDependents());

        // When/ Then
        assertThat(dependentService.findAll())
                .isNotEmpty()
                .isNotNull()
                .hasSize(2)
                .extracting(Dependent::getId, Dependent::getName)
                .contains(
                        tuple(1,"Dependent1"),
                        tuple(2, "Dependent2")
                );
    }

    @Test
    public void testFindAll_Empty() {
        // Given
        when(dependentRepository.findAll()).thenReturn(new ArrayList<>());

        // When/ Then
        assertThat(dependentService.findAll())
                .isNotNull()
                .isEmpty();
    }

    @Test
    public void testDeleteById() {
        // When
        dependentService.deleteById(anyInt());

        // Then
        verify(dependentRepository, times(1)).deleteById(anyInt());
    }

    @Test
    public void testDeleteByDependent() {
        // When
        dependentService.delete(any());

        // Then
        verify(dependentRepository, times(1)).delete(any());
    }

    @Test
    public void testDeleteAll() {
        // When
        dependentService.deleteAll();

        // Then
        verify(dependentRepository, times(1)).deleteAll();
    }

    public Dependent createDependent() {
        Dependent dependent = new Dependent();
        dependent.setId(1);
        dependent.setName("Dependent1");
        dependent.setDateOfBirth(new Date());
        dependent.setEnrollee(createEnrollee());

        return dependent;
    }

    private List<Dependent> getAllDependents() {

        Dependent dependent1 = new Dependent();
        dependent1.setId(1);
        dependent1.setName("Dependent1");
        dependent1.setDateOfBirth(new Date());
        dependent1.setEnrollee(createEnrollee());

        Dependent dependent2 = new Dependent();
        dependent2.setId(2);
        dependent2.setName("Dependent2");
        dependent2.setDateOfBirth(new Date());
        dependent2.setEnrollee(createEnrollee());

        return new ArrayList<Dependent>() {{
            add(dependent1);
            add(dependent2);
        }};

    }

    private Enrollee createEnrollee() {
        Enrollee enrollee = new Enrollee();
        enrollee.setId(1);
        enrollee.setName("Robert");
        enrollee.setActivationStatus(Boolean.TRUE);
        enrollee.setDateOfBirth(new Date());
        enrollee.setPhoneNumber("2145695987");

        return enrollee;
    }
}
