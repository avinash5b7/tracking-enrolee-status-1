package com.ehealthcare.tes.repository;

import com.ehealthcare.tes.domain.Enrollee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrolleeRepository extends CrudRepository<Enrollee, Integer> {
}
