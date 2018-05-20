package br.ufc.quixada.hackthonGreenMile.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.ufc.quixada.hackthonGreenMile.security.AccountCredentials;

public interface AccountService {
	
	public ResponseEntity<AccountCredentials> create (AccountCredentials account);
	
	public ResponseEntity<Boolean> delete (Long id);
	
	public ResponseEntity<AccountCredentials> get (Long id);
	
	public ResponseEntity<AccountCredentials> update (AccountCredentials account);
	
	public ResponseEntity<List<AccountCredentials>> getAll ();
	
	public AccountCredentials getByUsername (String username);
}
