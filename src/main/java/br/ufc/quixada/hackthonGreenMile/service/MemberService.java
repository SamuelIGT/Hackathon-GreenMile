package br.ufc.quixada.hackthonGreenMile.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.ufc.quixada.hackthonGreenMile.model.Member;

public interface MemberService {
	public ResponseEntity<Member> create (Member member);
	public ResponseEntity<Boolean> delete (Long id);
	public ResponseEntity<Member> get (Long id);
	public ResponseEntity<Member> update (Member member);
	public ResponseEntity<List<Member>> getAll ();
	public ResponseEntity<Boolean> jointTeam(Long teamId, Member member);
}
