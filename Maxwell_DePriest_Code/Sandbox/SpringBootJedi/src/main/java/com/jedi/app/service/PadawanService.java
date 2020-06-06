package com.jedi.app.service;

import java.util.List;

import com.jedi.app.model.Padawan;

public interface PadawanService {

	public Padawan createPadawan(Padawan padawan);
	public Padawan updatePadawan(Padawan padawan);
	public Padawan getPadawanById(int id);
	public void deletePadawanById(int id);
	public List<Padawan> getAllPadawans();
}
