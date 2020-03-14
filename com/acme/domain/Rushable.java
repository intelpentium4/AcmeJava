package com.acme.domain;
import com.acme.utils.MyDate;
/**
 * This functional interface allows orders to be flagged as "priority" to be processed quickly.
 * @author Pi
 */
public interface Rushable {
	public abstract boolean isRushable(MyDate orderDate, double amount);
}