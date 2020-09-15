package com.ehealthcare.tes.service;

import com.ehealthcare.tes.domain.Dependent;
import com.ehealthcare.tes.repository.DependentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DependentService implements IDependentService {

    @Autowired
    private DependentRepository dependentRepository;

    @Override
    public Dependent save(Dependent depedent) {
        return dependentRepository.save(depedent);
    }

    @Override
    public Optional<Dependent> findById(Integer id) {
        return dependentRepository.findById(id);
    }

    @Override
    public Dependent update(Dependent depedent) {
        return dependentRepository.save(depedent);
    }

    @Override
    public Iterable<Dependent> findAll() {
        return dependentRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        dependentRepository.deleteById(id);
    }

    @Override
    public void delete(Dependent depedent) {
        dependentRepository.delete(depedent);
    }

    @Override
    public void deleteAll() {
        dependentRepository.deleteAll();
    }

    @Override
    public List<Dependent> findByEnrolleeId(Integer enrolleeId) {
        return dependentRepository.findByEnrolleeId(enrolleeId);
    }
}
