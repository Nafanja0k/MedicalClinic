package ua.com.diakin.medicalclinic.repository;

import org.springframework.dao.DataAccessException;
import ua.com.diakin.medicalclinic.model.BaseEntity;
import ua.com.diakin.medicalclinic.model.UploadFile;

import java.util.Collection;
import java.util.List;

/**
 * Created by Admin on 03.12.2015.
 */
public interface UploadFileRepository {

    /**
     * Retrieve an <code>UploadFile</code> from the datastore by id.
     *
     * @param id the id to search for
     * @return the <code>UploadFile</code> if found
     * @throws org.springframework.dao.DataRetrievalFailureException if not found
     */
    UploadFile findFileById(int id) throws DataAccessException;

    /**
     * Retrieve an <code>UploadFile</code> from the datastore by <code>Record<code/> id.
     *
     * @param id the id of Record to search for
     * @return the <code>List<UploadFile></UploadFile></code> if found
     * @throws org.springframework.dao.DataRetrievalFailureException if not found
     */
    Collection<UploadFile> getFilesByRecordId(int id) throws DataAccessException;

    /**
     * Save an <code>UploadFile</code> to the data store, either inserting or updating it.
     * @param uploadFile <code>UploadFile<code/>
     * @see BaseEntity#isNew
     */
    void save(UploadFile uploadFile) throws DataAccessException;
}
