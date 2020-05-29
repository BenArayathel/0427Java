package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Ballplayers")
public class Ballplayer {

	@Id
	@Column(name = "player_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int playerId;
	
	@Column(name ="player_name", nullable=false)
	private String name;
	
	@Column(name = "avg")
	private double avg;
	
	@Column(name = "slg")
	private double slg;
	
	@Column(name = "ops")
	private double ops;
	
	@Column(name = "hr", nullable=false)
	private int hr;

	public Ballplayer() {
		super();
	}

	public Ballplayer(int playerId, String name, double avg, double slg, double ops, int hr) {
		super();
		this.playerId = playerId;
		this.name = name;
		this.avg = avg;
		this.slg = slg;
		this.ops = ops;
		this.hr = hr;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public double getSlg() {
		return slg;
	}

	public void setSlg(double slg) {
		this.slg = slg;
	}

	public double getOps() {
		return ops;
	}

	public void setOps(double ops) {
		this.ops = ops;
	}

	public int getHr() {
		return hr;
	}

	public void setHr(int hr) {
		this.hr = hr;
	}
	
}
