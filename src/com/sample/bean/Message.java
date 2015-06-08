package com.sample.bean;

/**
 * reasoning fact message
 * @author liu
 */
public class Message {
	public Message(Integer number, double r) {
		a = number;
		this.r = r;
	}
	public Message(Integer number) {
		a = number;
	}
	
	private Integer a;

	public Integer isA() {
		return a;
	}
	
	private double r = 1.0;
	
	public double getR() {
		return r;
	}

	public void setR(double r) {
		this.r = r;
	}
}
