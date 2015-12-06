package ua.com.diakin.medicalclinic.model;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

import javax.persistence.*;
import java.util.*;

/**
 * Created by Admin on 18.11.2015.
 */
@Entity
@Table(name= "patients")
public class Patient extends Person{

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient", fetch = FetchType.EAGER)
    private Set<Record> records;
/*

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient", fetch = FetchType.EAGER)
    private Set<Stuff> stuff;
*/

    protected Set<Record> getRecordsInternal() {
        if (this.records == null) {
            this.records = new HashSet<>();
        }
        return this.records;
    }

    public void addRecord(Record record) {
        getRecordsInternal().add(record);
        record.setPatient(this);
    }

    public List<Record> getRecords() {
        List<Record> sortedRecords = new ArrayList<>(getRecordsInternal());
//        PropertyComparator.sort(sortedRecords, new MutableSortDefinition("date", false, false));
        return Collections.unmodifiableList(sortedRecords);
    }

}
