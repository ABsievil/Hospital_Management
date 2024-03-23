package hcmut.hospitalmanagement.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import hcmut.hospitalmanagement.models.ImageData;
import hcmut.hospitalmanagement.models.ResponseObject;
import hcmut.hospitalmanagement.services.ImageService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/Images")
public class ImageController {
    @Autowired
    private ImageService service;


    @GetMapping("")
    public List<ImageData> getAllImages() {
        return service.getAllImages();
    }
    
    @GetMapping("/getByID/{id}")
    public ResponseEntity<?> downloadImage(@PathVariable Long id) {
        // Optional<ImageData> image = service.getByID(id);
        // String fileName = image.get().getName();
        byte[] imageData = service.downloadImageByID(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);

    }
    
    @GetMapping("/getByName/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName) {
        byte[] imageData = service.downloadImageByName(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);

    }

    @PostMapping("/insert")
    public ResponseEntity<ResponseObject> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        ImageData uploadImage = service.uploadImage(file);
        return (uploadImage != null)? ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("OK", "Upload Image Successfully", uploadImage))
                : ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(new ResponseObject("Failed", "Upload Image Failed", null));
    }

    // @PostMapping("/insert")
    // public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
    //     String uploadImage = service.uploadImage(file);
    //     return ResponseEntity.status(HttpStatus.OK)
    //             .body(uploadImage);
    // }
}
