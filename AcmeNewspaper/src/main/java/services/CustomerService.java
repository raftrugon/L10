
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CustomerRepository;
import security.LoginService;
import security.UserAccount;
import domain.Customer;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository	customerRepository;


	//Supporting Services -------------------

	//CRUD Methods -------------------------

	public Customer create() {
		Customer res = new Customer();

		return res;
	}

	public void flush() {
		customerRepository.flush();
	}

	public Customer findOne(final int customerId) {
		Assert.isTrue(customerId != 0);
		Customer res = customerRepository.findOne(customerId);
		Assert.notNull(res);
		return res;
	}

	public Collection<Customer> findAll() {
		Collection<Customer> res = customerRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Customer save(final Customer customer) {
		Assert.notNull(customer);

		return customerRepository.save(customer);
	}

	public Customer findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);
		Customer res;
		res = customerRepository.findByUserAccount(userAccount.getId());
		return res;
	}

	public Customer findByPrincipal() {
		Customer res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = findByUserAccount(userAccount);
		return res;
	}

}
