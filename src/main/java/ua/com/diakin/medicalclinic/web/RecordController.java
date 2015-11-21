package ua.com.diakin.medicalclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ua.com.diakin.medicalclinic.model.Patient;
import ua.com.diakin.medicalclinic.model.Record;
import ua.com.diakin.medicalclinic.service.ClinicService;

import javax.validation.Valid;
import java.util.Map;

/**
 * Created by Admin on 18.11.2015.
 */
@Controller
public class RecordController {
    private final ClinicService clinicService;

    @Autowired
    public RecordController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    // Spring MVC calls method loadPetWithVisit(...) before initNewVisitForm is called
    @ModelAttribute("patientId")
    public Record loadPatientWithRecord(@PathVariable("patientId") int patientId) {
        Patient patient = this.clinicService.findPatientById(patientId);
        Record record = new Record();
        patient.addRecord(record);
        return record;
    }


    @RequestMapping(value = "/patients/*/records/new", method = RequestMethod.GET)
    public  String initCreationForm(Map<String, Object> model){
        return  "patients/createOrUpdateRecordForm";
    }

    // Spring MVC calls method loadPetWithVisit(...) before processNewVisitForm is called
    @RequestMapping(value = "/patients/{patientId}/records/new", method = RequestMethod.POST)
    public String processNewVisitForm(@Valid Record record, BindingResult result) {
        if (result.hasErrors()) {
            return "patients/createOrUpdateRecordForm";
        } else {
            this.clinicService.saveRecord(record);
            return "redirect:/patients/{patientId}";
        }
    }

    @RequestMapping(value = "/patients/{petientId}/records", method = RequestMethod.GET)
    public String showVisits(@PathVariable int patientId, Map<String, Object> model) {
        model.put("records", this.clinicService.findPatientById(patientId).getRecords());
        return "recordsList";
    }

}

