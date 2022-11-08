package com.assignment.ADA.controller;


import com.assignment.ADA.module.Request;
import com.assignment.ADA.module.Response;
import com.assignment.ADA.service.MemberService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
public class MemberController {
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/ada")
    Response getMemberADA(@RequestBody Request requestBody) {
        return memberService.getADAOfMember(requestBody);
    }

    @PostMapping("/enrollment_details")
    List<Response> getEnrollmentDetails(@RequestBody Request request) {
        return memberService.getEnrollementdetails(request);
    }
}
