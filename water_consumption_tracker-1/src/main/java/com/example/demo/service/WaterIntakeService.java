package com.example.demo.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.response.WaterIntakeDTO;
import com.example.demo.entity.Login;

import com.example.demo.entity.WaterIntake;
import com.example.demo.repository.LoginRepository;
import com.example.demo.repository.WaterIntakeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WaterIntakeService {
	
	
    @Autowired
    private WaterIntakeRepository waterIntakeRepository;
   
    private final LoginRepository loginRepository;

    public List<WaterIntake> getWaterIntakeForLastFiveDays(Login user) {
        LocalDate today = LocalDate.now();
        LocalDate fiveDaysAgo = today.minusDays(4);

        return waterIntakeRepository.findByUserAndDateBetween(user, fiveDaysAgo, today);
    }

    public WaterIntake logWaterIntake(Login user, String container, LocalDate date,int dailyTotal) {
    	
        WaterIntake intake = new WaterIntake();
        intake.setUser(user);
        intake.setDate(date);
        intake.setContainer(container);

        switch (container.toLowerCase()) {
            case "cup":
                intake.setVolume(250);
                break;
            case "glass":
                intake.setVolume(500);
                break;
            case "bottle":
                intake.setVolume(750);
                break;
            default:
                intake.setVolume(0);
                break;
        }

        intake.setDailyTotal(dailyTotal);

        return waterIntakeRepository.save(intake);
     }
    public WaterIntakeDTO getWaterIntakeDTO(Login user, LocalDate date) {
        
        WaterIntake intake = waterIntakeRepository.findByUserAndDateOrderByDateDesc(user, date).stream().findFirst().orElse(null);

        if (intake != null) {
            return new WaterIntakeDTO(intake.getUser().getUserId(), intake.getDate(), intake.getDailyTotal());
        } else {
          
            return new WaterIntakeDTO(user.getUserId(), date, 0);
        }
    
    
}
    public List<WaterIntakeDTO> getSevenDayRecords(Login user) {
        LocalDate today = LocalDate.now();
        LocalDate sevenDaysAgo = today.minusDays(6);

        List<WaterIntakeDTO> result = new ArrayList<>();

        for (LocalDate date = sevenDaysAgo; date.isBefore(today) || date.isEqual(today); date = date.plusDays(1)) {
            WaterIntakeDTO waterIntakeDTO = getWaterIntakeDTO(user, date);
            result.add(waterIntakeDTO);
        }

        return result;
    }

   
}



