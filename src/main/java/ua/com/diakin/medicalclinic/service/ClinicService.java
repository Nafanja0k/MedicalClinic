package ua.com.diakin.medicalclinic.service;
import java.util.Collection;

import org.springframework.dao.DataAccessException;
import ua.com.diakin.medicalclinic.model.UploadFile;
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

    //UploadFile
    UploadFile findFileById(int id) throws DataAccessException;
    Collection<UploadFile> findFilesByRecordId(int id) throws DataAccessException;
    void saveFile(UploadFile uploadFile) throws DataAccessException;

}
