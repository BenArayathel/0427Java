package DetectiveComics;

import java.util.ArrayList;

public class Hero{
	
	private String name;
	private String gender;
	private boolean hasSidekick;
	private String secretIdentity;
	private String location;
	private Hero sideKick;
	private ArrayList<String> weaponArrayList = new ArrayList<String>();
	private ArrayList<String> abilityArrayList = new ArrayList<String>();
	
	/*
	 * have empty lists of powers and weapons here, then in the individual hero classes can add to it based on own stats
	 * check out interface variables. may be easier to just have a big list of powers/weapons that can be picked out 
	 * instead of the default methods
	 */
	
	

	public Hero(String name, String gender, boolean hasSidekick, String secretIdentity, String location) {
		this.name = name;
		this.gender = gender;
		this.hasSidekick = hasSidekick;
		this.secretIdentity = secretIdentity;
		this.location = location;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSecretIdentity() {
		return secretIdentity;
	}

	public void setSecretIdentity(String secretIdentity) {
		this.secretIdentity = secretIdentity;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean isHasSidekick() {
		return hasSidekick;
	}

	public void setHasSidekick(boolean hasSidekick) {
		this.hasSidekick = hasSidekick;
	}

	public Hero getSideKick() {
		return sideKick;
	}

	public void setSideKick(Hero sideKick) {
		this.sideKick = sideKick;
	}
	
	public ArrayList<String> getWeaponArray() {
		return weaponArrayList;
	}
	
	public void addToWeaponArrayList(String weapon) {
		this.weaponArrayList.add(weapon);
	}
	
	public ArrayList<String> getAbilityArrayList() {
		return abilityArrayList;
	}
	
	public void addToAbilityArrayList(String ability) {
		this.getAbilityArrayList().add(ability);
	}

	
	public void printHero() {
		System.out.println("Name: " + name);
		System.out.println("Gender: " + gender);
		System.out.println("Secret Identity: " + secretIdentity);
		System.out.println("Location: " + location);
		if (!weaponArrayList.isEmpty()) {
			ArrayList<String> weapons = getWeaponArray();
			System.out.printf("Weapons: %s, %s, %s %n", weapons.get(0), weapons.get(1), weapons.get(2));
		}
		if (!abilityArrayList.isEmpty()) {
			ArrayList<String> abilities = getAbilityArrayList();
			System.out.printf("Abilities: %s, %s, %s %n", abilities.get(0), abilities.get(1), abilities.get(2));
		}
		if(hasSidekick) {
			System.out.println("Has a sidekick\n");
			System.out.println("-SideKick Details:");
			this.printSidekick(sideKick);	
		}
		
	}
	
	public void printSidekick(Hero sK) {
		System.out.println("Name: " + sK.name);
		System.out.println("Gender: " + sK.gender);
		System.out.println("Secret Identity: " + sK.secretIdentity);
		System.out.println("Location: " + sK.location);
		if (!sK.weaponArrayList.isEmpty()) {
			ArrayList<String> weapons = sK.getWeaponArray();
			System.out.printf("Weapons: %s, %s, %s %n", weapons.get(0), weapons.get(1), weapons.get(2));
		}
		if (!sK.abilityArrayList.isEmpty()) {
			ArrayList<String> abilities = sK.getAbilityArrayList();
			System.out.printf("Abilities: %s, %s, %s %n", abilities.get(0), abilities.get(1), abilities.get(2));
		}
		System.out.println();
	}
	
	

}
