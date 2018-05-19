package br.ufc.quixada.hackthonGreenMile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import br.ufc.quixada.hackthonGreenMile.model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
	
}
