package com.example.datajpa.controller;

import com.example.datajpa.dto.MemberDto;
import com.example.datajpa.entity.Member;
import com.example.datajpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("members")
    public Page<MemberDto> list(Pageable pageable) {
        return memberRepository.findAll(pageable)
            .map(MemberDto::new);
    }

}
