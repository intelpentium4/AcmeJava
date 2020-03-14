//MyDate Class
package com.acme.utils;
import java.time.DateTimeException;
public class MyDate
{
	private static MyDate[] holidays;
	static
	{
		holidays = new MyDate[6];
		holidays[0] = new MyDate(1,1,2016);
		holidays[1] = new MyDate(9,5,2016);
		holidays[2] = new MyDate(5,30,2016);
		holidays[3] = new MyDate(11,24,2016);
		holidays[4] = new MyDate(7,4,2016);
		holidays[5] = new MyDate(12,25,2016);
	}
	public static MyDate[] getHolidays() { return holidays; }
	public static void listHolidays()
	{
		System.out.println("The holidays are:");
		for(MyDate i : holidays) { System.out.println(i); }
	}

	private byte day;
	private byte month;
	private short year;

	public MyDate() { this(1,1,1990); }
	public MyDate(int m, int d, int y)
	{
		setDate(m,d,y);
	}

	public void setDay(int day) 
	{
		if (valid(day, month, year))
			this.day = (byte)day;
	}
	public int getDay() { return this.day; }
	public void setMonth(int month)
	{ 
		if (valid(day, month, year))	 
			this.month = (byte)month;
	}
	public int getMonth() { return this.month; }
	public void setYear(int year) 
	{
		if (valid(day, month, year))
			this.year = (short)year; 
	}
	public int getYear() { return this.year; }
	
	private boolean valid(int day, int month, int year)
	{
		try{
			if (day > 31 || day < 1 || month > 12 || month < 1 )
				throw new DateTimeException("" + month + "/" + day + "/" + year);	
		} catch(DateTimeException e) {
			System.out.println("Attempting to create a non-valid date " + e);
			return false;
		}
		switch (month){
			case 4:
			case 6:
			case 9:
			case 11: return (day <= 30);
			case 2: return day <= 28 || ( day == 29 && year % 4 == 0) ;
		}
		return true;
	}	

	public String toString()
	{
		return month+"/"+day+"/"+year;
	}
	public boolean equals(Object o)
	{
		if(o instanceof MyDate)
		{
			MyDate d = (MyDate) o;
			if((d.day == this.day) && (d.month == this.month) && (d.year == this.year)) 
				return true;
		}
		return false;
	}
	public void setDate(int m, int d, int y)
	{
		if (valid(d, m, y))
		{
			day = (byte)d;
			month = (byte)m;
			year = (short)y;
		}
	}
	public static void leapYears() 
	{
		for(int year = 1752; year <= 2020; year++)
		{
			if(year % 4 == 0)
			{
				if(year % 100 == 0)
				{
					if(year % 400 == 0)
						System.out.println("The year " + year + " is a leap year");
				}
				else
					System.out.println("The year " + year + " is a leap year");
			}
		}
	}
}