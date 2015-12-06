package ua.com.diakin.medicalclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.diakin.medicalclinic.model.File;
import ua.com.diakin.medicalclinic.model.Patient;
import ua.com.diakin.medicalclinic.model.Record;
import ua.com.diakin.medicalclinic.model.Stuff;
import ua.com.diakin.medicalclinic.repository.FileRepository;
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
    private FileRepository fileRepository;

    @Autowired
    public ClinicServiceImpl(PatientRepository patientRepository,
                             StuffRepository stuffRepository,
                             RecordRepository recordRepository,
                             FileRepository fileRepository){
        this.patientRepository = patientRepository;
        this.stuffRepository = stuffRepository;
        this.recordRepository = recordRepository;
        this.fileRepository = fileRepository;
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
    public File findFileById(int id) throws DataAccessException {
        return fileRepository.findFileById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<File> findFileByRecordId(int id) throws DataAccessException {
        return fileRepository.findFileByRecordId(id);
    }

    @Override
    public void save(File file) throws DataAccessException {
        fileRepository.save(file);
    }
}
