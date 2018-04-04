package services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import java.util.Collection;
import domain.User;
import repositories.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository		userRepository;

	//Supporting Services -------------------


	//CRUD Methods -------------------------

	public User create() {
		User res = new User();
		
		return res;
	}
	
	public User findOne(int userId) {
		Assert.isTrue(userId != 0);
		User res = userRepository.findOne(userId);
		Assert.notNull(res);
		return res;
	}
	
	public Collection<User> findAll() {
		return userRepository.findAll();
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