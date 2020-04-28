package com.example.inheritance;

public class Main {

	public static void main(String[] args) {
		/*
		 * 
		 * Implicit - compiler - upcasting - child class obj is assigned to parent
		 * Explicit - dev - downcasting- explicitly we have to say that the parent object needs to converted to which child
		 */
		Object o=new Child(); //reference variable
		System.out.println(o.getClass());
		System.out.println(o.hashCode());
		
		Parent p=(Parent)o;//ref var
		p.grandParentMethod();
		p.parentMethod();
		System.out.println(p.hashCode());
		
		Child c=(Child)o; //object
		System.out.println(c.hashCode());
		c.childMethod();
		c.parentMethod();
		c.grandParentMethod();
		
		
		Object o1=new Parent();
		//Child c1=(Child)o1;
		Parent p2=(Parent)o1;
	}

}
