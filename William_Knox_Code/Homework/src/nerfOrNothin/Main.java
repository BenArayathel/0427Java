package nerfOrNothin;

public class Main {
	public static void main(String[] args) {
		Blaster b = new Stryfe();
		
		b.shoot();
		b.loadChamber();
		
		Magazine m1 = new Magazine(10, 2);
		Magazine m2 = new Magazine(30, 1);
		
		b.setMagazine(m1);
		b.shoot();
		b.loadChamber();
		b.shoot();
		b.shoot();
		b.shoot();
		
		b.setMagazine(m2);
		b.loadChamber();
//		b.twirl();
		Stryfe s = (Stryfe) b;
		s.twirl();
		s.shoot();
		
		System.out.println(s.getMagazine().toString());
		System.out.println(b.getMagazine().toString());
		System.out.println(m2.toString());
		
		DoubleBreach d = new DoubleBreach(new Magazine(3,3));
		d.loadChamber();
		d.shoot();
		d.shoot();
		d.shoot();
		d.shake();
	}
}
