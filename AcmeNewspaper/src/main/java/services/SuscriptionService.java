package services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import java.util.Collection;
import domain.Subscription;
import repositories.SuscriptionRepository;

@Service
@Transactional
public class SuscriptionService {

	@Autowired
	private SuscriptionRepository		suscriptionRepository;

	//Supporting Services -------------------


	//CRUD Methods -------------------------

	public Subscription create() {
		Subscription res = new Subscription();
		
		return res;
	}
	
	public Subscription findOne(int suscriptionId) {
		Assert.isTrue(suscriptionId != 0);
		Subscription res = suscriptionRepository.findOne(suscriptionId);
		Assert.notNull(res);
		return res;
	}
	
	public Collection<Subscription> findAll() {
		return suscriptionRepository.findAll();
	}
	
	public Subscription save(final Subscription subscription) {
		Assert.notNull(subscription);
		
		
		return suscriptionRepository.save(subscription);
	}
	
	
}