package ua.com.diakin.medicalclinic.repository;

import org.springframework.dao.DataAccessException;
import ua.com.diakin.medicalclinic.model.Record;
import ua.com.diakin.medicalclinic.model.Stuff;
import ua.com.diakin.medicalclinic.model.UploadFile;

import java.util.Collection;
import java.util.List;

/**
 * Created by Admin on 20.11.2015.
 */
public interface RecordRepository {

    Collection<Record> findRecordsByPatientId(int id) throws DataAccessException;
    Record findRecordById(int id) throws DataAccessException;
    void save(Record record) throws DataAccessException;
    List<Stuff> stuff() throws DataAccessException;
}
