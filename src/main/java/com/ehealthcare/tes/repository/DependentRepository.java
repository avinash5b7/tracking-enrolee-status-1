package com.ehealthcare.tes.repository;

import com.ehealthcare.tes.domain.Dependent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DependentRepository extends CrudRepository<Dependent, Integer> {

    /**
     * The method return the list of {@link Dependent} objects by enrollee id
     *
     * @param enrolleeId
     * @return {@link List<Dependent>} object
     */
    @Query("from Dependent d where d.enrollee.id = :enrolleeId")
    List<Dependent> findByEnrolleeId(@Param("enrolleeId") Integer enrolleeId);
}
