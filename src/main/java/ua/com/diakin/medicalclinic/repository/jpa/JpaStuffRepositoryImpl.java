package ua.com.diakin.medicalclinic.repository.jpa;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.OpenSessionInViewFilter;
import org.springframework.stereotype.Repository;
import ua.com.diakin.medicalclinic.model.Stuff;
import ua.com.diakin.medicalclinic.repository.StuffRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

/**
 * Created by Admin on 18.11.2015.
 */
@Repository
public class JpaStuffRepositoryImpl implements StuffRepository{

    @PersistenceContext
    private EntityManager em;

    /**
     * Important: in the current version of this method, we load Owners with all their Pets and Visits while
     * we do not need Visits at all and we only need one property from the Pet objects (the 'name' property).
     * There are some ways to improve it such as:
     * - creating a Ligtweight class (example here: https://community.jboss.org/wiki/LightweightClass)
     * - Turning on lazy-loading and using {@link OpenSessionInViewFilter}
     */

    @Override
    public Collection<Stuff> findByLastName(String lastName) throws DataAccessException {
        Query query = this.em.createQuery("SELECT DISTINCT stuff FROM Stuff stuff WHERE stuff.lastName LIKE :lastName");
        query.setParameter("lastName", lastName + "%");
        return query.getResultList();
    }

    @Override
    public Stuff findById(int id) throws DataAccessException {
        Query query = this.em.createQuery("SELECT stuff FROM Stuff stuff WHERE stuff.id =:id");
        query.setParameter("id", id);
        return (Stuff) query.getSingleResult();
    }

    @Override
    public void save(Stuff stuff) throws DataAccessException {
        if (stuff.getId() == null) {
            this.em.persist(stuff);
        } else {
            this.em.merge(stuff);
        }
    }

    @Override
    public List<Stuff> getStuff() throws DataAccessException {
        Query query = this.em.createQuery("SELECT DISTINCT stuff FROM Stuff stuff");
        return query.getResultList();
    }
}
