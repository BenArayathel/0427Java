package dao;

import java.util.List;

import model.Stuff;

public interface Dao {
	
	//CREATE 
	public void insertStuff(Stuff thing);
	
	//UPDATE 
	public void updateStuff(Stuff thing);
	
	void updateStuffAmount(String name, int amount);
	
	//READ 
	public List<Stuff> selectAllStuff();
	public Stuff selectStuffByName(String thing);
	
	//DELETE 
	public void deleteStuff(Stuff thing);

	public void deleteStuff(String thingName);






}
