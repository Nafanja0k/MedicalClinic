package ua.com.diakin.medicalclinic.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

/**
 * Created by Admin on 20.11.2015.
 */
@Entity
@Table(name= "patient_records")
public class Record extends BaseEntity {

    @ManyToOne
    protected Patient patient;

    @ManyToOne(fetch = FetchType.EAGER)
    protected Stuff stuff;

    @OneToMany
    protected Collection<UploadFile> uploadedFiles;


    @Column(name = "patient_record_datetime")
    @NotEmpty
    protected String dateTime;

    @Column(name = "comment")
    protected String comment;


    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Stuff getStuff() {
        return stuff;
    }

    public void setStuff(Stuff stuff) {
        this.stuff = stuff;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Collection<UploadFile> getUploadedFiles() {
        return uploadedFiles;
    }

    public void setUploadedFiles(Set<UploadFile> uploadedFiles) {
        this.uploadedFiles = uploadedFiles;
    }
}

