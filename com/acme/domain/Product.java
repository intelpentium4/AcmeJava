package com.acme.domain;

/**
 * This interface provides the common fields for product and goods
 * @author Pi
 */
public interface Product {
	public abstract String getName();
	public abstract void setName(String n);
	public abstract String toString();
}