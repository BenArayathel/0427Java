package nerfOrNothin;

public abstract class Blaster implements NerfGun {
	private int bulletsPerShot;
	private int chamber;
	private int chamberCap;
	private Magazine magazine;
	
	public Blaster() {
		// default 12 gauge double barrel
		this(6, 6, 6, new Magazine(0,0));
	}
	
	public Blaster(int bulletsPerShot, int chamber, int chamberCap) {
		this(bulletsPerShot, chamber, chamberCap, new Magazine(0,0));
	}
	
	public Blaster(int bulletsPerShot, int chamber, int chamberCap, Magazine magazine) {
		this.bulletsPerShot = bulletsPerShot;
		this.chamber = chamber;
		this.magazine = magazine;
	}
	
	public void shoot() {
		if (this.chamber > 0) {
			if (this.chamber < this.bulletsPerShot) {
				System.out.println("Shot " + chamber + " dart(s)!");
				this.chamber = 0;
			} else {
				System.out.println("Shot " + bulletsPerShot + " dart(s)!");
				this.chamber -= this.bulletsPerShot;
			}
			this.loadChamber();
		} else {
			System.out.println("Tried to shoot with no dart in the chamber!");
		}
	}
	
	public void loadChamber() {
		if (this.magazine.getDarts() > 0) {
			if (this.magazine.getDarts() > this.bulletsPerShot) {
				this.chamber += this.bulletsPerShot;
				this.magazine.setDarts(magazine.getDarts() - this.bulletsPerShot);
			} else {
				this.chamber += this.magazine.getDarts();
				this.magazine.setDarts(0);
			}
			System.out.println("Chamber loaded!");
		} else {
			System.out.println("Tried to load chamber from an empty mag!");
		}
	}

	public int getBulletsPerShot() {
		return bulletsPerShot;
	}

	public void setBulletsPerShot(int bulletsPerShot) {
		this.bulletsPerShot = bulletsPerShot;
	}

	public int getChamber() {
		return chamber;
	}

	public void setChamber(int chamber) {
		if (chamber < 0) {
			this.chamber = 0;
		} else if (chamber >= this.chamberCap) {
			this.chamber = this.chamberCap;
		} else {
			this.chamber = chamber;
		}
	}

	public int getChamberCap() {
		return chamberCap;
	}

	public void setChamberCap(int chamberCap) {
		this.chamberCap = chamberCap;
	}

	public Magazine getMagazine() {
		return magazine;
	}

	public void setMagazine(Magazine magazine) {
		System.out.println("Magazine loaded!");
		this.magazine = magazine;
	}
	
}
