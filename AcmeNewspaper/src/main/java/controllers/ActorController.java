
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.ActorService;

@Controller
@RequestMapping("/actor")
public class ActorController extends AbstractController {

	@Autowired
	private ActorService	actorService;


	//Constructor
	public ActorController() {
		super();
	}
//
//	@RequestMapping("/list")
//	public ModelAndView list() {
//		ModelAndView result;
//		final List<Actor> actors = new ArrayList<Actor>(actorService.findAll());
//		result = new ModelAndView("actor/list");
//		result.addObject("actors", actors);
//		result.addObject("requestUri", "actor/list.do");
//		return result;
//	}
//	
//		@RequestMapping(value = "/create", method = RequestMethod.GET)
//	public ModelAndView create() {
//		ModelAndView result;
//		try {
//			Actor actor = actorService.create();
//			result = newEditModelAndView(actor);
//		} catch (Throwable oops) {
//			result = new ModelAndView("redirect:list.do");
//		}
//		return result;
//	}
//
//	@RequestMapping(value = "/edit", method = RequestMethod.GET)
//	public ModelAndView edit(@RequestParam(required = true) final int actorId) {
//		return newEditModelAndView(actor);
//	}
//
//	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
//	public ModelAndView save(@Valid final Actor actor, final BindingResult binding) {
//		ModelAndView result;
//		if (binding.hasErrors())
//			result = newEditModelAndView(actor);
//		else
//			try {
//				actorService.save(actor);
//				result = new ModelAndView("redirect:list.do");
//			} catch (Throwable oops) {
//				result = newEditModelAndView(actor);
//				result.addObject("message", "actor.commitError");
//			}
//		return result;
//	}
//
//	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "delete")
//	public ModelAndView delete(@Valid final Actor actor, final BindingResult binding) {
//		ModelAndView result;
//		if (binding.hasErrors())
//			result = newEditModelAndView(actor);
//		else
//			try {
//				actorService.delete(actor);
//				result = new ModelAndView("redirect:list.do");
//			} catch (Throwable oops) {
//				result = newEditModelAndView(actor);
//				result.addObject("message", "actor.commitError");
//			}
//		return result;
//	}
//	protected ModelAndView newEditModelAndView(final Actor actor) {
//		ModelAndView result;
//		result = new ModelAndView("actor/edit");
//		result.addObject("actor", actor);
//		result.addObject("actionUri", "actor/save.do");
//		return result;
//	}
}