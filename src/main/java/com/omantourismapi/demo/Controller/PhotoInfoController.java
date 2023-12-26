package com.omantourismapi.demo.Controller;


import com.omantourismapi.demo.Model.Photo;
import com.omantourismapi.demo.Services.PhotoInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/photos")
public class PhotoInfoController {

    @Autowired
    PhotoInfoService photoInfoService;

    @GetMapping
    public List<Photo> getImages(){

        return photoInfoService.Photo();
    }

    @PostMapping
    public Photo addPhoto(@RequestBody Photo addNewPhoto){
        photoInfoService.createPhoto(addNewPhoto);
        return addNewPhoto;
    }

    @GetMapping(path = "/{Id}")
    public Photo returnPhoto(@PathVariable int Id){


        return photoInfoService.getSpecificPhoto(Id);
    }

    @PutMapping(path = "/{Id}")
    public Photo updateDetails(@PathVariable int Id, @RequestBody Photo updateInfo){

        return photoInfoService.updateDetails(Id,updateInfo);
    }

    @DeleteMapping(path = "/{Id}")
    public Photo removePhoto(@PathVariable int Id){

        return photoInfoService.removePhoto(Id);
    }
}
