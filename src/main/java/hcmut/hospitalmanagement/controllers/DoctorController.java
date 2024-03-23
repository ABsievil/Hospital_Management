package hcmut.hospitalmanagement.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import hcmut.hospitalmanagement.models.Doctor;
import hcmut.hospitalmanagement.models.ImageData;
import hcmut.hospitalmanagement.models.ResponseObject;
import hcmut.hospitalmanagement.services.DoctorService;
import hcmut.hospitalmanagement.services.ImageService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/Doctors")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ImageService imageService;

    // Get All Doctors
    @GetMapping("")
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    // Get Doctor By ID
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getDoctorById(@PathVariable Long id) {
        Optional<Doctor> foundDoctor = doctorService.getDoctorById(id);
        return foundDoctor.isPresent()
                ? ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject("ok", "Query Doctor Successfully", foundDoctor))
                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseObject("failed", "Cannot find Doctor with id = " + id, ""));
    }

    @GetMapping("/{doctorId}/getImage")
    public ResponseEntity<?> getDoctorImage(@PathVariable Long doctorId) {
        ImageData image = doctorService.getDoctorImage(doctorId);
        if (image != null) {
            Long imageId = image.getId();
            byte[] imageData = imageService.downloadImageByID(imageId);
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.valueOf("image/png"))
                    .body(imageData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Insert New Doctor
    @PostMapping("/insert")
    public ResponseEntity<ResponseObject> insertDoctor(
            @RequestBody Doctor newDoctor) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert Doctor Successfully", doctorService.saveDoctor(newDoctor)));
    }

    @PostMapping("/{doctorId}/insertImageWithID/{imageDataId}")
    public ResponseEntity<String> setDoctorImageWithID(@PathVariable Long doctorId, @PathVariable Long imageDataId) {
        doctorService.setDoctorImage(doctorId, imageDataId);
        return ResponseEntity.status(HttpStatus.OK).body("Doctor image set successfully");
    }

    

    @PostMapping("/{doctorID}/insertImage")
    public ResponseEntity<ResponseObject> setDoctorImage(@PathVariable Long doctorID, @RequestParam("image") MultipartFile file) throws IOException {
        Optional<Doctor> foundDoctor = doctorService.getDoctorById(doctorID);
        if (!foundDoctor.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                    .body(new ResponseObject("Failed", "Cannot find Doctor with id: " + doctorID, null));
        }
        ImageData imageData = imageService.uploadImage(file);
        Doctor doctor = foundDoctor.get();
        if (imageData == null) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
            .body(new ResponseObject("Failed", "Image is Empty", null));
        }
        
        doctor.setImage(imageData);
        imageData.setDoctor(doctor);

        doctorService.saveDoctor(doctor);
        imageService.saveImage(imageData);
        return ResponseEntity.status(HttpStatus.OK)
        .body(new ResponseObject("OK", "Insert image successfully for doctor with id: " + doctorID, null));
    }

}
