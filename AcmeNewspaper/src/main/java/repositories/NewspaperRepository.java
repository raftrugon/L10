package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Newspaper;
import domain.User;

@Repository
public interface NewspaperRepository extends JpaRepository<Newspaper, Integer> {

	@Query("select n from Newspaper n where (n.title like %?1% or n.description like %?1%) and  n.inappropriate = false")
	Collection<Newspaper> findByKeyword(String keyword);

	@Query("select n from Newspaper n where n.user = ?1 AND (n.publicationDate > CURRENT_TIMESTAMP)")
	Collection<Newspaper> findMyNonPublished(User user);



}