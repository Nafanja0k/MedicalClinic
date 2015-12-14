package ua.com.diakin.medicalclinic.model;

import org.hibernate.boot.registry.selector.StrategyRegistration;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.internal.util.StringHelper;

import javax.persistence.*;
import java.sql.Blob;

/**
 * Created by Admin on 03.12.2015.
 */
@Entity
@Table(name = "files")
public class UploadFile extends BaseEntity {

    @Column(name = "comment")
    protected String comment;

    @Column(name = "file_name")
    protected String fileName;

    @Column(name = "data")
    protected byte[] data;

    @Column(name = "content_type")
    protected String contentType;

    @ManyToOne
    protected Record record;


    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] image) {
        this.data = image;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
