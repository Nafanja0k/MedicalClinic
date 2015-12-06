package ua.com.diakin.medicalclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
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

    // Spring MVC calls method loadPatientWithRecords(...) before initNewRecordForm is called
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

    // Spring MVC calls method loadPatientWithVisit(...) before initNewRecordForm is called
    @RequestMapping(value = "/patients/{patientId}/records/new", method = RequestMethod.GET)
    public String initNewRecordForm(@PathVariable("patientId") int patientId, Map<String, Object> model) {
        return "records/createOrUpdateRecordForm";
    }

    // Spring MVC calls method loadPatientWithRecords(...) before processNewRecordForm is called
    @RequestMapping(value = "/patients/{patientId}/records/new", method = RequestMethod.POST)
    public String processNewRecordForm(@Valid Record record, Stuff stuff, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.put("stuff", stuff);
            return "records/createOrUpdateRecordForm";
        } else {
            record.setStuff(stuff);
            this.clinicService.saveRecord(record);
            return "redirect:/patients/{patientId}";
        }
    }

    @RequestMapping(value = "/patients/{patientId}/records", method = RequestMethod.GET)
    public String showRecords(@PathVariable("patientId") int patientId, Map<String, Object> model) {
        model.put("records", this.clinicService.findPatientById(patientId).getRecords());
        return "records/recordsList";
    }
    // Spring MVC calls method loadPatientWithVisit(...) before initNewRecordForm is called
    @RequestMapping(value = "/patients/{patientId}/records/{recordId}/edit", method = RequestMethod.GET)
    public String initRecordForm(@PathVariable("patientId") int patientId, @PathVariable("recordId") int recordId, Model model) {
        Record record = this.clinicService.findRecordById(recordId);
        model.addAttribute(record);
        return "records/createOrUpdateRecordForm";
    }

    // Spring MVC calls method loadPatientWithRecords(...) before processNewRecordForm is called
    @RequestMapping(value = "/patients/{patientId}/records/{recordId}/edit", method = RequestMethod.POST)
    public String processRecordForm(@Valid Record record, Stuff stuff, BindingResult result, ModelMap model, @PathVariable(value = "recordId") int recordId) {
        if (result.hasErrors()) {
            return "records/createOrUpdateRecordForm";
        } else {
            record.setId(recordId);
            record.setStuff(stuff);
            this.clinicService.saveRecord(record);
            return "redirect:/patients/{patientId}";
        }
    }

}

