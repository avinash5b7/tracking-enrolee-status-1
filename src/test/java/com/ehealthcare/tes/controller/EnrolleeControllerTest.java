package com.ehealthcare.tes.controller;

import com.ehealthcare.tes.domain.Dependent;
import com.ehealthcare.tes.domain.Enrollee;
import com.ehealthcare.tes.service.IDependentService;
import com.ehealthcare.tes.service.IEnrolleeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EnrolleeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IEnrolleeService enrolleeService;

    @MockBean
    private IDependentService dependentService;

    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(get("/enrollee"))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    public void testFindById() throws Exception {
        // Given
        when(enrolleeService.findById(anyInt())).thenReturn(Optional.of(createEnrollee()));

        // When/ Then
        mockMvc.perform(get("/enrollee/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Robert")))
                .andExpect(jsonPath("$.phoneNumber", is("2145695987")));

        verify(enrolleeService, times(1)).findById(anyInt());
    }

    @Test
    public void testFindById_throwResourceNotFoundException() throws Exception {
        // Given
        when(enrolleeService.findById(anyInt())).thenReturn(Optional.empty());

        // When/ Then
        mockMvc.perform(get("/enrollee/1"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(enrolleeService, times(1)).findById(anyInt());
    }

    @Test
    public void testSave() throws Exception {
        // Given
        String requestBody = new ObjectMapper().valueToTree(createEnrollee()).toString();
        when(enrolleeService.save(any())).thenReturn(createEnrollee());

        // When/ Then
        mockMvc.perform(post("/enrollee/save")
                .content(requestBody)
                .accept("application/json")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Robert")))
                .andExpect(jsonPath("$.phoneNumber", is("2145695987")));

        verify(enrolleeService, times(1)).save(any());
    }

    @Test
    public void testUpdate() throws Exception {
        // Given
        String requestBody = new ObjectMapper().valueToTree(createEnrollee()).toString();
        when(enrolleeService.update(any())).thenReturn(createEnrollee());

        // When/ Then
        mockMvc.perform(post("/enrollee/update")
                .content(requestBody)
                .accept("application/json")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Robert")))
                .andExpect(jsonPath("$.phoneNumber", is("2145695987")));

        verify(enrolleeService, times(1)).update(any());
    }

    @Test
    public void testFindAll() throws Exception {
        // Given
        when(enrolleeService.findAll()).thenReturn(getAllEnrollees());

        // When/ Then
        mockMvc.perform(get("/enrollee/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Robert")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Michael")));

        verify(enrolleeService, times(1)).findAll();
    }

    @Test
    public void testDeleteById() throws Exception {
        // When/ Then
        mockMvc.perform(delete("/enrollee/1"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(enrolleeService, times(1)).deleteById(anyInt());
    }

    @Test
    public void testDeleteAll() throws Exception {
        // When/ Then
        mockMvc.perform(delete("/enrollee/all"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(enrolleeService, times(1)).deleteAll();
    }

    @Test
    public void testFindDependentById() throws Exception {
        // Given
        when(dependentService.findById(anyInt())).thenReturn(Optional.of(createDependent()));

        // When/ Then
        mockMvc.perform(get("/enrollee/dependent/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Dependent1")));

        verify(dependentService, times(1)).findById(anyInt());
    }

    @Test
    public void testFindDependentById_throwResourceNotFoundException() throws Exception {
        // Given
        when(dependentService.findById(anyInt())).thenReturn(Optional.empty());

        // When/ Then
        mockMvc.perform(get("/enrollee/dependent/1"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(dependentService, times(1)).findById(anyInt());
    }

    @Test
    public void testSaveDependent() throws Exception {
        // Given
        String requestBody = new ObjectMapper().valueToTree(createDependent()).toString();
        when(enrolleeService.findById(anyInt())).thenReturn(Optional.of(createEnrollee()));
        when(dependentService.save(any())).thenReturn(createDependent());

        // When/ Then
        mockMvc.perform(post("/enrollee/1/dependent/save")
                .content(requestBody)
                .accept("application/json")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Dependent1")));

        verify(enrolleeService, times(1)).findById(anyInt());
        verify(dependentService, times(1)).save(any());
    }

    @Test
    public void testUpdateDependent() throws Exception {
        // Given
        String requestBody = new ObjectMapper().valueToTree(createDependent()).toString();
        when(enrolleeService.findById(anyInt())).thenReturn(Optional.of(createEnrollee()));
        when(dependentService.update(any())).thenReturn(createDependent());

        // When/ Then
        mockMvc.perform(post("/enrollee/1/dependent/update")
                .content(requestBody)
                .accept("application/json")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Dependent1")));

        verify(enrolleeService, times(1)).findById(anyInt());
        verify(dependentService, times(1)).update(any());
    }

    @Test
    public void testListDependentsByEnrolleeId() throws Exception {
        // Given
        when(dependentService.findByEnrolleeId(anyInt())).thenReturn(getAllDependents());

        // When/ Then
        mockMvc.perform(get("/enrollee/1/dependent/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Dependent1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Dependent2")));

        verify(dependentService, times(1)).findByEnrolleeId(anyInt());
    }

    @Test
    public void testDeleteDependentById() throws Exception {
        // When/ Then
        mockMvc.perform(delete("/enrollee/dependent/1"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(dependentService, times(1)).deleteById(anyInt());
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
}
