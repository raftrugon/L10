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

		res.setUser(this.userService.findByPrincipal());
		return res;
	}

	public Newspaper findOne(final int newspaperId) {
		Assert.isTrue(newspaperId != 0);
		Newspaper res = this.newspaperRepository.findOne(newspaperId);
		Assert.notNull(res);
		return res;
	}

	public Collection<Newspaper> findAll() {
		return this.newspaperRepository.findAll();
	}

	public Newspaper save(final Newspaper newspaper) {
		Assert.notNull(newspaper);
		Assert.notNull(this.userService.findByPrincipal());
		Assert.isTrue(newspaper.getPublicationDate().after(new Date()));
		return this.newspaperRepository.save(newspaper);
	}

	public Collection<Newspaper> findByKeyword(final String keyword){
		return this.newspaperRepository.findByKeyword(keyword);
	}
	public void markAsInappropriate(final int newspaperId) {
		Assert.notNull(this.adminService.findByPrincipal());
		Newspaper n = this.findOne(newspaperId);
		n.setInappropriate(true);
		this.newspaperRepository.save(n);
		this.articleService.markInappropriateArticlesOfNewspaper(n);
	}

	public Collection<Newspaper> findMyNonPublished() {
		Assert.notNull(this.userService.findByPrincipal());
		return this.newspaperRepository.findMyNonPublished(this.userService.findByPrincipal());
	}

	public Collection<Newspaper> findAllTaboo() {
		return newspaperRepository.findAllTaboo();
	}
}