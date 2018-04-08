package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.UserService;
import domain.User;

@Controller
@RequestMapping("/register")
public class RegisterController extends AbstractController {

	@Autowired
	private UserService			userService;
	/*@Autowired
	private ManagerService		managerService;*/


	//Create Edit GET
	@RequestMapping(value = "user", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		try {
			result = this.newEditModelAndView(this.userService.create());
		} catch (Throwable oops) {
			result = new ModelAndView("redirect:list.do");
		}
		return result;
	}

	//Save Delete POST
	@RequestMapping(value = "user", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final User user, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.newEditModelAndView(user);
		else
			try {
				User saved = this.userService.save(user);
				result = new ModelAndView("redirect:../user-display.do?userId=" + saved.getId());
			} catch (Throwable oops) {
				oops.printStackTrace();
				result = this.newEditModelAndView(user);
				result.addObject("message", "user.commitError");
			}
		return result;
	}

	protected ModelAndView newEditModelAndView(final User user) {
		ModelAndView result;
		result = this.newEditModelAndView(user, null);
		return result;
	}

	protected ModelAndView newEditModelAndView(final User user, final String message) {
		ModelAndView result;
		result = new ModelAndView("register/user");
		result.addObject("user", user);
		result.addObject("message", message);
		result.addObject("actionUri", "user/save.do");
		return result;
	}

}
