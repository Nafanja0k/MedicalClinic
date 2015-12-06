package ua.com.diakin.medicalclinic.repository.jpa;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import ua.com.diakin.medicalclinic.model.File;
import ua.com.diakin.medicalclinic.repository.FileRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Admin on 03.12.2015.
 */
@Repository
public class JpaFileRepositoryImpl implements FileRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public File findFileById(int id) throws DataAccessException {
        return null;
    }

    @Override
    public List<File> findFileByRecordId(int id) throws DataAccessException {
        return null;
    }

    @Override
    public void save(File file) throws DataAccessException {
        if (file.getId() == null) {
            this.em.persist(file);
        } else {
            this.em.merge(file);
        }

    }
}
