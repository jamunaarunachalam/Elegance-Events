package com.example.demo.entity;

import com.example.demo.entity.enumerate.Activity;
import com.example.demo.entity.enumerate.Climate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="suggestor")
public class SuggestorEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int age;
	private int weight;
	@Enumerated(EnumType.STRING)
	private Activity activity;
	@Enumerated(EnumType.STRING)
	private Climate climate;

}
