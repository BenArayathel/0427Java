package com.examples.factory;

public class FruitFactory {

		public Fruit getFruit(String name) {
			Fruit f = null;
			if(name.equalsIgnoreCase("Red Apple")) {
				
				f = new Apple("Apple",100,"red");
				
			}else if (name.equalsIgnoreCase("Green Apple")) {
				f = new Apple("Apple",101,"green");
			}
			
			else if(name.equalsIgnoreCase("Kiwi")) {
				
				f = new Kiwi(true);
			}
			
			return f;
		}

}
