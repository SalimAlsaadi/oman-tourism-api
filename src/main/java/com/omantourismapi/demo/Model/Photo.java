package com.omantourismapi.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int imdID;
    public String imgName;
    public String imgPath;

    public Photo(int imdID, String imgName, String imgPath) {
        this.imdID = imdID;
        this.imgName = imgName;
        this.imgPath = imgPath;
    }


    public Photo() {

    }
}
