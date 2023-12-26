package com.omantourismapi.demo.Services;

import com.omantourismapi.demo.Model.Photo;
import com.omantourismapi.demo.repository.PhotoInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PhotoInfoService {

    @Autowired
    public PhotoInfoRepository photoInfoRepository;
    public ArrayList<Photo> imagesList=new ArrayList<>();

    public List<Photo> Photo(){
        return photoInfoRepository.findAll();
    }

    public Photo createPhoto(Photo addNewPhoto){
        photoInfoRepository.save(addNewPhoto);
        return addNewPhoto;
    }

    public Photo getSpecificPhoto(int Id){
       Photo getPhoto=null;
//        Optional<Photo> photo= imagesList.stream().filter((currPhoto)->{
//            return currPhoto.imdID.equals(Id);
//        }).findFirst();

        Optional<Photo> photo1=photoInfoRepository.findAll().stream().filter((currentPhoto)->{
            return (currentPhoto.imdID==Id);
        }).findFirst();

        if(photo1.isPresent()){
            getPhoto=photo1.get();
        }

        return getPhoto;
    }

    public Photo updateDetails(int Id, Photo addNewPhoto){
        Photo updatePhoto=getSpecificPhoto(Id);
        updatePhoto.imgName= addNewPhoto.imgName;
        updatePhoto.imgPath= addNewPhoto.imgPath;
        return updatePhoto;
    }

    public Photo removePhoto(int Id){
        Photo photoDetails=getSpecificPhoto(Id);
        imagesList.remove(photoDetails);
        return photoDetails;
    }


}
