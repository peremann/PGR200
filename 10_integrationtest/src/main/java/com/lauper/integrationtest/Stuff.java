package com.lauper.integrationtest;

public class Stuff {
	private int pk;
	private String name;
	private int value;
	
	public Stuff(int pk, String name, int value) {
		this.pk = pk;
		this.name = name;
		this.value = value;
	}

    public int getPk() {
		return pk;
	}
	public void setPk(int pk) {
		this.pk = pk;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "Stuff [pk=" + pk + ", name=" + name + ", value=" + value + "]";
	}
	
	
}
