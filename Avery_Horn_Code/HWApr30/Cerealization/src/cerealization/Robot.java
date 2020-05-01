package cerealization;

import java.io.Serializable;

public class Robot implements Serializable{
	private static final long serialVersionUID = -634939817748860226L;
	private int sNumber;
	private String pilot;
	private String weapon;
	
	public Robot()
	{
		
	}
	
	public Robot(int sNumber, String pilot, String weapon) {
		super();
		this.sNumber = sNumber;
		this.pilot = pilot;
		this.weapon = weapon;
	}
	
	public int getsNumber() {
		return sNumber;
	}
	public void setsNumber(int sNumber) {
		this.sNumber = sNumber;
	}
	public String getPilot() {
		return pilot;
	}
	public void setPilot(String pilot) {
		this.pilot = pilot;
	}
	public String getWeapon() {
		return weapon;
	}
	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}
	@Override
	public String toString() {
		return "Robot [sNumber=" + sNumber + ", pilot=" + pilot + ", weapon=" + weapon + "]";
	}

	

}
