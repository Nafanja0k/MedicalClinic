package ua.com.diakin.medicalclinic.repository.jpa;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.OpenSessionInViewFilter;
import org.springframework.stereotype.Repository;
import ua.com.diakin.medicalclinic.model.Patient;
import ua.com.diakin.medicalclinic.repository.PatientRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

/**
 * Created by Admin on 18.11.2015.
 */
@Repository
public class JpaPatientRepositoryImpl implements PatientRepository{

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
    public Collection<Patient> findByLastName(String lastName) throws DataAccessException {
        Query query = this.em.createQuery("SELECT DISTINCT patient FROM Patient patient WHERE patient.lastName LIKE :lastName");
        query.setParameter("lastName", lastName + "%");
        return query.getResultList();
    }

    @Override
    public Patient findById(int id) throws DataAccessException {
        Query query = this.em.createQuery("SELECT patient FROM Patient patient WHERE patient.id =:id");
        query.setParameter("id", id);
        return (Patient) query.getSingleResult();
    }

    @Override
    public void save(Patient patient) throws DataAccessException {
        if (patient.getId() == null) {
            this.em.persist(patient);
        } else {
            this.em.merge(patient);
        }

    }
}
