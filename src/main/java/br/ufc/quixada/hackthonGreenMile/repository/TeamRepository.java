package br.ufc.quixada.hackthonGreenMile.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.ufc.quixada.hackthonGreenMile.model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
	
	@Query("Select t from Team t where t.hackathon.id = :hackathonid")
	Page<Team> findAllTeams(@Param("hackathonid") long hackathonid, Pageable pageable);
	
	@Query("Select t from Team t where t.hackathon.id = :hackathonid ORDER BY t.name")
	 Page<Team> findAllTeamsOrderedByName(@Param("hackathonid") long hackathonid, Pageable pageable);
	
	@Query("Select t from Team t where t.hackathon.id = :hackathonid ORDER BY t.subscriptionDate")
	 Page<Team> findAllTeamsOrderedByDate(@Param("hackathonid") long hackathonid, Pageable pageable);
	
	
}
