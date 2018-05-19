package br.ufc.quixada.hackthonGreenMile.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.ufc.quixada.hackthonGreenMile.model.Member;
import br.ufc.quixada.hackthonGreenMile.repository.MemberRepository;
import br.ufc.quixada.hackthonGreenMile.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository repository;

	@Override
	public ResponseEntity<Member> create(Member member) {
		return new ResponseEntity<Member>(this.repository.save(member), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Boolean> delete(Long id) {
		this.repository.deleteById(id);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Member> get(Long id) {
		return new ResponseEntity<Member>(this.repository.findById(id).get(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Member> update(Member member) {
		return new ResponseEntity<Member>(this.repository.save(member), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Member>> getAll() {
		return new ResponseEntity<List<Member>>(this.repository.findAll(), HttpStatus.OK);
	}

}
