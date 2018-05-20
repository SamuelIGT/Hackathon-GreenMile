package br.ufc.quixada.hackthonGreenMile.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.ufc.quixada.hackthonGreenMile.repository.AccountRepository;
import br.ufc.quixada.hackthonGreenMile.security.AccountCredentials;
import br.ufc.quixada.hackthonGreenMile.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository repository;
	
	@Override
	public ResponseEntity<AccountCredentials> create(AccountCredentials account) {
		
		return new ResponseEntity<AccountCredentials>(repository.save(account), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Boolean> delete(Long id) {
		this.repository.deleteById(id);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<AccountCredentials> get(Long id) {
		return new ResponseEntity<AccountCredentials>(this.repository.findById(id).get(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<AccountCredentials> update(AccountCredentials account) {
		return new ResponseEntity<AccountCredentials>(this.repository.save(account), HttpStatus.OK);

	}

	@Override
	public ResponseEntity<List<AccountCredentials>> getAll() {
		return new ResponseEntity<List<AccountCredentials>>(this.repository.findAll(), HttpStatus.OK);
	}

	@Override
	public AccountCredentials getByUsername(String username) {
		return this.repository.findByUsername(username);
	}

}
