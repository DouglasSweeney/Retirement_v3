package test.system.main;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import application.main.XmlWriter;

public class XmlWriterTest {
	final static double EPSILON = 0.01;

	/**
     * Create a variable.
     * 
     * @var XMLWriter    xmlWriter
     */
    final XmlWriter xmlWriter = new XmlWriter();
	
	@Test
	public void testXmlWriter() {
	    final XmlWriter xmlWriter = new XmlWriter();
	    
	    xmlWriter.save("input_xmls/temp.xml");
	    xmlWriter.putHeader();
	    xmlWriter.putTopLevelNode();
	   
	    xmlWriter.saveAccount401k(xmlWriter);
	    xmlWriter.saveAccount403b(xmlWriter);
	    xmlWriter.saveCashBalance(xmlWriter);
	    xmlWriter.saveIra(xmlWriter);
	    xmlWriter.saveRoth(xmlWriter);
	    xmlWriter.saveBrokerage(xmlWriter);
	    xmlWriter.saveDeductions(xmlWriter);
	    xmlWriter.saveExpenses(xmlWriter);
	    xmlWriter.savePension(xmlWriter);
	    xmlWriter.savePersonal(xmlWriter);
	    xmlWriter.saveSalary(xmlWriter);
	    xmlWriter.saveSocialSecurity(xmlWriter);
	    xmlWriter.saveTaxes(xmlWriter);
	 
	    xmlWriter.putCloseTopLevelNode();
	    xmlWriter.close();
	    
	    assertNotNull("Ensure that a variable is not null", xmlWriter);
	  }
}
