package teamphony.service.logic;

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
		System.out.println("service "+ member.getMemberId());
		store.insertMember(member);
	}

	@Override
	public void modifyMember(Member member) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeMember(String memberId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkMember(Member member) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Member findMemberByMemberId(String memberId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveStarPoint(Member member) {
		// TODO Auto-generated method stub
		
	}

}
