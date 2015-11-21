package ua.com.diakin.medicalclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.com.diakin.medicalclinic.model.Patient;
import ua.com.diakin.medicalclinic.service.ClinicService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;

/**
 * Created by Admin on 18.11.2015.
 */
@Controller
public class PatientController {
    private final ClinicService clinicService;

    @Autowired
    public PatientController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping(value = "/patients/new", method = RequestMethod.GET)
    public  String initCreationForm(Map<String, Object> model){
        Patient patient = new Patient();
        model.put("patient", patient);
        return  "patients/createOrUpdatePatientForm";
    }

    @RequestMapping(value = "/patients/new", method = RequestMethod.POST)
    public String processCreationForm(@Valid Patient patient, BindingResult result){
        if (result.hasErrors()) {
            return "patients/createOrUpdatePatientForm";
        } else {
            this.clinicService.savePatient(patient);
            return  "redirect:/patients/" + patient.getId();
        }
    }

    @RequestMapping(value = "/patients/find", method = RequestMethod.GET)
    public String initFindForm(Map<String, Object> model) {
        model.put("patient", new Patient());
        return  "patients/findPatients";
    }

    @RequestMapping(value = "/patients", method = RequestMethod.GET)
    public String processFindForm(Patient patient, BindingResult result, Map<String, Object> model) {
        // allow parameterless GET request fo /patients to return all records
        if (patient.getLastName() == null) {
            patient.setLastName(""); //empty string signifies broadest possible search
        }

        //find patients by last name
        Collection<Patient> results = this.clinicService.findPatientByLastName(patient.getLastName());
        if (results.isEmpty()) {
            //no answers found
            result.rejectValue("lastName", "notFound", "not found");
            return "patients/findPatients";
        } else if (results.size() == 1) {
            // 1 patient found
            patient = results.iterator().next();
            return "redirect:/patients/" + patient.getId();
        } else {
            //multiple patients found
            model.put("selections", results);
            return "patients/patientsList";
        }
    }

    @RequestMapping(value = "/patients/{patientId}/edit", method = RequestMethod.GET)
    public String initUpdatePatientForm(@PathVariable("patientId") int patientId, Model model) {
        Patient patient = this.clinicService.findPatientById(patientId);
        model.addAttribute(patient);
        return "patients/createOrUpdatePatientForm";
    }

    @RequestMapping(value = "/patients/{patientId}/edit", method = RequestMethod.POST)
    public String processUpdatePatientForm(@Valid Patient patient, BindingResult result, @PathVariable("patientId") int patientId) {
        if (result.hasErrors()) {
            return "patients/createOrUpdatePatientForm";
        } else {
            patient.setId(patientId);
            this.clinicService.savePatient(patient);
            return "redirect:/patients/{patientId}";
        }
    }

    /**
     * Custom handler for displaying an patient.
     *
     * @param patientId the ID of the patient to display
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping("patients/{patientId}")
    public ModelAndView showPatient(@PathVariable("patientId") int patientId) {
        ModelAndView mav = new ModelAndView("patients/patientDetails");
        mav.addObject(this.clinicService.findPatientById(patientId));
        return mav;
    }
}
