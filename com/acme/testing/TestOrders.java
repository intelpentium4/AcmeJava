package com.acme.testing;
import com.acme.domain.Order;
import com.acme.domain.Solid;
import com.acme.domain.Good;
import com.acme.domain.Good.UnitOfMeasureType;
import com.acme.domain.Service;
import com.acme.utils.MyDate;
import java.time.LocalDate;
public class TestOrders {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyDate date1 = new MyDate(1,20,2008);
		Solid s1 = new Solid("Acme Anvil", 1668, 0.3, UnitOfMeasureType.CUBIC_METER, false, 500, 0.25, 0.3);
		Order anvil = new Order(date1, 2000.00, "Wile E Coyote", s1, 10);

		MyDate date2 = new MyDate(4,10,2008);
		Solid s2 = new Solid("Acme Balloon", 1401, 15, UnitOfMeasureType.CUBIC_FEET, false, 10, 5, 5);
		Order balloons = new Order(date2, 1000.00, "Bugs Bunny", s2, 125);
		balloons.setQuantity(-200);

		System.out.println(anvil);
		System.out.println(balloons);

		System.out.println("The tax Rate is currently: " + Order.getTaxRate());
		Order.computeTaxOn(3000.00);
		anvil.computeTax();
		balloons.computeTax();
		Order.setTaxRate(0.06);
		System.out.println("The tax Rate is currently: " + Order.getTaxRate());
		Order.computeTaxOn(3000.00);
		anvil.computeTax();
		balloons.computeTax();
		
		MyDate bonusDate2 = new MyDate(5, 20, 2008);
		Order anotherAnvil = new Order(bonusDate2, 200, "Road Runner");
		System.out.println(anotherAnvil);

		System.out.println("The total bill for: " + anvil + " is " + anvil.computeTotal());
		System.out.println("The total bill for: " + balloons + " is " + balloons.computeTotal());

		System.out.println("The volume of the anvil is:  " + ((Good)anvil.getProduct()).volume());
		if(anvil.getProduct() instanceof Solid)
			System.out.println("The length of the anvil is:  " + ((Solid)(anvil.getProduct())).getLength());
		MyDate date3 = new MyDate(4,10,2008);
		Service s3 = new Service("Road Runner Eradication", 14, false);
		Order birdEradication = new Order(date3, 20000, "Daffy Duck", s3, 1);
		System.out.println("The total bill for: " + birdEradication + " is "+ birdEradication.computeTotal());

	//Lambda Expression:
		Order.setRushable( (MyDate orderDate, double orderAmount) -> { 
			LocalDate now = LocalDate.now();
			LocalDate orderPlus1Month = LocalDate.of(orderDate.getYear(), orderDate.getMonth(), orderDate.getDay());
			orderPlus1Month = orderPlus1Month.plusMonths(1);
			return (now.isAfter(orderPlus1Month) || orderAmount > 1500);
		} );
		System.out.println("Anvil isPriorityOrder: " +
		anvil.isPriorityOrder()); // true
		System.out.println("Balloons isPriorityOrder: " +
		balloons.isPriorityOrder()); // false

		// Change this date to one that is within the last 15 days of today.
		LocalDate now = LocalDate.now();
		MyDate hammerDate = new MyDate( now.getMonthValue(), (now.minusDays(15)).getDayOfMonth(), now.getYear() );
		Solid hammerType = new Solid( "Acme Hammer", 281, 0.3,
		UnitOfMeasureType.CUBIC_METER, false, 100, 0.25, 0.3 );
		Order hammer = new Order( hammerDate, 10.00, "Wile E Coyote", hammerType, 10 );
		System.out.println("Hammer isPriorityOrder: " + hammer.isPriorityOrder());
		
		MyDate date4 = new MyDate(1,1,2016);
		Solid s4 = new Solid("Holiday Anvil", 1668, 0.3, UnitOfMeasureType.CUBIC_METER, false, 500, 0.25, 0.3);
		Order holidayAnvil = new Order(date4, 2000.00, "Wile E Coyote", s4, 10);
	}
}
