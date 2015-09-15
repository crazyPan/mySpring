package test;

import spring.ApplicationContext;
import entity.ObjectAImpl;

public class MyTest {

	
	public static void main(String[] args) {
		ApplicationContext app = new ApplicationContext("F:/code/mySpring/resources/applicationContext.xml");
		ObjectAImpl a = (ObjectAImpl)app.getBean("objectA");
		a.getB().print();
	}
	
	
}
