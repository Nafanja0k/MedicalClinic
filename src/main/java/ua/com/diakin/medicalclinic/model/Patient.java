package ua.com.diakin.medicalclinic.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Admin on 18.11.2015.
 */
@Entity
@Table(name= "patients")
public class Patient extends Person{

}
