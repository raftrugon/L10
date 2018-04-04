package services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import java.util.Collection;
import domain.Chirp;
import repositories.ChirpRepository;

@Service
@Transactional
public class ChirpService {

	@Autowired
	private ChirpRepository		chirpRepository;

	//Supporting Services -------------------


	//CRUD Methods -------------------------

	public Chirp create() {
		Chirp res = new Chirp();
		
		return res;
	}
	
	public Chirp findOne(int chirpId) {
		Assert.isTrue(chirpId != 0);
		Chirp res = chirpRepository.findOne(chirpId);
		Assert.notNull(res);
		return res;
	}
	
	public Collection<Chirp> findAll() {
		return chirpRepository.findAll();
	}
	
	public Chirp save(final Chirp chirp) {
		Assert.notNull(chirp);
		
		
		return chirpRepository.save(chirp);
	}
	
	
}