package services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import java.util.Collection;
import domain.Newspaper;
import repositories.NewspaperRepository;

@Service
@Transactional
public class NewspaperService {

	@Autowired
	private NewspaperRepository		newspaperRepository;

	//Supporting Services -------------------


	//CRUD Methods -------------------------

	public Newspaper create() {
		Newspaper res = new Newspaper();
		
		return res;
	}
	
	public Newspaper findOne(int newspaperId) {
		Assert.isTrue(newspaperId != 0);
		Newspaper res = newspaperRepository.findOne(newspaperId);
		Assert.notNull(res);
		return res;
	}
	
	public Collection<Newspaper> findAll() {
		return newspaperRepository.findAll();
	}
	
	public Newspaper save(final Newspaper newspaper) {
		Assert.notNull(newspaper);
		
		
		return newspaperRepository.save(newspaper);
	}
	
	
}