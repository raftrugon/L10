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
public class ArticleServiceTest extends AbstractTest{
	
	// System under test ------------------------------------------------------
	@Autowired
	private ArticleService		articleService;

	//Supporting services -----------------------------------------------------


	// Tests ------------------------------------------------------------------
	@Test
	public void driverFindOne() {
		
		System.out.println("===============================================================================================================");
		System.out.println("=====================================TEST FIND ONE ARTICLE==================================================");
		System.out.println("===============================================================================================================\r");

		Object testingData[][] = {
			//Positive test
			{
				getEntityId("article1"), null, "Búsqueda del find One correcto"
			},

			//Find one newspaper using another role id
			{
				getEntityId("newspaper1"), IllegalArgumentException.class, "Intentando buscar un newspaper"
			},
		};

		for (int i = 0; i < testingData.length; i++)
			templateFindOne((int) testingData[i][0], (Class<?>) testingData[i][1], (String) testingData[i][2]);
	}

	protected void templateFindOne(int articleId, Class<?> expected, String explanation) {

		Class<?> caught = null;

		try {
			articleService.findOne(articleId);

		} catch (Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);
		
		if(expected == null)
			System.out.println("---------------------------- POSITIVO ---------------------------");
		else
			System.out.println("---------------------------- NEGATIVO ---------------------------");
		System.out.println("Explicación: " + explanation);
		System.out.println("ArticleId: " + articleId);
		System.out.println("\r¿Correcto? " + (expected == caught));
		System.out.println("-----------------------------------------------------------------\r");

	}
	
}