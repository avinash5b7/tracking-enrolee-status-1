package com.ehealthcare.tes.service;

import com.ehealthcare.tes.domain.Enrollee;
import com.ehealthcare.tes.repository.EnrolleeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnrolleeService implements IEnrolleeService {

    @Autowired
    private EnrolleeRepository enrolleeRepository;

    @Override
    public Enrollee save(Enrollee enrollee) {
        return enrolleeRepository.save(enrollee);
    }

    @Override
    public Optional<Enrollee> findById(Integer id) {
        return enrolleeRepository.findById(id);
    }

    @Override
    public Enrollee update(Enrollee enrollee) {
        return enrolleeRepository.save(enrollee);
    }

    @Override
    public Iterable<Enrollee> findAll() {
        return enrolleeRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        enrolleeRepository.deleteById(id);
    }

    @Override
    public void delete(Enrollee enrollee) {
        enrolleeRepository.delete(enrollee);
    }

    @Override
    public void deleteAll() {
        enrolleeRepository.deleteAll();
    }
}
