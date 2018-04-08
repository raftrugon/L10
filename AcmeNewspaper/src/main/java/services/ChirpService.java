package services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import java.util.Collection;
import java.util.Date;

import domain.Chirp;
import repositories.ChirpRepository;
import utilities.internal.SchemaPrinter;

@Service
@Transactional
public class ChirpService {

	@Autowired
	private ChirpRepository		chirpRepository;
	@Autowired
	private UserService userService;
	
	
	//Supporting Services -------------------


	//CRUD Methods -------------------------

	public Chirp create() {
		Chirp res = new Chirp();
		
		res.setCreationMoment(new Date(System.currentTimeMillis() - 1000));
		res.setUser(userService.findByPrincipal());
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
		Assert.notNull(userService.findByPrincipal());
		Assert.isTrue(chirp.getId()==0);
		chirp.setCreationMoment(new Date(System.currentTimeMillis() - 1000));
		
		return chirpRepository.save(chirp);
	}
	
	
}