package br.ufc.quixada.hackthonGreenMile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.quixada.hackthonGreenMile.model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
	
}
