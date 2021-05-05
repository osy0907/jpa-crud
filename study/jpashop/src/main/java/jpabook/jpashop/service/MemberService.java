package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join (Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        if (!memberRepository.findByName(member.getUserName()).isEmpty()) // was가 여러개 떳을 때 a라는 이름으로 동시에 저장이 되는 경우가 있기 때문에 멀티스레드나 이러한 상황을 고려해서 db name 에 unique 제약을 건다.
            throw new IllegalStateException("이미 존재하는 회원입니다.");
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
