
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CustomerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountService;
import domain.Customer;
import domain.Newspaper;
import domain.Subscription;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository	customerRepository;


	//Supporting Services -------------------
	@Autowired
	private UserAccountService	userAccountService;
	//CRUD Methods -------------------------

	public Customer create() {
		Customer res = new Customer();

		//Collections
		res.setSubscriptionss(new ArrayList<Subscription>());
		//UserAccount
		UserAccount userAccount = new UserAccount();
		Collection<Authority> authorities = userAccount.getAuthorities();
		Authority authority = new Authority();

		authority.setAuthority(Authority.CUSTOMER);
		authorities.add(authority);
		userAccount.setAuthorities(authorities);

		res.setUserAccount(userAccount);

		return res;
	}

	public void flush() {
		this.customerRepository.flush();
	}

	public Customer findOne(final int customerId) {
		Assert.isTrue(customerId != 0);
		Customer res = this.customerRepository.findOne(customerId);
		Assert.notNull(res);
		return res;
	}

	public Collection<Customer> findAll() {
		Collection<Customer> res = this.customerRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Customer save(final Customer customer) {
		Assert.notNull(customer);

		if (customer.getId() == 0) {
			Md5PasswordEncoder password = new Md5PasswordEncoder();
			String encodedPassword = password.encodePassword(customer.getUserAccount().getPassword(), null);
			customer.getUserAccount().setPassword(encodedPassword);
			customer.setUserAccount(this.userAccountService.save(customer.getUserAccount()));
		}

		return this.customerRepository.save(customer);
	}

	public Customer findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);
		Customer res;
		res = this.customerRepository.findByUserAccount(userAccount.getId());
		return res;
	}

	public Customer findByPrincipal() {
		Customer res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = this.findByUserAccount(userAccount);
		return res;
	}

	public boolean isSubscribed(final Newspaper newspaper) {
		return this.customerRepository.isSubscribed(newspaper,this.findByPrincipal()) > 0 ? true : false;
	}

}
