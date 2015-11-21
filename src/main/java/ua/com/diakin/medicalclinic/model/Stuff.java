package ua.com.diakin.medicalclinic.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Admin on 18.11.2015.
 */
@Entity
@Table(name= "stuff")
public class Stuff extends Person{

    @Column(name = "job_title")
    @NotEmpty
    protected String jobTitle;


    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
