package com.example.dao;

import java.util.List;

import com.example.model.Cars;

public interface CarsDAO {

	public List<Cars> selectAllCars();
	public Cars selectCarById(int id);
	public void insertCar(Cars c);
	public void deleteCar(Cars c);
	public void updateCar(Cars c);
}
