package services;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ArticleRepository;
import domain.Article;
import domain.FollowUp;
import domain.Newspaper;
import domain.User;

@Service
@Transactional
public class ArticleService {

	@Autowired
	private ArticleRepository		articleRepository;
	@Autowired
	private AdminService adminService;
	@Autowired
	private UserService userService;
	@Autowired
	private Validator validator;

	//Supporting Services -------------------


	//CRUD Methods -------------------------

	public Article create() {
		Article res = new Article();

		Collection<String> picturess = new ArrayList<String>();
		Collection<FollowUp> followUps= new ArrayList<FollowUp>();

		res.setPicturess(picturess);
		res.setFollowUps(followUps);

		res.setPublicationMoment(new Date(System.currentTimeMillis()+9999999));
		res.setInappropriate(false);
		Assert.isTrue(userService.findByPrincipal() instanceof domain.User);
		return res;
	}

	public Article findOne(final int articleId) {
		Assert.isTrue(articleId != 0);
		Article res = this.articleRepository.findOne(articleId);
		Assert.notNull(res);
		return res;
	}

	public Collection<Article> findAll() {
		return this.articleRepository.findAll();
	}

	public Article save(final Article article) {
		Assert.notNull(article);
		User user = this.userService.findByPrincipal();
		Assert.notNull(user);
		Assert.isTrue(article.getNewspaper().getUser().equals(user)); //Solo el creador del periodico puede crear articulos en su periodico


		if(article.getId() == 0){
			Assert.isTrue(article.getNewspaper().getPublicationDate().after(new Date())); 	// No se puede crear un articulo en un periodico publicado
			article.setPublicationMoment(article.getNewspaper().getPublicationDate()); 		//Seteo de la fecha de publicación
		} else {
			Article bd = this.findOne(article.getId());
			Assert.isTrue(article.getPublicationMoment().equals(bd.getPublicationMoment()));//No puede modificarse la fecha de publicacion de un articulo
			Assert.isTrue(!bd.getFinalMode());												//No puede modificarse un articulo en finalMode
		}

		Article saved = this.articleRepository.save(article);

		article.getNewspaper().getArticless().add(saved);

		return saved;
	}

	public Collection<Article> findAllPublished() {
		return this.articleRepository.findAllPublished();
	}
	
	public void flush(){
		articleRepository.flush();
	}

	public Collection<Article> findAllPublishedForUser(final User u) {
		return this.articleRepository.findAllPublishedForUser(u.getId());
	}

	public Collection<Article> findAllPublishedForNewspaper(final Newspaper n) {
		return this.articleRepository.findAllPublishedForNewspaper(n.getId());
	}

	public Collection<Article> findAllPublishedKeyword(final String keyword) {
		return this.articleRepository.findAllPublishedKeyword(keyword);
	}

	public void markAsInappropriate(final int articleId) {
		Assert.notNull(this.adminService.findByPrincipal());
		Article a = this.findOne(articleId);
		a.setInappropriate(true);
		this.articleRepository.save(a);
	}

	public void markInappropriateArticlesOfNewspaper(final Newspaper n) {
		Assert.notNull(this.adminService.findByPrincipal());
		this.articleRepository.markInappropriateArticlesOfNewspaper(n);
	}

	public Article reconstruct(final Article article, final BindingResult binding) {
		Article res = article;
		res.setPublicationMoment(article.getNewspaper().getPublicationDate());
		this.validator.validate(res, binding);
		return res;
	}


	public Collection<Article> findAllTaboo() {
		return articleRepository.findAllTaboo();
	}
	

	public Boolean isPublished(Article a){
		return articleRepository.isPublished(a.getId()) > 0 ? true : false;
	}
	
}