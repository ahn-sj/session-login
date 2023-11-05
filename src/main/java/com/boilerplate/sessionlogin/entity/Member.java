package com.boilerplate.sessionlogin.entity;

import com.boilerplate.sessionlogin.enums.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Member(final String email, final String password, final Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public void validatePassword(final String password) {
        if(this.password.equals(password)) {
            return;
        }
        throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
    }
}
