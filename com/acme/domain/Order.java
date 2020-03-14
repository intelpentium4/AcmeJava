package com.acme.domain;
import com.acme.utils.MyDate;
import com.acme.utils.HolidayOrdersNotAllowedException;
public class Order {
	private static double taxRate;
	
	//Static Instantiation block
	static{
		taxRate = 0.05;
	}

	private MyDate orderDate;
	private double orderAmount = 0.00;
	private String customer;
	private Product product;
	private int quantity;
	private static Rushable rushable;
	
	//Constructor
	public Order(MyDate d, double amt, String c, Product p, int q){
		try{
			setOrderDate(d);
		} catch (HolidayOrdersNotAllowedException e) {
			System.out.println("The order date for an order cannot be a holiday! Application closing.");
			System.exit(0);
		}
		orderAmount=amt;
		customer=c;
		product=p;
		quantity=q;
	}
	public Order(MyDate d, double amt, String c){
		this(d,amt,c,new Solid("Acme Anvil", 1668, 0.3, Good.UnitOfMeasureType.CUBIC_METER, false, 500, 0.25, 0.3),1);
	}

	/**
	 * THis method checks if the input number is positive.
	 * @param num The input number to be checked for positivity.
	 */
	private static Boolean isPositive(double num)
	{
		return num > 0;
	}

	//Setters and Getters
	public void setOrderDate(MyDate orderDate) throws HolidayOrdersNotAllowedException
	{
		if (isHoliday(orderDate))
		{
			System.out.println("Order date, " + orderDate + ", cannot be set to a holiday!");
			throw new HolidayOrdersNotAllowedException(orderDate);
		}
		else
			this.orderDate = orderDate;
	}
	public MyDate getOrderDate() { return this.orderDate; }
	public void setOrderAmount(double orderAmount)
	{
		if(isPositive(orderAmount))
			this.orderAmount = orderAmount;
	}
	public double getOrderAmount() { return this.orderAmount; }
	public void setCustomer(String customer) { this.customer = customer; }
	public String getCustomer() { return this.customer; }
	public void setProduct(Product product) { this.product = product; }
	public Product getProduct() { return this.product; }
	public void setQuantity(int quantity)
	{
		if(isPositive((double)quantity))
			this.quantity = quantity;
		else
			System.out.println("Attempting to set the quantity to a value less than or equal to zero. Request Denied!");
	}
	public int getQuantity() { return this.quantity; }
	public static void setTaxRate(double newTaxRate)
	{
		if(isPositive(newTaxRate))
			taxRate = newTaxRate;
		else
			System.out.println("Attempting to set the taxRate to a value less than or equal to zero. Request Denied!");
	}
	public static double getTaxRate() { return taxRate; }
	public static void setRushable(Rushable rushable) { Order.rushable = rushable; }
	public static Rushable getRushable() { return rushable; }

	//Instance Methods
	public String toString(){
		return quantity + " ea. " + product + " for " + customer; 
	}

	//Static or Class Methods
	public static void computeTaxOn(double anAmount){

		System.out.println("The tax for " + anAmount + " is: " + anAmount * Order.taxRate);

	}
	public double computeTax() {

		System.out.println("The tax for this order is: " + orderAmount * Order.taxRate);

		return orderAmount * Order.taxRate;

	}
	public char jobSize() {
		if(this.quantity < 26)
			return 'S';
		else if(this.quantity < 76)
			return 'M';
		else if(this.quantity < 151)
			return 'L';
		else
			return 'X';
	}
	public double computeTotal() {
		double totalB4Tax = this.orderAmount;
		if(this.quantity < 26)
		{
			if(totalB4Tax >= 1500)
				return totalB4Tax;
			else
				return totalB4Tax + this.computeTax();
		}
		else if(this.quantity < 76)
		{
			if(totalB4Tax >= 1500)
				return totalB4Tax - (0.01 * totalB4Tax);
			else
				return totalB4Tax - (0.01 * totalB4Tax) + this.orderAmount * Order.taxRate;
		}
		else if(this.quantity < 151)
		{
			if(totalB4Tax >= 1500)
				return totalB4Tax - (0.02 * totalB4Tax);
			else
				return totalB4Tax - (0.02 * totalB4Tax) + this.orderAmount * Order.taxRate;
		}
		else
		{
			if(totalB4Tax >= 1500)
				return totalB4Tax - (0.03 * totalB4Tax);
			else
				return totalB4Tax - (0.03 * totalB4Tax) + this.orderAmount * Order.taxRate;
		}
	}
	public boolean isPriorityOrder()
	{
		boolean priorityOrder = false;

		if( rushable != null )

			priorityOrder = rushable.isRushable(orderDate, orderAmount);

		return priorityOrder;
	}
	private boolean isHoliday(MyDate proposedDate) {

		boolean result = false;

		for (MyDate holiday : MyDate.getHolidays()) {

			if( holiday.equals(proposedDate) )

				result = true;

		}

		return result;

	}
}
