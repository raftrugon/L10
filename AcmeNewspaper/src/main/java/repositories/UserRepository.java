package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("select u from User u where u.userAccount.id = ?1")
	User findByUserAccount(int id);
	
	
	//The average and the standard deviation of newspapers created per user.
	@Query("select coalesce(avg(u.newspapers.size),0), coalesce(stddev(u.newspapers.size),0) from User u")
	Double[] getStatsOfNewspapersPerUser();
	
	//The average and the standard deviation of articles written by writer.
	@Query("")
	Double[] getStatsOfArticlesPerUser();
	
	//The ratio of users who have ever created a newspaper.
	@Query("select coalesce((count(u)*1.0)/(select count(x) from User x),0) from User u where u.newspapers is not empty")
	Double getRatioOfUsersWhoHaveCreatedNewspapers();
	
	//The average and the standard deviation of the number of chirps per user.
	@Query("select coalesce(avg(u.chirps.size),0), coalesce(stddev(u.chirps.size),0) from User u")
	Double[] getStatsOfChirpsPerUser();
	
	//The ratio of users who have posted above 75% the average number of chirps per user.
	@Query("select coalesce((count(u)*1.0)/(select count(x) from User x),0) from User u where u.chirps.size > (select avg(u2.chirps.size)*0.75 from User u2)")
	Double getRatioOfUsersWhoHavePostedMOreChirpsThan75Avg();

	//The average ratio of private versus public newspapers per publisher.
	@Query("")
	Double getAvgRatioOfNewspapersPerPublisher();
}