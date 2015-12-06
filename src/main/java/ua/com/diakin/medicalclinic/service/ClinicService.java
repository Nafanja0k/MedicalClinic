package ua.com.diakin.medicalclinic.service;
import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import ua.com.diakin.medicalclinic.model.File;
import ua.com.diakin.medicalclinic.model.Patient;
import ua.com.diakin.medicalclinic.model.Record;
import ua.com.diakin.medicalclinic.model.Stuff;

/**
 * Mostly used as a facade so all controllers have a single point of entry
 *
 * @author Michael Isvy
 */
public interface ClinicService {
    // Patient
    Patient findPatientById(int id) throws DataAccessException;
    void savePatient(Patient patient) throws DataAccessException;
    Collection<Patient> findPatientByLastName(String lastName) throws DataAccessException;

    // Stuff
    Stuff findStuffById(int id) throws DataAccessException;
    void saveStuff(Stuff stuff) throws DataAccessException;
    Collection<Stuff> findStuffByLastName(String lastName) throws DataAccessException;

    // Record
    Collection<Record> findRecordsByPatientId(int id) throws DataAccessException;
    Record findRecordById(int id) throws DataAccessException;
    void saveRecord(Record record) throws DataAccessException;
    Collection<Stuff> getStuff() throws DataAccessException;

    //File
    File findFileById(int id) throws DataAccessException;
    Collection<File> findFileByRecordId(int id) throws DataAccessException;
    void save(File file) throws DataAccessException;

}
