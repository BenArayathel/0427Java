package com.data;

public class DataInterface {

	private String injectionType;
	
	//private Object[] objectInput = null;	
	private String[] stringData = null;
	private Integer[] intData = null;
	private Double [] doubleData =null;
	private Float[] floatData = null;
	private Boolean[] booleanData = null;
	private Byte[] byteData = null;
	private Long[] longData = null;
	private Short[] shortData = null;
	private String queryString = null;

	public DataInterface(Object[] data, String injectionType) {
		
		//Loop that iterates through query string of objects to check type
		//and assigns the correct class variable
		for (int i = 0; i < data.length; i++) {
			if (data[i] instanceof String) {
				//System.out.println(data[i]);
				stringData = (String[])data;
				dataSet("string", injectionType);
			}else if (data[i] instanceof Integer) {
				intData = (Integer[])data;
				dataSet("int", injectionType);
			}else if (data[i] instanceof Double) {
				doubleData = (Double[])data;
				dataSet("double", injectionType);
			}else if (data[i] instanceof Float) {
				floatData = (Float[])data;
				dataSet("float", injectionType);
			}else if (data[i] instanceof Boolean) {
				booleanData = (Boolean[])data;
				dataSet("boolean", injectionType);
			}else if (data[i] instanceof Byte) {
				byteData = (Byte[])data;
				dataSet("byte", injectionType);
			}else if (data[i] instanceof Long) {
				longData = (Long[])data;
				dataSet("long", injectionType);
			}else if (data[i] instanceof Short) {
				shortData = (Short[])data;
				dataSet("short", injectionType);
			}else {
				System.out.println("Fail");
			}
		}
	}
	
	/**
	 * This method pulls in information and concatenates it 
	 * to generate a query string to pass into the database
	 * @param dataType the type of data currently passed in =to the injection
	 * @param injectionType the type of SQL query to call
	 */
	private void dataSet(String dataType, String injectionType) {
		
		for (int j = 0; j < stringData.length; j++) {
			queryString += " " + stringData[j];
			
			
			if(queryString == null) {
				System.out.println("First Time");
			}else {
				System.out.println("Subsequent Time");
			}
		}
				
		int caseVal = 5;
		//Determine which injection type was passed in
		switch(caseVal) {
		case 7:
			
			break;
		case 6:
			
			break;
		case 5:
			
			break;
		case 4:
			
			break;
		case 3:
			
			break;
		case 2:
			
			break;
		case 1:
			
			break;
		case 0:
			
			break;
//		default:
//			System.out.println("There was an unexpected input error, please try again!");
//			break;
		}
		
	}
	
	public void insert(double [] data) {
		
	}
	
	public void select() {
		
	}
	
	public void delete() {
		
	}
}
