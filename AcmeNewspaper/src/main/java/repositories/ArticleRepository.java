package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Article;
import domain.Newspaper;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

	@Query(value="select * from article as a where exists (select n_id,a_date from (select sum(finalMode)-count(id) as published, publicationMoment as a_date, newspaper_id as n_id from article group by n_id,a_date) as temporal1 where published = 0 and n_id = a.newspaper_id and a_date = a.publicationMoment) and a.inappropriate = false", nativeQuery=true)
	Collection<Article> findAllPublished();
	
	@Query(value="select * from article as a where exists (select n_id,a_date from (select sum(finalMode)-count(id) as published, publicationMoment as a_date, newspaper_id as n_id from article group by n_id,a_date) as temporal1 where published = 0 and n_id = a.newspaper_id and a_date = a.publicationMoment) and (title like %?1% or summary like %?1% or body like %?1%) and a.inappropriate = false", nativeQuery=true)
	Collection<Article> findAllPublishedKeyword(String keyword);
	
	@Query(value="select a.* from article as a join newspaper on a.newspaper_id = newspaper.id where exists (select n_id,a_date from (select sum(finalMode)-count(id) as published, publicationMoment as a_date, newspaper_id as n_id from article group by n_id,a_date) as temporal1 where published = 0 and n_id = a.newspaper_id and a_date = a.publicationMoment) and newspaper.user_id = ?1 and a.inappropriate = false", nativeQuery=true)
	Collection<Article> findAllPublishedForUser(int userId);
	
	@Query(value="select a.* from article as a join newspaper on a.newspaper_id = newspaper.id where exists (select n_id,a_date from (select sum(finalMode)-count(id) as published, publicationMoment as a_date, newspaper_id as n_id from article group by n_id,a_date) as temporal1 where published = 0 and n_id = a.newspaper_id and a_date = a.publicationMoment) and newspaper.id = ?1 and a.inappropriate = false", nativeQuery=true)
	Collection<Article> findAllPublishedForNewspaper(int newspaperId);

	@Modifying
	@Query("update Article a set a.inappropriate = true where a.newspaper = ?1")
	void markInappropriateArticlesOfNewspaper(Newspaper n);

	@Query(value="select article.* from article join (select taboowordss as word from systemconfig join systemconfig_taboowordss on systemconfig.id = systemconfig_taboowordss.systemconfig_id) as taboo_words where body like concat('%',word,'%') or title like concat('%',word,'%') or summary like concat('%',word,'%') group by article.id",nativeQuery=true)
	Collection<Article> findAllTaboo();

}