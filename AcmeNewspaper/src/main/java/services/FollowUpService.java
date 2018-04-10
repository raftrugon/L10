package services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import java.util.Collection;
import domain.FollowUp;
import repositories.FollowUpRepository;

@Service
@Transactional
public class FollowUpService {

	@Autowired
	private FollowUpRepository		followUpRepository;

	//Supporting Services -------------------


	//CRUD Methods -------------------------

	public FollowUp create() {
		FollowUp res = new FollowUp();
		
		return res;
	}
	
	public FollowUp findOne(int followUpId) {
		Assert.isTrue(followUpId != 0);
		FollowUp res = followUpRepository.findOne(followUpId);
		Assert.notNull(res);
		return res;
	}
	
	public Collection<FollowUp> findAll() {
		return followUpRepository.findAll();
	}
	
	public FollowUp save(final FollowUp followUp) {
		Assert.notNull(followUp);
		
		
		return followUpRepository.save(followUp);
	}
	
	public void delete(final int followUpId) {
		Assert.isTrue(followUpId != 0);
		followUpRepository.delete(followUpId);
	}
	
	
}