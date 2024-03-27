package hcmut.hospitalmanagement.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import hcmut.hospitalmanagement.models.Doctor;
import hcmut.hospitalmanagement.models.ImageData;
import hcmut.hospitalmanagement.models.PersonalInformation;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1/Doctors")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ImageService imageService;

    // Get All Doctors
    @GetMapping("/getAll")
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    // Get All Active Doctors (Is Working in Hospital)
    @GetMapping("/getActive")
    public List<Doctor> getActiveDoctors() {
        return doctorService.getActiveDoctors();
    }

    // Get Doctor By ID
    @GetMapping("/getDoctorById/{id}")
    public ResponseEntity<ResponseObject> getDoctorById(@PathVariable Long id) {
        Optional<Doctor> foundDoctor = doctorService.getDoctorById(id);
        return foundDoctor.isPresent()
                ? ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject("ok", "Query Doctor Successfully", foundDoctor))
                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseObject("failed", "Cannot find Doctor with id = " + id, ""));
    }

    // Get Doctor Image
    @GetMapping("/getDoctorImage/{doctorId}")
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

    // Get Doctors By Occupation ("Doctor", "Nurse",...)
    @GetMapping("/getDoctorByOccupation/{occupation}")
    public List<Doctor> getDoctorsByOccupation(@PathVariable String occupation) {
        return doctorService.getDoctorsByOccupation(occupation);
    }

    // Get Other Occupation that is not Doctor or Nurse
    @GetMapping("/getDoctorByOtherOccupation")
    public List<Doctor> getDoctorsWithOtherOccupations() {
        return doctorService.getDoctorsWithOtherOccupations();
    }

    // Insert New Doctor
    @PostMapping("/insert")
    public ResponseEntity<ResponseObject> insertDoctor(
            @RequestBody Doctor newDoctor) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert Doctor Successfully", doctorService.saveDoctor(newDoctor)));
    }

    // Insert Image to Doctor with doctorId and ImageId
    @PutMapping("/{doctorId}/updateImageWithID/{imageDataId}")
    public ResponseEntity<String> setDoctorImageWithID(@PathVariable Long doctorId, @PathVariable Long imageDataId) {
        doctorService.setDoctorImage(doctorId, imageDataId);
        return ResponseEntity.status(HttpStatus.OK).body("Doctor image set successfully");
    }

    // Insert Image to Doctor with doctorId
    @PutMapping("/updateDoctorImage/{doctorID}")
    public ResponseEntity<ResponseObject> setDoctorImage(@PathVariable Long doctorID,
            @RequestParam("image") MultipartFile file) throws IOException {
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

    // Update Doctor with new Doctor data
    @PutMapping("/updateDoctor/{id}")
    ResponseEntity<ResponseObject> updateDoctor(@PathVariable Long id, @RequestBody Doctor newDoctor) {
        Optional<Doctor> foundDoctor = doctorService.getDoctorById(id);
        if (!foundDoctor.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject("Failed", "Cannot find Doctor with id: " + id, null));
        }
        Doctor doctor = foundDoctor.get();
        doctor.setActive(newDoctor.getActive());
        doctor.setImage(newDoctor.getImage());
        doctor.setInformation(newDoctor.getInformation());
        doctor.setPatients(newDoctor.getPatients());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("OK", "Update Successfully for Doctor with id: " + id,
                        doctorService.saveDoctor(doctor)));
    }

    // Update Doctor with new Information
    @PutMapping("/updateDoctorInformation/{id}")
    ResponseEntity<ResponseObject> updateDoctorInformation(@PathVariable Long id,
            @RequestBody PersonalInformation newInfo) {
        Optional<Doctor> foundDoctor = doctorService.getDoctorById(id);
        if (!foundDoctor.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject("Failed", "Cannot find Doctor with id: " + id, null));
        }
        Doctor doctor = foundDoctor.get();
        doctor.setInformation(newInfo);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("OK", "Update Successfully for Doctor with id: " + id,
                        doctorService.saveDoctor(doctor)));
    }

    // Deactive Doctor because this doctor is no longer working for the hospital
    @PutMapping("/deactivateDoctor/{id}")
    ResponseEntity<ResponseObject> deactivateDoctor(@PathVariable Long id) {
        Optional<Doctor> foundDoctor = doctorService.getDoctorById(id);
        if (!foundDoctor.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject("Failed", "Cannot find Doctor with id: " + id, null));
        }
        Doctor doctor = foundDoctor.get();
        doctor.setActive(false);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("OK", "Update Successfully for Doctor with id: " + id,
                        doctorService.saveDoctor(doctor)));
    }
}
