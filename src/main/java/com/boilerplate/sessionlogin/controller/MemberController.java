package com.boilerplate.sessionlogin.controller;

import com.boilerplate.sessionlogin.dto.JoinRequest;
import com.boilerplate.sessionlogin.dto.LoginRequest;
import com.boilerplate.sessionlogin.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static com.boilerplate.sessionlogin.controller.MemberController.SessionConst.USER;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/api/v1/join")
    public ResponseEntity<Void> join(@RequestBody JoinRequest request) {
        memberService.joinMember(request);
        return ResponseEntity.created(URI.create("/login")).build();
    }

    @PostMapping("/api/v1/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest request, HttpSession session) {
        Long member = memberService.loginMember(request);

        registerSession(session, member);

        return ResponseEntity.ok().build();
    }

    private void registerSession(final HttpSession session, final Long member) {
        session.setAttribute(USER.getSessionId(), member);
        session.setMaxInactiveInterval(USER.getExpiredTime());
    }

    @Getter
    @AllArgsConstructor
    public enum SessionConst {
        USER("userId", 10 * 60) // 10분
        ;

        private final String sessionId;
        private final int expiredTime;

    }
}
