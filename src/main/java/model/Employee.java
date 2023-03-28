package model;

public class Employee {
	private int primaryid;	
	private String loginid;
	private String name;
	private int age;

	public Employee(int primaryid, String loginid, String name, int age) {
		this.primaryid = primaryid;
		this.loginid = loginid;
		this.name = name;
		this.age = age;
	}

	public int getPrimaryid() {
		return primaryid;
	}

	public void setPrimaryid(int primaryid) {
		this.primaryid = primaryid;
	}

	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}