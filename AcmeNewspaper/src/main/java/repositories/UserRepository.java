
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("select u from User u where u.userAccount.id = ?1")
	User findByUserAccount(int id);
	
	
	@Query("select coalesce(avg(u.newspapers.size),0), coalesce(stddev(u.newspapers.size),0) from User u")
	Double[] getNewspaperUserStats();
	
	

}
