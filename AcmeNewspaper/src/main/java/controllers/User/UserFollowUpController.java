
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

import services.FollowUpService;
import services.UserService;
import controllers.AbstractController;
import domain.FollowUp;
import domain.User;

@Controller
@RequestMapping("/followUp")
public class UserFollowUpController extends AbstractController {

	@Autowired
	private FollowUpService	followUpService;
	@Autowired
	private UserService	userService;


	//Constructor
	public UserFollowUpController() {
		super();
	}

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;
		final List<FollowUp> followUps = new ArrayList<FollowUp>(userService.findByPrincipal().getFollowUps());
		result = new ModelAndView("followUp/list");
		result.addObject("followUps", followUps);
		result.addObject("requestUri", "user/followUp/list.do");
		return result;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		try {
			FollowUp followUp = followUpService.create();
			result = newEditModelAndView(followUp);
		} catch (Throwable oops) {
			result = new ModelAndView("redirect:list.do");
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam(required = true) final int followUpId) {
		FollowUp followUp = followUpService.findOne(followUpId);
		if (followUp.getUser().equals(userService.findByPrincipal()))
			return newEditModelAndView(followUp);
		else
			return new ModelAndView("redirect:list.do");
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final FollowUp followUp, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = newEditModelAndView(followUp);
		else
			try {
				followUpService.save(followUp);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = newEditModelAndView(followUp);
				result.addObject("message", "followUp.commitError");
			}
		return result;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final FollowUp followUp, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = newEditModelAndView(followUp);
		else
			try {
				followUpService.delete(followUp);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = newEditModelAndView(followUp);
				result.addObject("message", "followUp.commitError");
			}
		return result;
	}
	protected ModelAndView newEditModelAndView(final FollowUp followUp) {
		ModelAndView result;
		result = new ModelAndView("followUp/edit");
		result.addObject("followUp", followUp);
		result.addObject("actionUri", "user/followUp/save.do");
		return result;
	}
}