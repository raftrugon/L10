
package controllers.Customer;

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

import services.SuscriptionService;
import services.CustomerService;
import controllers.AbstractController;
import domain.Subscription;
import domain.Customer;

@Controller
@RequestMapping("/suscription")
public class CustomerSuscriptionController extends AbstractController {

	@Autowired
	private SuscriptionService	suscriptionService;
	@Autowired
	private CustomerService	customerService;


	//Constructor
	public CustomerSuscriptionController() {
		super();
	}

//	@RequestMapping("/list")
//	public ModelAndView list() {
//		ModelAndView result;
//		final List<Subscription> subscriptions = new ArrayList<Subscription>(customerService.findByPrincipal().getSuscriptions());
//		result = new ModelAndView("suscription/list");
//		result.addObject("suscriptions", subscriptions);
//		result.addObject("requestUri", "customer/suscription/list.do");
//		return result;
//	}
//	
//	@RequestMapping(value = "/create", method = RequestMethod.GET)
//	public ModelAndView create() {
//		ModelAndView result;
//		try {
//			Subscription subscription = suscriptionService.create();
//			result = newEditModelAndView(subscription);
//		} catch (Throwable oops) {
//			result = new ModelAndView("redirect:list.do");
//		}
//		return result;
//	}
//
//	@RequestMapping(value = "/edit", method = RequestMethod.GET)
//	public ModelAndView edit(@RequestParam(required = true) final int suscriptionId) {
//		Subscription subscription = suscriptionService.findOne(suscriptionId);
//		if (subscription.getCustomer().equals(customerService.findByPrincipal()))
//			return newEditModelAndView(subscription);
//		else
//			return new ModelAndView("redirect:list.do");
//	}
//
//	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
//	public ModelAndView save(@Valid final Subscription subscription, final BindingResult binding) {
//		ModelAndView result;
//		if (binding.hasErrors())
//			result = newEditModelAndView(subscription);
//		else
//			try {
//				suscriptionService.save(subscription);
//				result = new ModelAndView("redirect:list.do");
//			} catch (Throwable oops) {
//				result = newEditModelAndView(subscription);
//				result.addObject("message", "suscription.commitError");
//			}
//		return result;
//	}
//
//	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "delete")
//	public ModelAndView delete(@Valid final Subscription subscription, final BindingResult binding) {
//		ModelAndView result;
//		if (binding.hasErrors())
//			result = newEditModelAndView(subscription);
//		else
//			try {
//				suscriptionService.delete(subscription);
//				result = new ModelAndView("redirect:list.do");
//			} catch (Throwable oops) {
//				result = newEditModelAndView(subscription);
//				result.addObject("message", "suscription.commitError");
//			}
//		return result;
//	}
//	protected ModelAndView newEditModelAndView(final Subscription subscription) {
//		ModelAndView result;
//		result = new ModelAndView("suscription/edit");
//		result.addObject("suscription", subscription);
//		result.addObject("actionUri", "customer/suscription/save.do");
//		return result;
//	}
}