package services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import java.util.Collection;
import domain.Customer;
import repositories.CustomerRepository;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository		customerRepository;

	//Supporting Services -------------------


	//CRUD Methods -------------------------

	public Customer create() {
		Customer res = new Customer();
		
		return res;
	}
	
	public Customer findOne(int customerId) {
		Assert.isTrue(customerId != 0);
		Customer res = customerRepository.findOne(customerId);
		Assert.notNull(res);
		return res;
	}
	
	public Collection<Customer> findAll() {
		return customerRepository.findAll();
	}
	
	public Customer save(final Customer customer) {
		Assert.notNull(customer);
		
		
		return customerRepository.save(customer);
	}
	
	
}