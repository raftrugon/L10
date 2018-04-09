
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

import services.ChirpService;
import services.UserService;
import utilities.internal.SchemaPrinter;
import controllers.AbstractController;
import domain.Chirp;
import domain.User;

@Controller
@RequestMapping("user/chirp")
public class UserChirpController extends AbstractController {

	@Autowired
	private ChirpService	chirpService;
	@Autowired
	private UserService	userService;


	//Constructor
	public UserChirpController() {
		super();
	}

	@RequestMapping("/timeline")
	public ModelAndView timeline() {
		ModelAndView result;
		final List<Chirp> chirps = new ArrayList<Chirp>(chirpService.getTimeline());
		result = new ModelAndView("chirp/list");
		result.addObject("chirps", chirps);
		result.addObject("requestUri", "user/chirp/timeline.do");
		return result;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		try {
			Chirp chirp = chirpService.create();
			result = newEditModelAndView(chirp);
		} catch (Throwable oops) {
			result = new ModelAndView("redirect:list.do");
			System.out.println(oops.getMessage());
		}
		return result;
	}
//
//	@RequestMapping(value = "/edit", method = RequestMethod.GET)
//	public ModelAndView edit(@RequestParam(required = true) final int chirpId) {
//		Chirp chirp = chirpService.findOne(chirpId);
//		if (chirp.getUser().equals(userService.findByPrincipal()))
//			return newEditModelAndView(chirp);
//		else
//			return new ModelAndView("redirect:list.do");
//	}
//
	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Chirp chirp, final BindingResult binding) {
		ModelAndView result;
		Chirp validated = chirpService.reconstruct(chirp, binding);
		if (binding.hasErrors()){
			result = newEditModelAndView(chirp);
		}
		else
			try {
				chirpService.save(validated);
				result = new ModelAndView("redirect:../../");
			} catch (Throwable oops) {
				result = newEditModelAndView(chirp);
				result.addObject("message", "chirp.commitError");
			}
		return result;
	}
//
//	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "delete")
//	public ModelAndView delete(@Valid final Chirp chirp, final BindingResult binding) {
//		ModelAndView result;
//		if (binding.hasErrors())
//			result = newEditModelAndView(chirp);
//		else
//			try {
//				chirpService.delete(chirp);
//				result = new ModelAndView("redirect:list.do");
//			} catch (Throwable oops) {
//				result = newEditModelAndView(chirp);
//				result.addObject("message", "chirp.commitError");
//			}
//		return result;
//	}
	protected ModelAndView newEditModelAndView(final Chirp chirp) {
		ModelAndView result;
		result = new ModelAndView("chirp/edit");
		result.addObject("chirp", chirp);
		result.addObject("actionUri", "user/chirp/save.do");
		return result;
	}
}