package com.boilerplate.sessionlogin.dto;

import com.boilerplate.sessionlogin.entity.Member;
import com.boilerplate.sessionlogin.enums.Role;

public record JoinRequest(String email, String password) {

    public Member toEntity() {
        return new Member(email, password, Role.USER);
    }
}
