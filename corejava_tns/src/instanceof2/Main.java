package instanceof2;
class Person {
    // Base class
}

class Student extends Person {
    // Student is a subclass of Person
}

public class Main {
    public static void main(String[] args) {
        Person p1 = new Student();  // Upcasting Student to Person
        Person p2 = new Person();   // Just a Person

        // Check if p1 is a Student
        if (p1 instanceof Student) {
            System.out.println("p1 is a Student");
        }

        // Check if p2 is a Student
        if (p2 instanceof Student) {
            System.out.println("p2 is a Student");
        } else {
            System.out.println("p2 is NOT a Student");
        }
    }
}