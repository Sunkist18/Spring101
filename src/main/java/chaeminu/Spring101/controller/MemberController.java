package chaeminu.Spring101.controller;

import chaeminu.Spring101.service.MemberService;

public class MemberController {

    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
