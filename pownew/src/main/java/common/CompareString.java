package common;


public class CompareString {

	String expectedString;
	String actualString;
	
	
	public static  boolean compareText(String actualString, String expectedString){
		Boolean  val = actualString.contains(expectedString);
		System.out.println("value of Expected "+expectedString+" value of actualString is "+actualString + " Boolean value "+val);
		return val;
	
	}
	
	
}
