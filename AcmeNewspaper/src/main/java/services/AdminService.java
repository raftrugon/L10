package services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import java.util.Collection;

import domain.Actor;
import domain.Admin;
import repositories.AdminRepository;

@Service
@Transactional
public class AdminService {

	@Autowired
	private AdminRepository		adminRepository;

	public Admin findOne(int adminId) {
		Assert.isTrue(adminId != 0);
		Admin res = adminRepository.findOne(adminId);
		Assert.notNull(res);
		return res;
	}

	//Supporting Services -------------------


	//CRUD Methods -------------------------

	
}