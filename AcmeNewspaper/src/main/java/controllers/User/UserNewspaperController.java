
package controllers.User;

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

import services.NewspaperService;
import services.UserService;
import controllers.AbstractController;
import domain.Newspaper;
import domain.User;

@Controller
@RequestMapping("user/newspaper")
public class UserNewspaperController extends AbstractController {

	@Autowired
	private NewspaperService	newspaperService;
	@Autowired
	private UserService	userService;


	//Constructor
	public UserNewspaperController() {
		super();
	}
//
//	@RequestMapping("/list")
//	public ModelAndView list() {
//		ModelAndView result;
//		final List<Newspaper> newspapers = new ArrayList<Newspaper>(userService.findByPrincipal().getNewspapers());
//		result = new ModelAndView("newspaper/list");
//		result.addObject("newspapers", newspapers);
//		result.addObject("requestUri", "user/newspaper/list.do");
//		return result;
//	}
//	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		try {
			Newspaper newspaper = newspaperService.create();
			result = newEditModelAndView(newspaper);
		} catch (Throwable oops) {
			result = new ModelAndView("redirect:list.do");
		}
		return result;
	}
//
//	@RequestMapping(value = "/edit", method = RequestMethod.GET)
//	public ModelAndView edit(@RequestParam(required = true) final int newspaperId) {
//		Newspaper newspaper = newspaperService.findOne(newspaperId);
//		if (newspaper.getUser().equals(userService.findByPrincipal()))
//			return newEditModelAndView(newspaper);
//		else
//			return new ModelAndView("redirect:list.do");
//	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Newspaper newspaper, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = newEditModelAndView(newspaper);
			System.out.println(binding.toString());
		}
		else
			try {
				newspaperService.save(newspaper);
				result = new ModelAndView("redirect:../../newspaper/list.do");
			} catch (Throwable oops) {
				result = newEditModelAndView(newspaper);
				result.addObject("message", "newspaper.commitError");
				System.out.println(oops.getMessage());
			}
		return result;
	}
//
//	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "delete")
//	public ModelAndView delete(@Valid final Newspaper newspaper, final BindingResult binding) {
//		ModelAndView result;
//		if (binding.hasErrors())
//			result = newEditModelAndView(newspaper);
//		else
//			try {
//				newspaperService.delete(newspaper);
//				result = new ModelAndView("redirect:list.do");
//			} catch (Throwable oops) {
//				result = newEditModelAndView(newspaper);
//				result.addObject("message", "newspaper.commitError");
//			}
//		return result;
//	}
	protected ModelAndView newEditModelAndView(final Newspaper newspaper) {
		ModelAndView result;
		result = new ModelAndView("newspaper/edit");
		result.addObject("newspaper", newspaper);
		result.addObject("actionUri", "user/newspaper/save.do");
		return result;
	}
}