
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.UserRepository;
import domain.User;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository	userRepository;


	//Supporting Services -------------------

	//CRUD Methods -------------------------

	public User create() {
		User res = new User();

		return res;
	}

	public void flush() {
		this.userRepository.flush();
	}

	public User findOne(final int userId) {
		Assert.isTrue(userId != 0);
		User res = this.userRepository.findOne(userId);
		Assert.notNull(res);
		return res;
	}

	public Collection<User> findAll() {
		Collection<User> res = this.userRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public User save(final User user) {
		Assert.notNull(user);

		return userRepository.save(user);
	}

	public void delete(final int userId) {
		Assert.isTrue(userId != 0);
		userRepository.delete(userId);
	}

}
