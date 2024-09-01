package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.entity.SuggestorEntity;
import com.example.demo.repository.SuggestorRepo;
@Service
public class SuggestorService {
	@Autowired
	private  SuggestorRepo srepo;
	
	public List<SuggestorEntity> getSuggestor()
	{
		return srepo.findAll();
	}
	public void postSuggestor(SuggestorEntity se)
	{
		srepo.save(se);
	}
	public void deleteSuggestor(int id)
	{
		srepo.deleteById(id);
	}
	public SuggestorEntity updateSuggestor(SuggestorEntity se)
	{
		return srepo.save(se);
	}
	

}
