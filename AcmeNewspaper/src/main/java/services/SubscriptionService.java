package services;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.SubscriptionRepository;
import domain.Subscription;

@Service
@Transactional
public class SubscriptionService {

	@Autowired
	private SubscriptionRepository		subscriptionRepository;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private Validator validator;
	@Autowired
	private NewspaperService newspaperService;

	//Supporting Services -------------------


	//CRUD Methods -------------------------

	public Subscription create(int newspaperId) {
		Subscription res = new Subscription();
		res.setNewspaper(newspaperService.findOne(newspaperId));		
		return res;
	}
	
	public Subscription findOne(int subscriptionId) {
		Assert.isTrue(subscriptionId != 0);
		Subscription res = subscriptionRepository.findOne(subscriptionId);
		Assert.notNull(res);
		return res;
	}
	
	public Collection<Subscription> findAll() {
		return subscriptionRepository.findAll();
	}
	
	public Subscription save(final Subscription subscription) {
		Assert.notNull(subscription);
		
		
		return subscriptionRepository.save(subscription);
	}

	public Subscription reconstruct(Subscription subscription, BindingResult binding) {
		subscription.setId(0);
		subscription.setCustomer(customerService.findByPrincipal());
		validator.validate(subscription, binding);
		return subscription;		
	}
	
	
}