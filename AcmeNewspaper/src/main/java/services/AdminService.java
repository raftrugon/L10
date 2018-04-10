
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdminRepository;
import security.LoginService;
import security.UserAccount;
import domain.Admin;

@Service
@Transactional
public class AdminService {

	@Autowired
	private AdminRepository	adminRepository;


	public Admin findOne(int adminId) {
		Assert.isTrue(adminId != 0);
		Admin res = adminRepository.findOne(adminId);
		Assert.notNull(res);
		return res;
	}

	public void flush() {
		adminRepository.flush();
	}

	//Other Business Methods --------------------------------

	public Admin findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);
		Admin res;
		res = adminRepository.findByUserAccount(userAccount.getId());
		return res;
	}

	public Admin findByPrincipal() {
		Admin res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = findByUserAccount(userAccount);
		return res;
	}

}
