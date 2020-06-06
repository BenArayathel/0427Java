package com.jedi.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jedi.app.dao.JediDao;
import com.jedi.app.model.Jedi;
import com.jedi.app.service.JediService;

@Service
public class JediServiceImpl implements JediService {
	
	@Autowired
	private JediDao jediDao;

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

}
