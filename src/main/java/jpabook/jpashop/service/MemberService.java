package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //모든 public 메서드에 적용
@RequiredArgsConstructor //final있는 field만 가지고 생성자 만들어줌.
public class MemberService {

    private final MemberRepository memberRepository;

//    //생성자가 하나면 @Autowired생략 가능 (@RequiredArgsConstructor있으면 생략)
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
    /**
     * 회원가입s
     */
    @Transactional //join 메서드에만 적용
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 화면입니다");
        }
    }

    /**
     * 회원조회
     */
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
