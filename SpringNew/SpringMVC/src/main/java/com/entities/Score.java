package com.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="score")
public class Score {

	@Id
	@Column(name="num")
	private int number;
	
	@Column(name="std_Name")
	private String name;
	
	@Column(name="chinese")
	private int chinese;
	
	@Column(name="math")
	private int math;
	
	@Column(name="english")
	private int english;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getChinese() {
		return chinese;
	}

	public void setChinese(int chinese) {
		this.chinese = chinese;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getEnglish() {
		return english;
	}

	public void setEnglish(int english) {
		this.english = english;
	}
	
}
