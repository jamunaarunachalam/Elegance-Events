package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Feedback;
import com.example.demo.repository.FeedbackRepo;

@CrossOrigin
@RequestMapping("/auth")
@RestController
public class FeedbackCon 
{
@Autowired
private FeedbackRepo fr;
@GetMapping("/getfeed")
public List<Feedback> getfeed()
{
	return fr.findAll();
}

@PostMapping("/postfeed")
public void postfeed(@RequestBody Feedback fe)
{
	fr.save(fe);
}

}
