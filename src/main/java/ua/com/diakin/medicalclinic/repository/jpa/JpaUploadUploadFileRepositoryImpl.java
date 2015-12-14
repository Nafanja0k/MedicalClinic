package ua.com.diakin.medicalclinic.repository.jpa;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import ua.com.diakin.medicalclinic.model.Record;
import ua.com.diakin.medicalclinic.model.UploadFile;
import ua.com.diakin.medicalclinic.repository.UploadFileRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

/**
 * Created by Admin on 03.12.2015.
 */
@Repository
public class JpaUploadUploadFileRepositoryImpl implements UploadFileRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public UploadFile findFileById(int id) throws DataAccessException {
        Query query = this.em.createQuery("SELECT uplodedFile FROM UploadFile uplodedFile WHERE uplodedFile.id = :id");
        query.setParameter("id", id);
        return (UploadFile) query.getSingleResult();
    }

    @Override
    public Collection<UploadFile> getFilesByRecordId(int id) throws DataAccessException {
        Query query = this.em.createQuery("SELECT uploadedFile FROM UploadFile uploadedFile WHERE uploadedFile.record.id = :id");
        query.setParameter("id", id);
        return query.getResultList();

    }

    @Override
    public void save(UploadFile uploadFile) throws DataAccessException {
        if (uploadFile.getId() == null) {
            this.em.persist(uploadFile);
        } else {
            this.em.merge(uploadFile);
        }

    }
}
