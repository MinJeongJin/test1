package teamphony.service.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import teamphony.domain.Member;
import teamphony.service.facade.MemberService;
import teamphony.store.facade.MemberStore;

@Service
public class MemberServiceLogic implements MemberService {

	@Autowired
	private MemberStore store;

	@Override
	public void registerMember(Member member) {
		store.insertMember(member);
	}

	@Override
	public void modifyMember(Member member) {
		store.updateMember(member);
	}

	@Override
	public void removeMember(String memberId) {
		store.deleteMember(memberId);
	}

	@Override
	public Member findMemberByMemberId(String memberId) {
		return store.selectMemberByMemberId(memberId);
	}

	@Override
	public void saveStarPoint(String memberId, double starPoint) {
		store.insertStarPoint(memberId, starPoint);
	}
	
	public List<Member> findAllMember(){
		return store.selectAllMember();
	}
}
