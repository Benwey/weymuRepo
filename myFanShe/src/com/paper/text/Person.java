package com.paper.text;

public class Person {
	
	public int age;
	public String name;
	private String addr;
	
	public Person(){
		System.out.println("�޲ι���");
	}
	
	public Person(String name ,int age){
		this.age=age;
		this.name=name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void privateMD(String sss){
		System.out.println("˽�з���:");
	}
	
	
	
}
