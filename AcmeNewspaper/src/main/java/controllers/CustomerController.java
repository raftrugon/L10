
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

import services.CustomerService;
import controllers.AbstractController;
import domain.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController extends AbstractController {

	@Autowired
	private CustomerService	customerService;


	//Constructor
	public CustomerController() {
		super();
	}

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;
		final List<Customer> customers = new ArrayList<Customer>(customerService.findAll());
		result = new ModelAndView("customer/list");
		result.addObject("customers", customers);
		result.addObject("requestUri", "customer/list.do");
		return result;
	}
	
		@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		try {
			Customer customer = customerService.create();
			result = newEditModelAndView(customer);
		} catch (Throwable oops) {
			result = new ModelAndView("redirect:list.do");
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam(required = true) final int customerId) {
		return newEditModelAndView(customer);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Customer customer, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = newEditModelAndView(customer);
		else
			try {
				customerService.save(customer);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = newEditModelAndView(customer);
				result.addObject("message", "customer.commitError");
			}
		return result;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final Customer customer, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = newEditModelAndView(customer);
		else
			try {
				customerService.delete(customer);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = newEditModelAndView(customer);
				result.addObject("message", "customer.commitError");
			}
		return result;
	}
	protected ModelAndView newEditModelAndView(final Customer customer) {
		ModelAndView result;
		result = new ModelAndView("customer/edit");
		result.addObject("customer", customer);
		result.addObject("actionUri", "customer/save.do");
		return result;
	}
}