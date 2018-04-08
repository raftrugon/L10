package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Chirp;
import domain.User;

@Repository
public interface ChirpRepository extends JpaRepository<Chirp, Integer> {

	@Query("select c from Chirp c where c.user in ?1 order by c.creationMoment desc")
	Collection<Chirp> getTimeline(Collection<User> follows);
	
	@Query(value="select chirp.* from chirp join (select taboowordss as word from systemconfig join systemconfig_taboowordss on systemconfig.id = systemconfig_taboowordss.systemconfig_id) as taboo_words where description like concat('%',word,'%') or title like concat('%',word,'%') group by chirp.id",nativeQuery=true)
	Collection<Chirp> findAllTaboo();


}