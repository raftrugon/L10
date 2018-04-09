package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Subscription;

@Repository
public interface SuscriptionRepository extends JpaRepository<Subscription, Integer> {



}