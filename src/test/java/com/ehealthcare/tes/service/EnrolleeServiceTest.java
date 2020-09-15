package com.ehealthcare.tes.service;


import com.ehealthcare.tes.domain.Enrollee;
import com.ehealthcare.tes.repository.EnrolleeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EnrolleeServiceTest {

    @Mock
    EnrolleeRepository enrolleeRepository;

    @InjectMocks
    EnrolleeService enrolleeService;

    @Test
    public void testSaveEnrollee() {
        // Given
        when(enrolleeRepository.save(any())).thenReturn(createEnrollee());

        // When/ Then
        assertThat(enrolleeService.save(any()))
                .isNotNull()
                .extracting(Enrollee::getId, Enrollee::getName, Enrollee::getPhoneNumber)
                .contains(1, "Robert", "2145695987");
    }

    @Test
    public void testFindById_Return_Enrollee() {
        // Given
        when(enrolleeRepository.findById(anyInt())).thenReturn(Optional.of(createEnrollee()));

        // When/ Then
        assertThat(enrolleeService.findById(anyInt()))
                .isPresent()
                .get()
                .extracting(Enrollee::getId, Enrollee::getName, Enrollee::getPhoneNumber)
                .contains(1, "Robert", "2145695987");
    }

    @Test
    public void testFindById_Return_Empty() {
        // Given
        when(enrolleeRepository.findById(anyInt())).thenReturn(Optional.empty());

        // When/ Then
        assertThat(enrolleeService.findById(anyInt()))
                .isNotPresent()
                .isNotNull();

    }

    @Test
    public void testUpdateEnrollee() {
        // Given
        when(enrolleeRepository.save(any())).thenReturn(createEnrollee());

        // When/ Then
        assertThat(enrolleeService.update(any()))
                .isNotNull()
                .extracting(Enrollee::getId, Enrollee::getName, Enrollee::getPhoneNumber)
                .contains(1, "Robert", "2145695987");
    }

    @Test
    public void testFindAll() {
        // Given
        when(enrolleeRepository.findAll()).thenReturn(getAllEnrollees());

        // When/ Then
        assertThat(enrolleeService.findAll())
                .isNotEmpty()
                .isNotNull()
                .hasSize(2)
                .extracting(Enrollee::getId, Enrollee::getName, Enrollee::getPhoneNumber)
                .contains(
                        tuple(1,"Robert", "2145695986"),
                        tuple(2, "Michael", "2145695987")
                );
    }

    @Test
    public void testFindAll_Empty() {
        // Given
        when(enrolleeRepository.findAll()).thenReturn(new ArrayList<>());

        // When/ Then
        assertThat(enrolleeService.findAll())
                .isNotNull()
                .isEmpty();
    }

    @Test
    public void testDeleteById() {
        // When
        enrolleeService.deleteById(anyInt());

        // Then
        verify(enrolleeRepository, times(1)).deleteById(anyInt());
    }

    @Test
    public void testDeleteByEnrollee() {
        // When
        enrolleeService.delete(any());

        // Then
        verify(enrolleeRepository, times(1)).delete(any());
    }

    @Test
    public void testDeleteAll() {
        // When
        enrolleeService.deleteAll();

        // Then
        verify(enrolleeRepository, times(1)).deleteAll();
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

    private List<Enrollee> getAllEnrollees() {

        Enrollee enrollee1 = new Enrollee();
        enrollee1.setId(1);
        enrollee1.setName("Robert");
        enrollee1.setActivationStatus(Boolean.TRUE);
        enrollee1.setDateOfBirth(new Date());
        enrollee1.setPhoneNumber("2145695986");

        Enrollee enrollee2 = new Enrollee();
        enrollee2.setId(2);
        enrollee2.setName("Michael");
        enrollee2.setActivationStatus(Boolean.TRUE);
        enrollee2.setDateOfBirth(new Date());
        enrollee2.setPhoneNumber("2145695987");

        return new ArrayList<Enrollee>() {{
            add(enrollee1);
            add(enrollee2);
        }};
    }

}
