package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
public class CustomerServiceTest extends AbstractTest{
	
	// System under test ------------------------------------------------------
	@Autowired
	private CustomerService		customerService;

	//Supporting services -----------------------------------------------------


	// Tests ------------------------------------------------------------------
	@Test
	public void driverFindOne() {
		
		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST FIND ONE CUSTOMER==================================================");
		System.out.println("===============================================================================================================\r");

		Object testingData[][] = {
			//Positive test
			{
				getEntityId("customer1"), null, "B�squeda del find One correcto"
			},

			//Find one newspaper using another role id
			{
				getEntityId("newspaper1"), IllegalArgumentException.class, "Intentando buscar un newspaper"
			},
		};

		for (int i = 0; i < testingData.length; i++)
			templateFindOne((int) testingData[i][0], (Class<?>) testingData[i][1], (String) testingData[i][2]);
	}

	protected void templateFindOne(int customerid, Class<?> expected, String explanation) {

		Class<?> caught = null;

		try {
			customerService.findOne(customerid);

		} catch (Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);
		
		if(expected == null)
			System.out.println("---------------------------- POSITIVO ---------------------------");
		else
			System.out.println("---------------------------- NEGATIVO ---------------------------");
		System.out.println("Explicaci�n: " + explanation);
		System.out.println("Customerid: " + customerid);
		System.out.println("\r�Correcto? " + (expected == caught));
		System.out.println("-----------------------------------------------------------------\r");

	}
	
}