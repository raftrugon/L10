package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

	@Query(value="select * from article as a where exists (select n_id,a_date from (select sum(finalMode)-count(id) as published, publicationMoment as a_date, newspaper_id as n_id from article group by n_id,a_date) as temporal1 where published = 0 and n_id = a.newspaper_id and a_date = a.publicationMoment)", nativeQuery=true)
	Collection<Article> findAllPublished();
	
	@Query(value="select a.* from article as a join newspaper on a.newspaper_id = newspaper.id where exists (select n_id,a_date from (select sum(finalMode)-count(id) as published, publicationMoment as a_date, newspaper_id as n_id from article group by n_id,a_date) as temporal1 where published = 0 and n_id = a.newspaper_id and a_date = a.publicationMoment) and newspaper.user_id = ?1", nativeQuery=true)
	Collection<Article> findAllPublishedForUser(int userId);
	
	@Query(value="select a.* from article as a join newspaper on a.newspaper_id = newspaper.id where exists (select n_id,a_date from (select sum(finalMode)-count(id) as published, publicationMoment as a_date, newspaper_id as n_id from article group by n_id,a_date) as temporal1 where published = 0 and n_id = a.newspaper_id and a_date = a.publicationMoment) and newspaper.id = ?1", nativeQuery=true)
	Collection<Article> findAllPublishedForNewspaper(int newspaperId);



}