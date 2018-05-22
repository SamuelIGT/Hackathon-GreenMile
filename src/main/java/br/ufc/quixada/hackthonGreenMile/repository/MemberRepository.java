package br.ufc.quixada.hackthonGreenMile.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.ufc.quixada.hackthonGreenMile.model.Member;
import br.ufc.quixada.hackthonGreenMile.model.Team;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
	/*@Query("SELECT tm FROM Team.members tm, Hackathon.teams ht, HACKATHON h where tm.id = ht.id and ht.id = :hackathonid and tm.MEMBER_ID = :memberid")
	 boolean findMemberByHackthonIdAndTeamId(@Param("hackathonid") long memberid, @Param("hackathonid") long hackathonid);*/
	
}
