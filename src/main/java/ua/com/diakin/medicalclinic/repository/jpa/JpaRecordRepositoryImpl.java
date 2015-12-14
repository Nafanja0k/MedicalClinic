package ua.com.diakin.medicalclinic.repository.jpa;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.diakin.medicalclinic.model.Record;
import ua.com.diakin.medicalclinic.model.Stuff;
import ua.com.diakin.medicalclinic.model.UploadFile;
import ua.com.diakin.medicalclinic.repository.RecordRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 20.11.2015.
 */
@Repository
public class JpaRecordRepositoryImpl implements RecordRepository {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @PersistenceContext
    private EntityManager em;

    @Override
    public Collection<Record> findRecordsByPatientId(int id) throws DataAccessException {
        Query query = this.em.createQuery("SELECT record FROM Record record WHERE record.patient.id = :id");
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public Record findRecordById(int id) throws DataAccessException {
        Query query = this.em.createQuery("SELECT record FROM Record record WHERE record.id = :id");
        query.setParameter("id", id);
        return (Record) query.getSingleResult();
    }
    @Override
    public void save(Record record) throws DataAccessException {
        if (record.getId() == null) {
            this.em.persist(record);
        } else {
            this.em.merge(record);
        }
    }

    @Override
    public List<Stuff> stuff() throws DataAccessException {
        Map<String, Object> params = new HashMap<>();
        return this.namedParameterJdbcTemplate.query(
                "SELECT id, last_name FROM stuff ORDER BY lastname",
                params,
                BeanPropertyRowMapper.newInstance(Stuff.class));

    }


}
