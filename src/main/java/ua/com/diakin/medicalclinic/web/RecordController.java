package ua.com.diakin.medicalclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ua.com.diakin.medicalclinic.model.Patient;
import ua.com.diakin.medicalclinic.model.Record;
import ua.com.diakin.medicalclinic.model.Stuff;
import ua.com.diakin.medicalclinic.service.ClinicService;

import javax.validation.Valid;
import java.util.Collection;
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

    // Spring MVC calls method loadPetWithVisit(...) before initNewRecordForm is called
    @ModelAttribute("record")
    public Record loadPatientWithRecord(@PathVariable("patientId") int patientId) {
        Patient patient = this.clinicService.findPatientById(patientId);
        Record record = new Record();
        patient.addRecord(record);
        return record;
    }

    @ModelAttribute("stuff")
    public Collection<Stuff> populateStuffList() {
        return this.clinicService.getStuff();
    }

    // Spring MVC calls method loadPetWithVisit(...) before initNewRecordForm is called
    @RequestMapping(value = "/patients/{patientId}/records/new", method = RequestMethod.GET)
    public String initNewRecordForm(@PathVariable("patientId") int patientId, Map<String, Object> model) {
        return "patients/createOrUpdateRecordForm";
    }

    // Spring MVC calls method loadPetWithVisit(...) before processNewRecordForm is called
    @RequestMapping(value = "/patients/{patientId}/records/new", method = RequestMethod.POST)
    public String processNewRecordForm(@Valid Record record, Stuff stuff, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.put("stuff", stuff);
            return "patients/createOrUpdateRecordForm";
        } else {
            record.setStuff(stuff);
            this.clinicService.saveRecord(record);
            return "redirect:/patients/{patientId}";
        }
    }


    @RequestMapping(value = "/patients/{patientId}/records", method = RequestMethod.GET)
    public String showRecords(@PathVariable("patientId") int patientId, Map<String, Object> model) {
        model.put("records", this.clinicService.findPatientById(patientId).getRecords());
        return "recordsList";
    }

}

