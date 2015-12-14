package ua.com.diakin.medicalclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.diakin.medicalclinic.model.UploadFile;
import ua.com.diakin.medicalclinic.model.Patient;
import ua.com.diakin.medicalclinic.model.Record;
import ua.com.diakin.medicalclinic.model.Stuff;
import ua.com.diakin.medicalclinic.repository.UploadFileRepository;
import ua.com.diakin.medicalclinic.repository.RecordRepository;
import ua.com.diakin.medicalclinic.repository.PatientRepository;
import ua.com.diakin.medicalclinic.repository.StuffRepository;

import java.util.Collection;

/**
 * Created by Admin on 18.11.2015.
 */
@Service
public class ClinicServiceImpl implements ClinicService{

    private PatientRepository patientRepository;
    private StuffRepository stuffRepository;
    private RecordRepository recordRepository;
    private UploadFileRepository uploadFileRepository;

    @Autowired
    public ClinicServiceImpl(PatientRepository patientRepository,
                             StuffRepository stuffRepository,
                             RecordRepository recordRepository,
                             UploadFileRepository uploadFileRepository){
        this.patientRepository = patientRepository;
        this.stuffRepository = stuffRepository;
        this.recordRepository = recordRepository;
        this.uploadFileRepository = uploadFileRepository;
    }

    //Patient
    @Override
    @Transactional(readOnly = true)
    public Patient findPatientById(int id) throws DataAccessException {
        return patientRepository.findById(id);
    }

    @Override
    @Transactional
    public void savePatient(Patient patient) throws DataAccessException {
        patientRepository.save(patient);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Patient> findPatientByLastName(String lastName) throws DataAccessException {
        return patientRepository.findByLastName(lastName);
    }

    //Stuff
    @Override
    @Transactional(readOnly = true)
    public Stuff findStuffById(int id) throws DataAccessException {
        return stuffRepository.findById(id);
    }

    @Override
    @Transactional
    public void saveStuff(Stuff stuff) throws DataAccessException {
        stuffRepository.save(stuff);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Stuff> findStuffByLastName(String lastName) throws DataAccessException {
        return stuffRepository.findByLastName(lastName);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Record> findRecordsByPatientId(int id) throws DataAccessException {
        return recordRepository.findRecordsByPatientId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Record findRecordById(int id) throws DataAccessException {
        return recordRepository.findRecordById(id);
    }

    @Override
    @Transactional
    public void saveRecord(Record record) throws DataAccessException {
        recordRepository.save(record);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Stuff> getStuff() throws DataAccessException {
        return stuffRepository.getStuff();
    }

    @Override
    @Transactional(readOnly = true)
    public UploadFile findFileById(int id) throws DataAccessException {
        return uploadFileRepository.findFileById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<UploadFile> findFilesByRecordId(int id) throws DataAccessException {
        return uploadFileRepository.getFilesByRecordId(id);
    }

    @Override
    @Transactional
    public void saveFile(UploadFile uploadFile) throws DataAccessException {
        uploadFileRepository.save(uploadFile);
    }
}
