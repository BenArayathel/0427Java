package com.jedi.app.service;

import java.util.List;

import com.jedi.app.model.Jedi;

public interface JediService {

	public Jedi createJedi(Jedi jedi);
	public Jedi updateJedi(Jedi jedi);
	public Jedi getJediById(int id);
	public void deleteJediById(int id);
	public List<Jedi> getAllJedi();
	public List<Jedi> getAllJediByRank(String rank);

}
