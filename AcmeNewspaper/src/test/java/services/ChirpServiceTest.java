
package services;

import javax.transaction.Transactional;

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
@Transactional
public class ChirpServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private ChirpService	chirpService;


	//Supporting services -----------------------------------------------------

	// Tests ------------------------------------------------------------------
	@Test
	public void driverFindOne() {

		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST FIND ONE CHIRP==================================================");
		System.out.println("===============================================================================================================\r");

		Object testingData[][] = {
			//Positive test
			{
				getEntityId("chirp1"), null, "Búsqueda del find One correcto"
			},

			//Find one newspaper using another role id
			{
				getEntityId("newspaper1"), IllegalArgumentException.class, "Intentando buscar un chirp"
			},
		};

		for (int i = 0; i < testingData.length; i++)
			templateFindOne((int) testingData[i][0], (Class<?>) testingData[i][1], (String) testingData[i][2]);
	}

	protected void templateFindOne(int chirpId, Class<?> expected, String explanation) {

		Class<?> caught = null;

		try {
			chirpService.findOne(chirpId);

		} catch (Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);

		if (expected == null)
			System.out.println("---------------------------- POSITIVO ---------------------------");
		else
			System.out.println("---------------------------- NEGATIVO ---------------------------");
		System.out.println("Explicación: " + explanation);
		System.out.println("ChirpId: " + chirpId);
		System.out.println("\r¿Correcto? " + (expected == caught));
		System.out.println("-----------------------------------------------------------------\r");

	}
	
	@Test
	public void driverCreate(){
		
		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST CREATE CHIRP=======================================================");
		System.out.println("===============================================================================================================\r");
		Object testingData[][] = {
				//Test positivo
				{
					"user1", null, "Creación correcta de un chirp"
				},
				//Intento de crear con un rol que no debe
				{
					"customer1", IllegalArgumentException.class, "Se ha intentado crear un newspaper con un customer1"
				},
				//Create sin logearse
				{
					null, IllegalArgumentException.class, "Intento de crear un chirp sin estar logeado"
				}
		};
		
		for(int i =0; i<testingData.length;i++)
			templateCreate((String) testingData[i][0], (Class<?>)testingData[i][1], (String) testingData[i][2]);
	}
	protected void templateCreate(String rol, Class<?> expected, String explanation){
		Class<?> caught = null;
		
		try{
			super.authenticate(rol);
			chirpService.create();
			super.unauthenticate();
		}catch (Throwable oops){
			caught = oops.getClass();
		}
		
		checkExceptions(expected, caught);
		
		if(expected==null)
			System.out.println("-----------------POSITIVO------------");
		else
			System.out.println("-----------------NEGATIVO------------");
		System.out.println("Explicacion: " + explanation);
		System.out.println("Rol: " + rol);
		System.out.println("\r¿Correcto? " +(expected==caught));
	}

}
