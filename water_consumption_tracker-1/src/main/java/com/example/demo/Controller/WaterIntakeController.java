package com.example.demo.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.request.WaterIntakeRequest;
import com.example.demo.dto.response.WaterIntakeDTO;
import com.example.demo.entity.Login;
import com.example.demo.entity.WaterIntake;
import com.example.demo.repository.WaterIntakeRepository;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.LoginService;
import com.example.demo.service.WaterIntakeService;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class WaterIntakeController {
	@Autowired
    private WaterIntakeService waterIntakeService;
    
    @Autowired
    private AuthenticationService userService;
    
    @Autowired
    private WaterIntakeRepository waterIntakeRepository;

    @GetMapping("/history/{username}")
    public ResponseEntity<List<WaterIntakeDTO>> getWaterIntakeHistory(@PathVariable String username) {
        Optional<Login> userOptional = userService.getUserByUsername(username);
    	System.err.println(userOptional.get());
        if (userOptional.isEmpty()) {
            
            return ResponseEntity.notFound().build();
        }

        List<WaterIntake> waterIntakeList = waterIntakeService.getWaterIntakeForLastFiveDays(userOptional.get());
        List<WaterIntakeDTO> history = waterIntakeList.stream()
                .map(waterIntake -> new WaterIntakeDTO(waterIntake.getUser().getUserId(), waterIntake.getDate(), waterIntake.getDailyTotal()))
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(history);
    }
 
    @PostMapping("/log-container/{username}")
    public ResponseEntity<WaterIntakeDTO> logContainer(@PathVariable String username, @RequestBody WaterIntakeRequest request)
    {
    	Optional<Login> userOptional = userService.getUserByUsername(username);
        if (userOptional.isEmpty()) {
           
            return ResponseEntity.notFound().build();
        }
        

        String container = request.getContainer();
        LocalDate currentDate = LocalDate.now();

   
        List<WaterIntake> intakes = waterIntakeService.getWaterIntakeForLastFiveDays(userOptional.get());
        int dailyTotal = intakes.isEmpty() ? 0 : intakes.get(intakes.size() - 1).getDailyTotal();
        switch (container.toLowerCase()) {
        case "cup":
            dailyTotal += 250;
            break;
        case "glass":
            dailyTotal += 500;
            break;
        case "bottle":
            dailyTotal += 750;
            break;
        default:
            break;
    }
     
        WaterIntake savedIntake = waterIntakeService.logWaterIntake(userOptional.get(), container, currentDate, dailyTotal);
        WaterIntakeDTO waterIntakeDTO = new WaterIntakeDTO(userOptional.get().getUserId(), currentDate, dailyTotal);

        return ResponseEntity.ok(waterIntakeDTO);
    }
    @GetMapping("/seven-day-records/{username}")
    public ResponseEntity<List<WaterIntakeDTO>> getSevenDayRecords(@PathVariable String username) {
        Optional<Login> userOptional = userService.getUserByUsername(username);
       
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<WaterIntakeDTO> sevenDayRecords = waterIntakeService.getSevenDayRecords(userOptional.get());
        
        return ResponseEntity.ok(sevenDayRecords);
    }
   
}