package com.omantourismapi.demo;


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/photos")
public class images {
    ArrayList<photoDetails> images=new ArrayList<>();

    @GetMapping
    public List<photoDetails> getImages(){
images.add(new photoDetails("1","Oman", "/images"));
        return images;
    }

    @PostMapping
    public photoDetails addPhoto(@RequestBody photoDetails addNewPhoto){
        images.add(addNewPhoto);
        return addNewPhoto;
    }

    @GetMapping(path = "/{Id}")
    public photoDetails returnPhoto(@PathVariable String Id){
        photoDetails getPhoto=null;
       Optional<photoDetails> photo= images.stream().filter((currPhoto)->{
        return currPhoto.imdID.equals(Id);
        }).findFirst();

         if(photo.isPresent()){
         getPhoto=photo.get();
         }

        return getPhoto;
    }

    @PutMapping(path = "/{Id}")
    public photoDetails updateDetails(@PathVariable String Id,@RequestBody photoDetails addNewPhoto){
        photoDetails updatePhoto=returnPhoto(Id);
        updatePhoto.imgName= addNewPhoto.imgName;
        updatePhoto.imgPath= addNewPhoto.imgPath;
        return updatePhoto;
    }
}
