package ua.com.diakin.medicalclinic.repository;

import org.springframework.dao.DataAccessException;
import ua.com.diakin.medicalclinic.model.BaseEntity;
import ua.com.diakin.medicalclinic.model.Stuff;

import java.util.Collection;
import java.util.List;

/**
 * Repository class for <code>Stuff</code> domain objects All method names are compliant with Spring Data naming
 * conventions so this interface can easily be extended for Spring Data See here: http://static.springsource.org/spring-data/jpa/docs/current/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
 */
public interface StuffRepository {

    /**
     * Retrieve <code>Stuff</code>s from the data store by last name, returning all owners whose last name <i>starts</i>
     * with the given name.
     *
     * @param lastName Value to search for
     * @return a <code>Collection</code> of matching <code>Owner</code>s (or an empty <code>Collection</code> if none
     * found)
     */
    Collection<Stuff> findByLastName(String lastName) throws DataAccessException;

    /**
     * Retrieve an <code>Stuff</code> from the data store by id.
     *
     * @param id the id to search for
     * @return the <code>Stuff</code> if found
     * @throws org.springframework.dao.DataRetrievalFailureException if not found
     */
    Stuff findById(int id) throws DataAccessException;

    /**
     * Save an <code>Stuff</code> to the data store, either inserting or updating it.
     *
     * @param stuff the <code>Stuff</code> to save
     * @see BaseEntity#isNew
     */
    void save(Stuff stuff) throws DataAccessException;

    Collection<Stuff> getStuff() throws DataAccessException;

}
