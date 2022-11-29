package com.example.datajpa.dto;

import com.example.datajpa.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberDto {
    private Long id;
    private String userName;
    private String teamName;

    public MemberDto(Member member) {
        this.id = member.getId();
        this.userName = member.getUserName();
    }
}
