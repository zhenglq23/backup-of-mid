package jtest1;

import junit.framework.Test; 
import junit.framework.TestCase;
import junit.framework.TestSuite;
import static junit.framework.Assert.*;

import jtest1.HelloWorld;

public class TestHw extends TestCase{
	public void testnull(){
		HelloWorld Hw = new HelloWorld();
		assertNotNull(Hw);
	}
	
	public static Test suite(){
		return new TestSuite(TestHw.class);
	}
	
	public static void main(String[] args){
		junit.textui.TestRunner.run(suite());
	}
	
	
}
