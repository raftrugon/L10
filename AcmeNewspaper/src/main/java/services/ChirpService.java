
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ChirpRepository;
import domain.Chirp;

@Service
@Transactional
public class ChirpService {

	@Autowired
	private ChirpRepository	chirpRepository;
	@Autowired
	private UserService		userService;
	@Autowired
	private AdminService	adminService;


	//Supporting Services -------------------

	//CRUD Methods -------------------------

	public Chirp create() {
		Chirp res = new Chirp();

		res.setCreationMoment(new Date(System.currentTimeMillis() - 1000));
		res.setInappropriate(false);
		Assert.isTrue(userService.findByPrincipal() instanceof domain.User);
		res.setUser(userService.findByPrincipal());
		res.setInappropriate(false);
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
		Assert.isTrue(chirp.getId() == 0);
		chirp.setCreationMoment(new Date(System.currentTimeMillis() - 1000));

		return chirpRepository.save(chirp);
	}

	public Collection<Chirp> getTimeline() {
		Assert.isTrue(userService.findByPrincipal() instanceof domain.User);
		return chirpRepository.getTimeline(userService.findByPrincipal().getFollows());
	}

	public Collection<Chirp> findAllTaboo() {
		return chirpRepository.findAllTaboo();
	}

	public void markAsInappropriate(int chirpId) {
		Assert.notNull(adminService.findByPrincipal());
		Chirp c = findOne(chirpId);
		Assert.isTrue(c instanceof domain.Chirp);
		c.setInappropriate(true);
		chirpRepository.save(c);
	}
	public void flush() {
		chirpRepository.flush();
	}

}
