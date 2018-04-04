
package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.UserService;
import controllers.AbstractController;
import domain.User;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {

	@Autowired
	private UserService	userService;


	//Constructor
	public UserController() {
		super();
	}

//	@RequestMapping("/list")
//	public ModelAndView list() {
//		ModelAndView result;
//		final List<User> users = new ArrayList<User>(userService.findAll());
//		result = new ModelAndView("user/list");
//		result.addObject("users", users);
//		result.addObject("requestUri", "user/list.do");
//		return result;
//	}
//	
//		@RequestMapping(value = "/create", method = RequestMethod.GET)
//	public ModelAndView create() {
//		ModelAndView result;
//		try {
//			User user = userService.create();
//			result = newEditModelAndView(user);
//		} catch (Throwable oops) {
//			result = new ModelAndView("redirect:list.do");
//		}
//		return result;
//	}
//
//	@RequestMapping(value = "/edit", method = RequestMethod.GET)
//	public ModelAndView edit(@RequestParam(required = true) final int userId) {
//		return newEditModelAndView(user);
//	}
//
//	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
//	public ModelAndView save(@Valid final User user, final BindingResult binding) {
//		ModelAndView result;
//		if (binding.hasErrors())
//			result = newEditModelAndView(user);
//		else
//			try {
//				userService.save(user);
//				result = new ModelAndView("redirect:list.do");
//			} catch (Throwable oops) {
//				result = newEditModelAndView(user);
//				result.addObject("message", "user.commitError");
//			}
//		return result;
//	}
//
//	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "delete")
//	public ModelAndView delete(@Valid final User user, final BindingResult binding) {
//		ModelAndView result;
//		if (binding.hasErrors())
//			result = newEditModelAndView(user);
//		else
//			try {
//				userService.delete(user);
//				result = new ModelAndView("redirect:list.do");
//			} catch (Throwable oops) {
//				result = newEditModelAndView(user);
//				result.addObject("message", "user.commitError");
//			}
//		return result;
//	}
//	protected ModelAndView newEditModelAndView(final User user) {
//		ModelAndView result;
//		result = new ModelAndView("user/edit");
//		result.addObject("user", user);
//		result.addObject("actionUri", "user/save.do");
//		return result;
//	}
}