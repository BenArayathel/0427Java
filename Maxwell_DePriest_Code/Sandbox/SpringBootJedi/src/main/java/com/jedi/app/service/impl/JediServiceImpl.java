package com.jedi.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jedi.app.dao.JediDao;
import com.jedi.app.dao.PadawanDao;
import com.jedi.app.model.Jedi;
import com.jedi.app.model.Padawan;
import com.jedi.app.service.JediService;

@Service
public class JediServiceImpl implements JediService {
	
	@Autowired
	private JediDao jediDao;
	
	@Autowired
	private PadawanDao padawanDao;

	@Override
	public Jedi createJedi(Jedi jedi) {
		return jediDao.save(jedi);
	}

	@Override
	public Jedi updateJedi(Jedi jedi) {
		return jediDao.save(jedi);
	}

	@Override
	public Jedi getJediById(int id) {
		return jediDao.findById(id).get();
	}

	@Override
	public void deleteJediById(int id) {
		jediDao.deleteById(id);
		
	}

	@Override
	public List<Jedi> getAllJedi() {
		return jediDao.findAll();
	}

	@Override
	public List<Jedi> getAllJediByRank(String rank) {
		return jediDao.findJediByRank(rank);
	}

	@Override
	public boolean createJediRoster() {		
				
		jediDao.save(new Jedi("Qui-gon Jinn", "knight", "green", false));
		jediDao.save(new Jedi("Mace Windu", "master", "purple", false));

		
//		Padawan anakin = new Padawan("Anakin Skywalker", "blue", obiWan.getId());
//		padawanDao.save(anakin);
		return true;
		
	}

}
