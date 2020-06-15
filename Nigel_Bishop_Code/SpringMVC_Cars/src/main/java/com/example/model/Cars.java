package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Car_Table")
public class Cars {
	
	@Id
	@Column(name = "car_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int carID;
	
	@Column(name = "car_Make", unique = false, nullable = false)
	private String carMake;
	
	@Column(name = "car_Model", unique = false, nullable = false)
	private String carModel;
	
	@Column(name = "car_HorsePower", unique = false, nullable = false)
	private int carHorsePower;
	
	public Cars() {
		// TODO Auto-generated constructor stub
	}

	public Cars(int carID, String carMake, String carModel, int carHorsePower) {
		super();
		this.carID = carID;
		this.carMake = carMake;
		this.carModel = carModel;
		this.carHorsePower = carHorsePower;
	}

	public int getCarID() {
		return carID;
	}

	public void setCarID(int carID) {
		this.carID = carID;
	}

	public String getCarMake() {
		return carMake;
	}

	public void setCarMake(String carMake) {
		this.carMake = carMake;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public int getCarHorsePower() {
		return carHorsePower;
	}

	public void setCarHorsePower(int carHorsePower) {
		this.carHorsePower = carHorsePower;
	}

	@Override
	public String toString() {
		return "Cars [carID=" + carID + ", carMake=" + carMake + ", carModel=" + carModel + ", carHorsePower="
				+ carHorsePower + "]";
	}
	
	
	
	

}
