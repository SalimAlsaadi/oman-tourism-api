package com.omantourismapi.demo.repository;

import com.omantourismapi.demo.Model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoInfoRepository extends JpaRepository<Photo,String> {
}
