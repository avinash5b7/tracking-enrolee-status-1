package com.ehealthcare.tes.service;

import com.ehealthcare.tes.domain.Enrollee;

import java.util.Optional;

public interface IEnrolleeService {

    /**
     * The method save and return the persisted {@link Enrollee} object
     *
     * @param enrollee
     * @return {@link Enrollee} object
     */
    Enrollee save(Enrollee enrollee);

    /**
     * The method return the {@link Enrollee} object or null
     *
     * @param id enrollee id
     * @return {@link Optional<Enrollee>} object
     */
    Optional<Enrollee> findById(Integer id);

    /**
     * The method update and return the updated {@link Enrollee} object
     *
     * @param enrollee
     * @return {@link Enrollee} object
     */
    Enrollee update(Enrollee enrollee);

    /**
     * The method return the list of {@link Enrollee} objects
     *
     * @return {@link Iterable<Enrollee>} object
     */
    Iterable<Enrollee> findAll();

    /**
     * The method delete the {@link Enrollee} object by enrollee id
     *
     * @param id enrollee id
     */
    void deleteById(Integer id);

    /**
     * The method delete the {@link Enrollee} object by Enrollee object
     *
     * @param enrollee {@link Enrollee} object
     */
    void delete(Enrollee enrollee);

    /**
     * The method delete all the enrollee objects
     */
    void deleteAll();
}
