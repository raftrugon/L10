package services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import java.util.Collection;
import domain.SystemConfig;
import repositories.SystemConfigRepository;

@Service
@Transactional
public class SystemConfigService {

	@Autowired
	private SystemConfigRepository		systemConfigRepository;

	//Supporting Services -------------------


	//CRUD Methods -------------------------

	public SystemConfig create() {
		SystemConfig res = new SystemConfig();
		
		return res;
	}
	
	public SystemConfig findOne(int systemConfigId) {
		Assert.isTrue(systemConfigId != 0);
		SystemConfig res = systemConfigRepository.findOne(systemConfigId);
		Assert.notNull(res);
		return res;
	}
	
	public Collection<SystemConfig> findAll() {
		return systemConfigRepository.findAll();
	}
	
	public SystemConfig save(final SystemConfig systemConfig) {
		Assert.notNull(systemConfig);
		
		
		return systemConfigRepository.save(systemConfig);
	}
	
	public void delete(final int systemConfigId) {
		Assert.isTrue(systemConfigId != 0);
		systemConfigRepository.delete(systemConfigId);
	}
	
	
}