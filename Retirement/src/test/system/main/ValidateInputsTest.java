package test.system.main;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import application.main.ValidateInputs;

public class ValidateInputsTest {
	/**
     * Create a mock variable.
     * 
     * @var XMLReader    xmlReader
     */
	@Mock
    private transient ValidateInputs validateInputs;
           
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void instanceNotNull() {
		assertNotNull("Ensure mock isn't null", validateInputs);
	}
/*	
	@Test
	public void personalCurrentAge() {
		Mockito.when(validateInputs.personalCurrentAge(any())).thenReturn(false);
		
		Personal personal = new Personal();
		personalCurrentAge(new Personal());
		
		assertNotNull(validateInputs);
	}
*/
}
