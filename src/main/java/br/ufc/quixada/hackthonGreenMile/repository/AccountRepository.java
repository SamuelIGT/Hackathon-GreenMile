package br.ufc.quixada.hackthonGreenMile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.quixada.hackthonGreenMile.security.AccountCredentials;

@Repository
public interface AccountRepository extends JpaRepository<AccountCredentials, Long> {
	
	AccountCredentials findById(int id);
	AccountCredentials findByUsername(String username);
}
