
package services;

import java.util.Collection;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.UserRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountService;
import domain.User;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository	userRepository;


	//Supporting Services -------------------
	@Autowired
	private UserAccountService userAccountService;
	
	@Autowired
	private Validator validator;
	//CRUD Methods -------------------------

	public User create() {
		User res = new User();

		//Collections

		//UserAccount
		UserAccount userAccount = new UserAccount();
		Collection<Authority> authorities = userAccount.getAuthorities();
		Authority authority = new Authority();

		authority.setAuthority(Authority.USER);
		authorities.add(authority);
		userAccount.setAuthorities(authorities);

		res.setUserAccount(userAccount);

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

		if (user.getId() == 0) {
			Md5PasswordEncoder password = new Md5PasswordEncoder();
			String encodedPassword = password.encodePassword(user.getUserAccount().getPassword(), null);
			user.getUserAccount().setPassword(encodedPassword);
			user.setUserAccount(this.userAccountService.save(user.getUserAccount()));
		}
		return this.userRepository.save(user);
	}

	public void delete(final int userId) {
		Assert.isTrue(userId != 0);
		userRepository.delete(userId);
	}

	//Other Business Methods --------------------------------

	public User findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);
		User res;
		res = this.userRepository.findByUserAccount(userAccount.getId());
		return res;
	}

	public User findByPrincipal() {
		User res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = this.findByUserAccount(userAccount);
		return res;
	}
}
