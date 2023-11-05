package com.boilerplate.sessionlogin.service;

import com.boilerplate.sessionlogin.dto.JoinRequest;
import com.boilerplate.sessionlogin.dto.LoginRequest;
import com.boilerplate.sessionlogin.entity.Member;
import com.boilerplate.sessionlogin.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void joinMember(final JoinRequest request) {
        validateDuplicateEmail(request);

        Member member = request.toEntity();
        memberRepository.save(member);
    }

    public Long loginMember(final LoginRequest request) {
        Member member = memberRepository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));

        member.validatePassword(request.password());

        return member.getId();
    }

    private void validateDuplicateEmail(final JoinRequest request) {
        if(memberRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("중복된 이메일이 존재합니다.");
        }
    }
}
