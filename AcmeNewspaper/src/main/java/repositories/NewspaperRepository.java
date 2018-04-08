package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Article;
import domain.Newspaper;

@Repository
public interface NewspaperRepository extends JpaRepository<Newspaper, Integer> {

	@Query("select n from Newspaper n where (n.title like %?1% or n.description like %?1%) and  n.inappropriate = false")
	Collection<Newspaper> findByKeyword(String keyword);
	
	@Query(value="select newspaper.* from newspaper join (select taboowordss as word from systemconfig join systemconfig_taboowordss on systemconfig.id = systemconfig_taboowordss.systemconfig_id) as taboo_words where description like concat('%',word,'%') or title like concat('%',word,'%') group by newspaper.id",nativeQuery=true)
	Collection<Newspaper> findAllTaboo();

	
	

}