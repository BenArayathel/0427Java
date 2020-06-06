package com.jedi.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jedi.app.dao.PadawanDao;
import com.jedi.app.model.Padawan;
import com.jedi.app.service.PadawanService;

@Service
public class PadawanServiceImpl implements PadawanService {

	@Autowired
	PadawanDao padawanDao;

	@Override
	public Padawan createPadawan(Padawan padawan) {
		return padawanDao.save(padawan);
	}

	@Override
	public Padawan updatePadawan(Padawan padawan) {
		return padawanDao.save(padawan);
	}

	@Override
	public Padawan getPadawanById(int id) {
		return padawanDao.findById(id).get();
	}

	@Override
	public void deletePadawanById(int id) {
		padawanDao.deleteById(id);
		
	}

	@Override
	public List<Padawan> getAllPadawans() {
		return padawanDao.findAll();
	}

}
