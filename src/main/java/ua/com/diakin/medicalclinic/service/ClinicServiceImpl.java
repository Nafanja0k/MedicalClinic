package ua.com.diakin.medicalclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.diakin.medicalclinic.model.Patient;
import ua.com.diakin.medicalclinic.repository.PatientRepository;

import java.util.Collection;

/**
 * Created by Admin on 18.11.2015.
 */
@Service
public class ClinicServiceImpl implements ClinicService{

    private PatientRepository patientRepository;

    @Autowired
    public ClinicServiceImpl(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Patient findOwnerById(int id) throws DataAccessException {
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
}
