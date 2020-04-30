package com.example.inheritance;

public class MainDriver {
	
	public static void main(String[] args) {
		Parent p = new Parent();
		Child c = new Child();
		
//		p.parentMethod();
		
//		System.out.println(c.name); //Child class has access to parent fields
		c.parentMethod(); //Also has access to all the public and protected methods. 
		System.out.println("This is the child name: " + c.childName);
		c.childMethod();
		
//		System.out.println(p.childName); Parent clas has no access to child class fields 
//		p.childMethod(); Parent class has no access to childe class methods 
		
		
		c.grandParentMethod(); //Multilevel inheritance 
		
		Object o; //Super parent of all classes within Java
		Child o0 = new Child();
		Parent o1 = new Child();
		GrandParent o2 = new Child(); //Grandparent will be extending Object class implicitly
		
		System.out.println("o1 hashcode:" + o1.hashCode());
		System.out.println("o2 hashcode:" + o2.hashCode());
		System.out.println("Memory address of o1" + o1);
		
		
		randomMethod(o0);
		/*
		 * multilevel inheritance:(implicit) Object -> A -> B -> C
		 * 
		 * C will have access to all the methods and variables in B and A
		 * B will have access to all the methods in A 
		 * A will not have access to anything in C and C 
		 * All will have access to the Object methods. 
		 */
		
	}
	
	public static void randomMethod(Parent p) {
		System.out.println("My parent object" + p);
	}

}
