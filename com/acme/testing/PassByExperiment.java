package com.acme.testing;
import com.acme.utils.MyDate;

/**
 * This code shows how passing in primatives, objects, strings and string buffer behaves differently from one another.
 * @author RaspberryPi
 */
public class PassByExperiment
{
	public static void main(String[] args)
	{
		MyDate date = new MyDate(1,20,2008);
		System.out.println("Before passing an object " + date);
		passObject(date);
		System.out.println("After passing an object " + date);
		System.out.println("Before passing a primitive  " + date.getYear());
		passPrimitive(date.getYear());
		System.out.println("After passing a primitive  " + date.getYear());
		String x = date.toString();
		System.out.println("Before passing a String  " + x);
		passString(x);
		System.out.println("After passing a String  " + x);
		StringBuffer y = new StringBuffer(date.toString());
		System.out.println("Before passing a StringBuffer " + y);
		passStringBuffer(y);
		System.out.println("After passing a StringBuffer  " + y);
	}

	/**
	 * This method shows how objects behave when passed into a method.
	 * @param d A Date object
	 */
	public static void passObject(MyDate d) {d.setYear(2009); }

	/**
	 * This method shows how primatives behave when passed into a method.
	 * @param i A primitive that will contain the value of a year for the MyDate class
	 */
	public static void passPrimitive(int i) {i = 2010; }

	/**
	 * Ths method shows how strings behave when passed into a method.
	 * @param s A string that will contain a reference to the year of the MyDate class
	 */
	public static void passString(String s)
	{
		int yearSlash = s.lastIndexOf('/');
		s = s.substring(0,yearSlash+1);
		s += "2012";
		System.out.println("New date string: " + s);
	}

	/**
	 * Ths method shows how the StringBuffer class behaves when passed into a method.
	 * @param s A StringBuffer that will contain a reference to the year of the MyDate class
	 */
	public static void passStringBuffer(StringBuffer sb)
	{
		int yearSlash = sb.lastIndexOf("/");
		sb.replace(yearSlash+1,sb.length(),"2012");
		System.out.println("Newer date string: " + sb);
	}
}