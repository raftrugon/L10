
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import domain.Actor;

@Service
@Transactional
public class ActorService {

	@Autowired
	private ActorRepository	actorRepository;


	//Supporting Services -------------------

	//CRUD Methods -------------------------

	public Actor findOne(int actorId) {
		Assert.isTrue(actorId != 0);
		Actor res = actorRepository.findOne(actorId);
		Assert.notNull(res);
		return res;
	}

	public Actor save(final Actor actor) {
		Assert.notNull(actor);

		return actorRepository.save(actor);
	}

	//Other Business Methods --------------------------------

}
