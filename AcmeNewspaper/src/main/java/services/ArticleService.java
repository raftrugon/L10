package services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import java.util.Collection;
import domain.Article;
import repositories.ArticleRepository;

@Service
@Transactional
public class ArticleService {

	@Autowired
	private ArticleRepository		articleRepository;

	//Supporting Services -------------------


	//CRUD Methods -------------------------

	public Article create() {
		Article res = new Article();
		
		return res;
	}
	
	public Article findOne(int articleId) {
		Assert.isTrue(articleId != 0);
		Article res = articleRepository.findOne(articleId);
		Assert.notNull(res);
		return res;
	}
	
	public Collection<Article> findAll() {
		return articleRepository.findAll();
	}
	
	public Article save(final Article article) {
		Assert.notNull(article);
		
		
		return articleRepository.save(article);
	}
	
	
}