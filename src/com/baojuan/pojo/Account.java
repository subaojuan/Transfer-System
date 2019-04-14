package com.baojuan.pojo;

public class Account {
	private int id;
	private String accNo;
	private int password;
	private double balance;
	@Override
	public String toString() {
		return "account [id=" + id + ", accno=" + accNo + ", password=" + password + ", balance=" + balance + ", name="
				+ Name + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccno() {
		return accNo;
	}
	public void setAccno(String accno) {
		this.accNo = accno;
	}
	public int getPassword() {
		return password;
	}
	public void setPassword(int i) {
		this.password = i;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		this.Name = name;
	}
	private String Name;
}
