package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.SuggestorEntity;
import com.example.demo.service.SuggestorService;
@CrossOrigin
@RestController
@RequestMapping("/auth")
public class SuggestorController {
	@Autowired
	
	private SuggestorService ss;
	
	    @GetMapping("/getSuggestor")
		public List<SuggestorEntity> getSuggestor()
		{
			return ss.getSuggestor();
		}
	    @PostMapping("/postSuggestor")
		public String postSuggestor(@RequestBody SuggestorEntity se)
		{
			ss.postSuggestor(se);
			return "Update Successfully";
		}
	    @DeleteMapping("/deleteSuggestor/{id}")
		public String deleteSuggestor(@PathVariable int id)
		{
			ss.deleteSuggestor(id);
			return "Deleted Successfully";
		}
	    @PutMapping("/putSuggestor/{id}")
		public String updateSuggestor(@RequestBody SuggestorEntity se,@PathVariable int id)
		{
			se.setId(id);
			ss.updateSuggestor(se);
			return "Updated Successfully";
		}
	}


