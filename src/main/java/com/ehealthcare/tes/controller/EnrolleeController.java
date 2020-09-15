package com.ehealthcare.tes.controller;

import com.ehealthcare.tes.domain.Dependent;
import com.ehealthcare.tes.domain.Enrollee;
import com.ehealthcare.tes.exception.ResourceNotFoundException;
import com.ehealthcare.tes.service.IDependentService;
import com.ehealthcare.tes.service.IEnrolleeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * The {@link EnrolleeController} REST API class handles all the CRUD operations related to
 * Enrollee and Dependent resource.
 *
 * @author Avinash
 * @version 1.o
 */
@Validated
@RestController
@RequestMapping("/enrollee")
public class EnrolleeController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IEnrolleeService enrolleeService;
    @Autowired
    private IDependentService dependentService;

    /**
     * The resource endpoint redirect to the list of enrollee's endpoint
     *
     * @return redirect url to list of enrollee's
     */
    @GetMapping(value = {"", "/"})
    public String index() {
        logger.info(":: Entered into index() method ::");
        return "redirect:/list";
    }

    /**
     * The resource endpoint return the Enrollee object by enrollee id, or else throws
     * {@link ResourceNotFoundException} exception
     *
     * @param id enrollee Id
     * @return returns {@link Enrollee} object
     */
    @GetMapping(value = {"/{id}"})
    public Enrollee findById(@PathVariable Integer id) {
        logger.info(":: Entered into findById() method ::");
        return enrolleeService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollee", "id", id));
    }

    /**
     * The resource endpoint save the {@link Enrollee} object
     *
     * @param enrollee {@link Enrollee} object
     * @return returns {@link Enrollee} object
     */
    @PostMapping(value = {"/save"})
    public Enrollee save(@Valid @RequestBody Enrollee enrollee) {
        logger.info(":: Entered into save() method ::");
        return enrolleeService.save(enrollee);
    }

    /**
     * The resource endpoint update the {@link Enrollee} object
     *
     * @param enrollee {@link Enrollee} object
     * @return returns {@link Enrollee} object
     */
    @PostMapping(value = {"/update"})
    public Enrollee update(@Valid @RequestBody Enrollee enrollee) {
        logger.info(":: Entered into update() method ::");
        return enrolleeService.update(enrollee);
    }

    /**
     * The resource endpoint return the list of enrollee objects
     *
     * @return {@link List<Enrollee>} list of enrollee objects
     */
    @GetMapping(value = {"/list"})
    public List<Enrollee> findAll() {
        logger.info(":: Entered into findAll() method ::");
        return (List<Enrollee>) enrolleeService.findAll();
    }

    /**
     * The resource endpoint delete the enrollee object by enrollee id
     *
     * @param id enrollee id
     */
    @DeleteMapping(value = {"/{id}"})
    public void deleteById(@PathVariable Integer id) {
        logger.info(":: Entered into deleteById() method ::");
        enrolleeService.deleteById(id);
    }

    /**
     * The reource endpoint delete all the enrollee objects
     */
    @DeleteMapping(value = "/all")
    public void deleteAll() {
        logger.info(":: Entered into deleteAll() method ::");
        enrolleeService.deleteAll();
    }

    /**
     * The resource endpoint return the {@link Dependent} object by dependent id
     *
     * @param id dependent id
     * @return {@link Dependent} object
     */
    @GetMapping(value = {"/dependent/{id}"})
    public Dependent findDependentById(@PathVariable Integer id) {
        logger.info(":: Entered into findDependentById() method ::");
        return dependentService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dependent", "id", id));
    }

    /**
     * The resource endpoint save the {@link Dependent} object to the given enrollee id
     *
     * @param enrolleeId enrollee Id
     * @param dependent {@link Dependent} object
     * @return {@link Dependent} object
     */
    @PostMapping(value = {"/{enrolleeId}/dependent/save"})
    public Dependent saveDependent(@PathVariable Integer enrolleeId, @Valid @RequestBody Dependent dependent) {
        logger.info(":: Entered into saveDependent() method ::");
        Optional<Enrollee> enrollee = enrolleeService.findById(enrolleeId);
        if(enrollee.isPresent()) {
            dependent.setEnrollee(enrollee.get());
            return dependentService.save(dependent);
        } else {
           throw new ResourceNotFoundException("Enrolleee", "id", enrolleeId);
        }
    }

    /**
     * The resource endpoint update the {@link Dependent} object to the given enrollee id
     *
     * @param enrolleeId enrollee Id
     * @param dependent {@link Dependent} object
     * @return {@link Dependent} object
     */
    @PostMapping(value = {"/{enrolleeId}/dependent/update"})
    public Dependent updateDependent(@PathVariable Integer enrolleeId, @Valid @RequestBody Dependent dependent) {
        logger.info(":: Entered into updateDependent() method ::");
        Optional<Enrollee> enrollee = enrolleeService.findById(enrolleeId);
        if(enrollee.isPresent()) {
            dependent.setEnrollee(enrollee.get());
            return dependentService.update(dependent);
        } else {
            throw new ResourceNotFoundException("Enrolleee", "id", enrolleeId);
        }
    }

    /**
     * The resource endpoint return the list of dependents to the enrollee by enrollee id
     *
     * @param enrolleeId
     * @return {@link List<Dependent>} list of Dependent objects
     */
    @GetMapping(value = {"/{enrolleeId}/dependent/list"})
    public List<Dependent> listDependentsByEnrolleeId(@PathVariable Integer enrolleeId) {
        logger.info(":: Entered into listDependentsByEnrolleeId() method ::");
        return (List<Dependent>) dependentService.findByEnrolleeId(enrolleeId);
    }

    /**
     * The resource endpoint delete the dependent by dependent id
     *
     * @param id dependent id
     */
    @DeleteMapping(value = {"/dependent/{id}"})
    public void deleteDependentById(@PathVariable Integer id) {
        logger.info(":: Entered into deleteDependentById() method ::");
        dependentService.deleteById(id);
    }

}
