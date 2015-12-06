package ua.com.diakin.medicalclinic.repository;

import org.springframework.dao.DataAccessException;
import ua.com.diakin.medicalclinic.model.BaseEntity;
import ua.com.diakin.medicalclinic.model.File;

import java.util.List;

/**
 * Created by Admin on 03.12.2015.
 */
public interface FileRepository {

    /**
     * Retrieve an <code>File</code> from the datastore by id.
     *
     * @param id the id to search for
     * @return the <code>File</code> if found
     * @throws org.springframework.dao.DataRetrievalFailureException if not found
     */
    File findFileById(int id) throws DataAccessException;

    /**
     * Retrieve an <code>File</code> from the datastore by <code>Record<code/> id.
     *
     * @param id the id of Record to search for
     * @return the <code>List<File></File></code> if found
     * @throws org.springframework.dao.DataRetrievalFailureException if not found
     */
    List<File> findFileByRecordId(int id) throws DataAccessException;

    /**
     * Save an <code>File</code> to the data store, either inserting or updating it.
     * @param file <code>File<code/>
     * @see BaseEntity#isNew
     */
    void save(File file) throws DataAccessException;
}
