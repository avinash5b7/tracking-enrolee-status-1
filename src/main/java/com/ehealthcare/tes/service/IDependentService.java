package com.ehealthcare.tes.service;

import com.ehealthcare.tes.domain.Dependent;

import java.util.List;
import java.util.Optional;

public interface IDependentService {

    /**
     * The method save and return the persisted {@link Dependent} object
     *
     * @param depedent
     * @return {@link Dependent} object
     */
    Dependent save(Dependent depedent);

    /**
     * The method return the {@link Dependent} object or null
     *
     * @param id dependent id
     * @return {@link Optional<Dependent>} object
     */
    Optional<Dependent> findById(Integer id);

    /**
     * The method update and return the updated {@link Dependent} object
     *
     * @param depedent
     * @return {@link Dependent} object
     */
    Dependent update(Dependent depedent);

    /**
     * The method return the list of {@link Dependent} objects
     *
     * @return {@link Iterable<Dependent>} object
     */
    Iterable<Dependent> findAll();

    /**
     * The method delete the {@link Dependent} object by dependent id
     *
     * @param id dependent id
     */
    void deleteById(Integer id);

    /**
     * The method delete the {@link Dependent} object by Dependent object
     *
     * @param depedent {@link Dependent} object
     */
    void delete(Dependent depedent);

    /**
     * The method delete all the dependents objects
     */
    void deleteAll();

    /**
     * The method return the list of {@link Dependent} objects by enrollee id
     *
     * @param enrolleeId
     * @return {@link List<Dependent>} object
     */
    List<Dependent> findByEnrolleeId(Integer enrolleeId);

}
