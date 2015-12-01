package ua.com.diakin.medicalclinic.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Admin on 28.11.2015.
 */
@Controller
public class UploadController {

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String showFormUpload() {
        return "/upload/upload";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String handleFormUpload(/*@RequestParam("name") String name,*/
                                   @RequestParam("file") MultipartFile file) {
        String fileName = null;
        if (!file.isEmpty()) {
            try {
                fileName = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                BufferedOutputStream buffStream =
                        new BufferedOutputStream(new FileOutputStream(new File("d:upload/" + fileName)));
                buffStream.write(bytes);
                buffStream.close();
                return "upload/uploadSuccess";
            } catch (Exception e) {
                return "You failed to upload " + fileName + ": " + e.getMessage();
            }
        } else {
            return "Unable to upload. File is empty.";
        }
    }
}