//Program to demonstrate simple class in Java
package encapsulation;

public class OopsConceptDemo {
	// Var declaration / Data members
	private int serialNum; // int, string, boolean, float = Primitive Data types
	private String name; // private , public, protected and default = Access modifiers / specifiers
	private int age;

	// Getters and Setters method
	public int getSerialNum() { // is used to get the var name & its return value
		return serialNum;
	}

	public void setSerialNum(int serialNum) { // is used to set the var value
		this.serialNum = serialNum;
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

	// Object class method - to returns string representation of the object
	@Override
	public String toString() {
		return "OopsConceptDemo [Serial Number=" + serialNum + ", name=" + name + ", age=" + age + "]";
	}

}