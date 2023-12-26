package com.omantourismapi.demo.Controller;

import com.omantourismapi.demo.Model.Photo;
import com.omantourismapi.demo.Services.PhotoInfoService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.xpath.XPathNamespace;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@RestController
@RequestMapping("/api/v1/photoContent")
public class PhotoContentController {

    @Autowired
    PhotoInfoService photoInfoService;

    @GetMapping(path = "/{Id}")
public ResponseEntity<byte[]> getContent(@PathVariable int Id) throws IOException {
       Photo photo= photoInfoService.getSpecificPhoto(Id);
       String photoName=photo.imgName;
    File data=new File("%s/%s.%s".formatted("./data", photoName,"jpg"));

    byte[] file=FileUtils.readFileToByteArray(data);
    return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(file);
}

@PostMapping("/Upload")
public ResponseEntity<String> uploadPhoto(@RequestParam MultipartFile file, @RequestParam String fileId) throws IOException {

        File destinationFile=new File("%s/%s.%s".formatted("./data", fileId,"jpg"));

        FileUtils.copyInputStreamToFile(file.getInputStream(),destinationFile);

    Photo photo=new Photo(0,fileId,destinationFile.getPath());
    photoInfoService.createPhoto(photo);
        return ResponseEntity.ok("Image Saved");
}

@DeleteMapping(path = "/{photoName}")
public ResponseEntity<String> deletePhoto(@PathVariable String photoName) throws IOException {
    File destinationFile=new File("%s/%s.%s".formatted("./data", photoName,"jpg"));
        FileUtils.forceDelete(destinationFile);
        return ResponseEntity.ok("Image has been deleted");
}



    @PutMapping("/{imageName}")
    public ResponseEntity<String> updateOrPutImage(
            @PathVariable String imageName,
            @RequestParam MultipartFile file) {

        String filePath = "./data/" + imageName;
        File existingFile = new File(filePath);

        try {
            // Get the bytes of the new file
            byte[] fileBytes = file.getBytes();

            // Create a new file with the same name or update the existing file
            FileUtils.writeByteArrayToFile(existingFile, fileBytes);

            if (existingFile.exists()) {
                return ResponseEntity.ok("Image updated successfully.");
            } else {
                return ResponseEntity.ok("Image uploaded successfully.");
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update or put image: " + e.getMessage());
        }
    }
}
