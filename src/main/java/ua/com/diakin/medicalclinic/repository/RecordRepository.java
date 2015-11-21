package ua.com.diakin.medicalclinic.repository;

import org.springframework.dao.DataAccessException;
import ua.com.diakin.medicalclinic.model.Record;

import java.util.Collection;

/**
 * Created by Admin on 20.11.2015.
 */
public interface RecordRepository {

    Collection<Record> findByPatientId(int id) throws DataAccessException;
    void save(Record record) throws DataAccessException;

}
