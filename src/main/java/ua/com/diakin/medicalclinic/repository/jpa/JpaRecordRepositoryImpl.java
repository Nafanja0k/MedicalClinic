package ua.com.diakin.medicalclinic.repository.jpa;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import ua.com.diakin.medicalclinic.model.Record;
import ua.com.diakin.medicalclinic.repository.RecordRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

/**
 * Created by Admin on 20.11.2015.
 */
@Repository
public class JpaRecordRepositoryImpl implements RecordRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Collection<Record> findByPatientId(int id) throws DataAccessException {
        Query query = this.em.createQuery("SELECT record FROM Record r where r.record.id= :id");
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public void save(Record record) throws DataAccessException {
        if (record.getId() == null) {
            this.em.persist(record);
        } else {
            this.em.merge(record);
        }


    }
}
