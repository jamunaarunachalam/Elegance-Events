package com.example.demo.dto.response;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WaterIntakeDTO {
   
	private int userId;
    private LocalDate date;
    private int dailyTotal;

    
    public WaterIntakeDTO(int userId, LocalDate date, int dailyTotal) {
        this.userId = userId;
        this.date = date;
        this.dailyTotal = dailyTotal;
    }

   
}
