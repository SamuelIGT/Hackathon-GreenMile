package br.ufc.quixada.hackthonGreenMile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.quixada.hackthonGreenMile.model.Hackathon;

@Repository
public interface HackathonRepository extends JpaRepository<Hackathon, Long> {
	 void deleteById(Long id);
}
