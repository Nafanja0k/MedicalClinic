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
import ua.com.diakin.medicalclinic.model.Stuff;
import ua.com.diakin.medicalclinic.service.ClinicService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;

/**
 * Created by Admin on 18.11.2015.
 */
@Controller
public class StuffController {
    private final ClinicService clinicService;

    @Autowired
    public StuffController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping(value = "/stuff/new", method = RequestMethod.GET)
    public  String initCreationForm(Map<String, Object> model){
        Stuff stuff = new Stuff();
        model.put("stuff", stuff);
        return  "stuff/createOrUpdateStuffForm";
    }

    @RequestMapping(value = "/stuff/new", method = RequestMethod.POST)
    public String processCreationForm(@Valid Stuff stuff, BindingResult result){
        if (result.hasErrors()) {
            return "stuff/createOrUpdateStuffForm";
        } else {
            this.clinicService.saveStuff(stuff);
            return  "redirect:/stuff/" + stuff.getId();
        }
    }

    @RequestMapping(value = "/stuff/find", method = RequestMethod.GET)
    public String initFindForm(Map<String, Object> model) {
        model.put("stuff", new Stuff());
        return  "stuff/findStuff";
    }

    @RequestMapping(value = "/stuff", method = RequestMethod.GET)
    public String processFindForm(Stuff stuff, BindingResult result, Map<String, Object> model) {
        // allow parameterless GET request fo /owners to return all records
        if (stuff.getLastName() == null) {
            stuff.setLastName(""); //empty string signifies broadest possible search
        }

        //find stuff by last name
        Collection<Stuff> results = this.clinicService.findStuffByLastName(stuff.getLastName());
        if (results.isEmpty()) {
            //no answers found
            result.rejectValue("lastName", "notFound", "not found");
            return "stuff/findStuff";
        } else if (results.size() == 1) {
            // 1 stuff found
            stuff = results.iterator().next();
            return "redirect:/stuff/" + stuff.getId();
        } else {
            //multiple stuff found
            model.put("selections", results);
            return "stuff/stuffList";
        }
    }

    @RequestMapping(value = "/stuff/{stuffId}/edit", method = RequestMethod.GET)
    public String initUpdateStuffForm(@PathVariable("stuffId") int stuffId, Model model) {
        Stuff stuff = this.clinicService.findStuffById(stuffId);
        model.addAttribute(stuff);
        return "stuff/createOrUpdateStuffForm";
    }

    @RequestMapping(value = "/stuff/{stuffId}/edit", method = RequestMethod.POST)
    public String processUpdateStuffForm(@Valid Stuff stuff, BindingResult result, @PathVariable("stuffId") int stuffId) {
        if (result.hasErrors()) {
            return "stuff/createOrUpdateStuffForm";
        } else {
            stuff.setId(stuffId);
            this.clinicService.saveStuff(stuff);
            return "redirect:/stuff/{stuffId}";
        }
    }

    /**
     * Custom handler for displaying an owner.
     *
     * @param stuffId the ID of the owner to display
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping("stuff/{stuffId}")
    public ModelAndView showStuff(@PathVariable("stuffId") int stuffId) {
        ModelAndView mav = new ModelAndView("stuff/stuffDetails");
        mav.addObject(this.clinicService.findStuffById(stuffId));
        return mav;
    }
}
