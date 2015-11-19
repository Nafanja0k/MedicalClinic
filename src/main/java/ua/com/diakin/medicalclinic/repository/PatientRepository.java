package ua.com.diakin.medicalclinic.repository;

import org.springframework.dao.DataAccessException;
import ua.com.diakin.medicalclinic.model.BaseEntity;
import ua.com.diakin.medicalclinic.model.Patient;

import java.util.Collection;

/**
 * Repository class for <code>Patient</code> domain objects All method names are compliant with Spring Data naming
 * conventions so this interface can easily be extended for Spring Data See here: http://static.springsource.org/spring-data/jpa/docs/current/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
 */
public interface PatientRepository {

    /**
     * Retrieve <code>Patient</code>s from the data store by last name, returning all owners whose last name <i>starts</i>
     * with the given name.
     *
     * @param lastName Value to search for
     * @return a <code>Collection</code> of matching <code>Owner</code>s (or an empty <code>Collection</code> if none
     * found)
     */
    Collection<Patient> findByLastName(String lastName) throws DataAccessException;

    /**
     * Retrieve an <code>Patient</code> from the data store by id.
     *
     * @param id the id to search for
     * @return the <code>Patient</code> if found
     * @throws org.springframework.dao.DataRetrievalFailureException if not found
     */
    Patient findById(int id) throws DataAccessException;

    /**
     * Save an <code>Patient</code> to the data store, either inserting or updating it.
     *
     * @param patient the <code>Patient</code> to save
     * @see BaseEntity#isNew
     */
    void save(Patient patient) throws DataAccessException;


}
