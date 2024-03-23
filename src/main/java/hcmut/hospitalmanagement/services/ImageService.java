package hcmut.hospitalmanagement.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import hcmut.hospitalmanagement.models.ImageData;
import hcmut.hospitalmanagement.repositories.ImageRepository;
import hcmut.hospitalmanagement.utils.ImageUtils;

@Service
public class ImageService {
    @Autowired
    private ImageRepository repository;

    public ImageData saveImage(ImageData imageData) {
        return repository.save(imageData);
    }

    public List<ImageData> getAllImages() {
        return repository.findAll();
    }

    public Optional<ImageData> getByID(Long id) {
        return repository.findById(id);
    }

    public ImageData uploadImage(MultipartFile file) throws IOException {
        ImageData imageData = repository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        if (imageData != null) {
            return imageData;
        }
        return null;
    }

    // public String uploadImage(MultipartFile file) throws IOException {
    //     ImageData imageData = repository.save(ImageData.builder()
    //             .name(file.getOriginalFilename())
    //             .type(file.getContentType())
    //             .imageData(ImageUtils.compressImage(file.getBytes())).build());
    //     if (imageData != null) {
    //         return "file uploaded successfully: " + file.getOriginalFilename();
    //     }
    //     return null;
    // }

    public byte[] downloadImageByID(Long id) {
        Optional<ImageData> dbImageData = repository.findById(id);
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }

    public byte[] downloadImageByName(String fileName) {
        Optional<ImageData> dbImageData = repository.findByName(fileName);
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }
}
