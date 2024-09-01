package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.demo.entity.SuggestorEntity;
@Repository
public interface SuggestorRepo extends JpaRepository<SuggestorEntity, Integer> {

}