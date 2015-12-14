package ua.com.diakin.medicalclinic.web;

import org.apache.commons.io.IOUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ua.com.diakin.medicalclinic.model.Patient;
import ua.com.diakin.medicalclinic.model.Record;
import ua.com.diakin.medicalclinic.model.UploadFile;
import ua.com.diakin.medicalclinic.service.ClinicService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

/**
 * Created by Admin on 28.11.2015.
 */
@Controller
public class UploadController {
    private final ClinicService clinicService;

    @Autowired
    public UploadController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @RequestMapping(value = "/uploadToRecord", method = RequestMethod.POST)
    public String handleFormUpload( @RequestParam("recordId") int recordId,
                                    @RequestParam("file") CommonsMultipartFile[] file,
                                    @RequestParam("currentUrl") String currentUrl) {
       if (file != null && file.length > 0) {
            for (MultipartFile aFile : file){

                    System.out.println("Saving file: " + aFile.getOriginalFilename());
                    UploadFile uploadFile = new UploadFile();
                    uploadFile.setFileName(aFile.getOriginalFilename());
                    uploadFile.setRecord(this.clinicService.findRecordById(recordId));
                    try {
                    uploadFile.setData(aFile.getBytes());
                    uploadFile.setContentType(aFile.getContentType());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.clinicService.saveFile(uploadFile);
            }
        }

        return "redirect:"+currentUrl;
    }

    @RequestMapping(value = "/download/{documentId}", method = RequestMethod.GET)
    public String download( @PathVariable("documentId") int documentId,
                            HttpServletResponse response) {

        UploadFile doc = this.clinicService.findFileById(documentId);
        try {
            response.setHeader("Content-Disposition", "inline;filename=\"" +doc.getFileName()+ "\"");
            OutputStream out = response.getOutputStream();
            response.setContentType(doc.getContentType());
            byte[] file = doc.getData();
            out.write(file);
            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
