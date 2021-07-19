package com.tennisscorer.service;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import com.tennisscorer.repository.TennisRepository;
import com.tennisscorer.helper.CSVHelper;
import com.tennisscorer.model.TennisMatch;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Configurable
@Service
public class CSVService {

    @Autowired
    com.tennisscorer.repository.TennisRepository repository;


    public void save(MultipartFile file){
        try{
            List<TennisMatch> tennisMatches = CSVHelper.csvToTennisMatch(file.getInputStream());
            repository.saveAll(tennisMatches);
        }catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public ByteArrayInputStream load(){
        List<TennisMatch> tennisMatches = (List<TennisMatch>) repository.findAll();
        return CSVHelper.tennisMatchToCsv(tennisMatches);
    }

    public List<TennisMatch> getAllTennisMatch(){
        return (List<TennisMatch>) repository.findAll();
    }

}
