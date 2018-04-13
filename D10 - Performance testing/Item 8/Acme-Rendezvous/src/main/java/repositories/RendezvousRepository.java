
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Category;
import domain.Rendezvous;
import domain.User;
import domain.Zervice;

@Repository
public interface RendezvousRepository extends JpaRepository<Rendezvous, Integer> {

	@Query("select r from Rendezvous r where r.adultOnly = false")
	Collection<Rendezvous> findAllUnder18();

	@Query("select r from Rendezvous r where r.adultOnly = true")
	Collection<Rendezvous> findAllOver18();

	@Query("select r.rendezvous from Rsvp r where r.user = ?1")
	Collection<Rendezvous> getRSVPRendezvousesForUser(User user);

	@Query("select coalesce(avg(u.rendezvouses.size),0), coalesce(stddev(u.rendezvouses.size),0) from User u")
	Double[] getRendezvousStats();

	@Query("select coalesce((count(u)*1.0)/(select count(x) from User x),0) from User u where u.rendezvouses is not empty")
	Double getRatioOfUsersWhoHaveCreatedRendezvouses();

	@Query("select coalesce(avg(r.rsvps.size),0), coalesce(stddev(r.rsvps.size),0) from Rendezvous r")
	Double[] getRendezvousUserStats();

	@Query("select coalesce(avg(u.rsvps.size),0), coalesce(stddev(u.rsvps.size),0) from User u")
	Double[] getUserRendezvousesStats();

	@Query(value="select Rendezvous.* from Rendezvous inner join Rsvp on rendezvous.id = rsvp.rendezvous_id group by rendezvous.id" +
			" order by count(rendezvous.id) desc limit 10", nativeQuery = true)
	Collection<Rendezvous> getTop10RendezvousByRSVPs();

	@Query("select coalesce(avg(r.announcements.size),0), coalesce(stddev(r.announcements.size),0) from Rendezvous r")
	Double[] getRendezvousAnnouncementStats();

	@Query("select r from Rendezvous r where (select count(a) from r.announcements a) >= ( select avg(r2.announcements.size) from Rendezvous r2)*0.75 order by r.announcements.size DESC")
	Collection<Rendezvous> getRendezvousesWithNumberOfAnnouncementsOver75PerCentAvg();

	@Query("select r from Rendezvous r where (select count(a) from r.rendezvouses a) >= ( select avg(r2.rendezvouses.size) from Rendezvous r2)*1.1 order by r.announcements.size DESC")
	Collection<Rendezvous> getRendezvousesLinkedToMoreThan10PerCentAVGNumberOfRendezvouses();

	@Query("select coalesce(avg(r.questions.size),0), coalesce(stddev(r.questions.size),0) from Rendezvous r")
	Double[] getRendezvousQuestionStats();

	//Avg and stdev of answers to questions per rendezvous
	@Query(value="select avg(a.qcount),std(a.qcount) from (select count(questionsAndAnswers_KEY) as qcount" +
			" from rsvp_questionsandanswers as r where r.questionsandanswers not like '' group by r.rsvp_id) a;", nativeQuery=true)
	Double[] getAnswersToQuestionsStats();

	@Query("select r from Rendezvous r where r.user = ?1 and r.inappropriate = false and r.deleted = false and ?2 not member of r.rendezvouses and r != ?2")
	Collection<Rendezvous> getRendezvousesToLink(User u, Rendezvous r);

	@Query("select r from Rendezvous r where r.inappropriate = false and r.deleted = false")
	Collection<Rendezvous> getRendezvousesToLink();

	@Query("select r from Rendezvous r where r.inappropriate = false and r.deleted = false and r.organisationMoment > CURRENT_TIMESTAMP")
	Collection<Rendezvous> getExploreRendezvouses();

	@Query("select distinct r from Rendezvous r join r.requests reqs join reqs.zervice z join z.category c where c in ?1")
	Collection<Rendezvous> getRendezvousForCategories(Collection<Category> categories);

	@Query(value="select avg(count) from (select count(distinct category.id) as count from rendezvous join request on rendezvous.id = request.rendezvous_id join zervice on request.zervice_id = zervice.id join category on zervice.category_id = category.id group by rendezvous.id) as temp", nativeQuery=true)
	Double getAvgCategoriesPerRendezvous();

	@Query("select r.zervice from Request r where r.rendezvous=?1")
	Collection<Zervice> getServicesForRendezvous(Rendezvous rendezvous);

}