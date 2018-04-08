package services;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.NewspaperRepository;
import domain.Article;
import domain.Newspaper;
import domain.Subscription;

@Service
@Transactional
public class NewspaperService {

	@Autowired
	private NewspaperRepository		newspaperRepository;
	@Autowired
	private AdminService adminService;
	@Autowired
	private UserService userService;
	@Autowired
	private ArticleService articleService;

	//Supporting Services -------------------


	//CRUD Methods -------------------------

	public Newspaper create() {
		Newspaper res = new Newspaper();
		
		res.setArticless(new ArrayList<Article>());
		res.setSubscriptionss(new ArrayList<Subscription>());
		
		res.setInappropriate(false);
		
		res.setUser(userService.findByPrincipal());
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
		Assert.notNull(userService.findByPrincipal());
		Assert.isTrue(newspaper.getPublicationDate().after(new Date()));
		return newspaperRepository.save(newspaper);
	}
	
	public Collection<Newspaper> findByKeyword(String keyword){
		return newspaperRepository.findByKeyword(keyword);
	}
	public void markAsInappropriate(int newspaperId) {
		Assert.notNull(adminService.findByPrincipal());
		Newspaper n = findOne(newspaperId);
		n.setInappropriate(true);
		newspaperRepository.save(n);
		articleService.markInappropriateArticlesOfNewspaper(n);
	}
}