package ua.com.diakin.medicalclinic.service;
import java.util.Collection;
import org.springframework.dao.DataAccessException;
import ua.com.diakin.medicalclinic.model.Patient;

/**
 * Mostly used as a facade so all controllers have a single point of entry
 *
 * @author Michael Isvy
 */
public interface ClinicService {

    Patient findOwnerById(int id) throws DataAccessException;

    void savePatient(Patient patient) throws DataAccessException;

    Collection<Patient> findPatientByLastName(String lastName) throws DataAccessException;

}
