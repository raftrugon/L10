
package controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/actor")
public class ActorController extends AbstractController {

	//Constructor
	public ActorController() {
		super();
	}
}