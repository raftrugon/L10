
package services;

import java.util.ArrayList;
import java.util.Collection;

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
import domain.Newspaper;
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
	private ActorService actorService;

	//CRUD Methods -------------------------

	public User create() {
		User res = new User();

		//Collections
		Collection<String> emailss = new ArrayList<String>();
		Collection<String> addressess = new ArrayList<String>();
		Collection<String> phoness = new ArrayList<String>();
		Collection<Newspaper> newspapers = new ArrayList<Newspaper>();

		res.setEmailss(emailss);
		res.setAddressess(addressess);
		res.setPhoness(phoness);
		res.setNewspapers(newspapers);

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
		Assert.isTrue(user.getId() == 0); 				// No puede ser modificado
		Assert.isTrue(!this.actorService.isLogged());	// No haya usuario logueado

		//Password
		Md5PasswordEncoder password = new Md5PasswordEncoder();
		String encodedPassword = password.encodePassword(user.getUserAccount().getPassword(), null);
		user.getUserAccount().setPassword(encodedPassword);
		user.setUserAccount(this.userAccountService.save(user.getUserAccount()));

		return this.userRepository.save(user);
	}

	public void delete(final int userId) {
		Assert.isTrue(userId != 0);
		this.userRepository.delete(userId);
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
