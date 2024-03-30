package hcmut.hospitalmanagement.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hcmut.hospitalmanagement.models.MedicineList;
import hcmut.hospitalmanagement.models.ResponseObject;
import hcmut.hospitalmanagement.services.MedicineListService;

@RestController
@RequestMapping("/api/v1/MedicineList")
public class MedicineListController {
    @Autowired
    private MedicineListService medicineListService;

    // Get All MedicineList
    @GetMapping("/getAllMedicineList")
    public List<MedicineList> getALlMedicineList() {
        return medicineListService.getAllMedicineList();
    }

    // Get MedicineList by Doctor
    @GetMapping("/getMedicineListByDoctor/{doctorId}")
    public List<MedicineList> getMedicineListByDoctor(@PathVariable Long doctorId) {
        return medicineListService.getMedineListByDoctorId(doctorId);
    }

    // Get MedicineList by Patient
    @GetMapping("/getMedicineListByPatient/{patientId}")
    public List<MedicineList> getMedicineListByPatient(@PathVariable Long patientId) {
        return medicineListService.getMedicineListByPatientId(patientId);
    }

    // Get MedicineList By Date
    @GetMapping("/getMedicineListByDate")
    public List<MedicineList> getMedicineListByDate(@RequestParam("date") 
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date ) {
        return medicineListService.getMedicineListByDate(date);
    }

    // Get MedicineList between startDate and endDate
    public List<MedicineList> getMedicineListBetweenDate(
        @RequestParam("start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
        @RequestParam("end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return medicineListService.getMedicineListBetweenDate(startDate, endDate);
    }

    // Insert new MedicineList
    @PostMapping("/insertMedicineList")
    public ResponseEntity<ResponseObject> insertMedicineList(@RequestBody MedicineList medicineList) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("OK", "Insert MedicineList successfully", 
                                        medicineListService.saveMedicineList(medicineList)));
    }

    // Update MedicineList
    @PutMapping("/updateMedicineList/{id}")
    public ResponseEntity<ResponseObject> updateMedicineList(@PathVariable Long id, @RequestBody MedicineList updatedMedicineList) {
        MedicineList foundMedicineList = medicineListService.getMedicineById(id).orElse(null);
        if (foundMedicineList == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseObject("Failed", "Cannot find MedicineList with id: " + id, null));
        }
        updatedMedicineList.setId(foundMedicineList.getId());
        return ResponseEntity.status(HttpStatus.OK)
            .body(new ResponseObject("OK", "Update MedicineList successfully with id: " + id, 
                                    medicineListService.saveMedicineList(updatedMedicineList)));
    }

    // Delete MedicineList
    @DeleteMapping("/deleteMedicineList/{id}")
    public ResponseEntity<ResponseObject> deleteMedicineList(@PathVariable Long id) {
        MedicineList foundMedicineList = medicineListService.getMedicineById(id).orElse(null);
        if (foundMedicineList == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseObject("Failed", "Cannot find MedicineList with id: " + id, null));
        }
        medicineListService.deleteMedicineListById(id);
        return ResponseEntity.status(HttpStatus.OK)
            .body(new ResponseObject("OK", "Delete MedicineList successfully with id: " + id, null));
    }
}
