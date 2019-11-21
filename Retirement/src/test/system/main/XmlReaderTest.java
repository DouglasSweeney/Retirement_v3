package test.system.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import application.main.XmlReader;

public class XmlReaderTest {
	final static double EPSILON = 0.01;

	/**
     * Create a variable.
     * 
     * @var XMLReader    xmlReader
     */
    final transient XmlReader xmlReader = new XmlReader();
           
	@Test
	public void testHappyCase() {
	   xmlReader.create("input_xmls/demo.xml");
		    
	   xmlReader.readAccount401k();
	   xmlReader.readAccount403b();
	   xmlReader.readAccountCashBalance();
	   xmlReader.readAccountIra();
	   xmlReader.readAccountRoth();
	   xmlReader.readBrokerage();
	   xmlReader.readDeductions();
	   xmlReader.readExpenses();
	   xmlReader.readPension();
	   xmlReader.readPersonal();
	   xmlReader.readSalary();
	   xmlReader.readSocialSecurity();
	   xmlReader.readTaxes();
	   
	   assertNotNull("Ensure the object is not null", xmlReader);
	}
	
	@Test
	public void testXmlReader401K1() {
	   xmlReader.create("input_xmls/demo.xml");
		    
	   xmlReader.readAccount401k();
	   
	   assertEquals(0.0,  xmlReader.getDoubleProperty("account401kAnnualContributions"), EPSILON);
	}
	
	@Test
	public void testXmlReader401K2() {
	   xmlReader.create("input_xmls/demo.xml");
		    
	   xmlReader.readAccount401k();
	   
	   assertEquals(354_000.0, xmlReader.getDoubleProperty("account401kBalance"), EPSILON);
	}
	
	@Test
	public void testXmlReader401K3() {
	   xmlReader.create("input_xmls/demo.xml");
		    
	   xmlReader.readAccount401k();
	   
	   assertEquals(0.04, xmlReader.getDoubleProperty("account401kGrowthRate"), EPSILON);
	}
	
	@Test
	public void testXmlReader401K4() {
	   xmlReader.create("input_xmls/demo.xml");
		    
	   xmlReader.readAccount401k();
	   
	   assertEquals(12, xmlReader.getIntegerProperty("account401kNumberOfWithdrawals"), EPSILON);
	}
	
	@Test
	public void testXmlReader401K5() {
	   xmlReader.create("input_xmls/demo.xml");
		    
	   xmlReader.readAccount401k();
	   
	   assertEquals(58, xmlReader.getIntegerProperty("account401kStartWithdrawalAge"), EPSILON);
	}
}