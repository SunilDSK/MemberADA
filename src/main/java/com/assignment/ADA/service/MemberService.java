package com.assignment.ADA.service;

import com.assignment.ADA.module.Frequency;
import com.assignment.ADA.module.Member;
import com.assignment.ADA.module.Request;
import com.assignment.ADA.module.Response;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MemberService {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
    private static List<Member> member2000707950 = List.of(
            new Member("VIS",LocalDate.parse("9/27/2020",formatter), 9.5d, Frequency.B,247d),
            new Member("VIS",LocalDate.parse("1/5/2020",formatter), 9.5d, Frequency.B, 247d),
            new Member("VIS",LocalDate.parse("1/3/2021",formatter), 9.68d, Frequency.B, 251.68d),
            new Member("VIS",LocalDate.parse("1/2/2022",formatter), 9.86d, Frequency.B, 256.36d),
            new Member("VIS",LocalDate.parse("1/2/2023",formatter), 10.86d, Frequency.B, 282.36d)
            );
    private static List<Member> members10000003576 = List.of(
            new Member("DEN",LocalDate.parse("8/29/2021",formatter), 12.99d, Frequency.B, 116.91d),
            new Member("DEN",LocalDate.parse("1/2/2022",formatter), 13.05d, Frequency.B, 339.3d),
            new Member("DEN",LocalDate.parse("11/7/2021",formatter), 12.99d, Frequency.B, 116.91d),
            new Member("DEN",LocalDate.parse("11/21/2021",formatter), 12.99d, Frequency.B, 116.91d),
            new Member("DEN",LocalDate.parse("3/1/2022",formatter), 28.28d, Frequency.M, 336.86d),
            new Member("DEN",LocalDate.parse("2/27/2022",formatter), 12.05d, Frequency.B, 339.3d),
            new Member("DEN",LocalDate.parse("4/1/2022",formatter), 28.28d, Frequency.M, 336.86d),
            new Member("DEN",LocalDate.parse("1/1/2023",formatter), 29.28d, Frequency.M, 351.36d)
    );

    private static Map<Long, List<Member>> memberDetails = Map.of(
        20007037950L, member2000707950, 10000003576L,members10000003576
    );

    public Response getADAOfMember(Request request) {
        List<Member> members = memberDetails.get(request.getMemberId());
        LocalDate requestDate = LocalDate.parse(request.getEff_date(), formatter);
        Member memberResult = members.stream()
                .filter(member -> member.getProd_id().equalsIgnoreCase(request.getStrProgramId()))
                .filter(member -> member.getEff_date().isEqual(requestDate))
                .findAny()
                .orElseThrow( () -> new RuntimeException("Member not found"));
        return new Response(request.getMemberId(), request.getStrProgramId(), request.getEff_date(), memberResult.getADA());
    }

    public List<Response> getEnrollementdetails(Request request) {
        List<Member> members = memberDetails.get(request.getMemberId());
        Year year = Year.parse(request.getPay_year(), DateTimeFormatter.ofPattern("yyyy"));
        List<Member> filteredMembers = members.stream()
                .filter(member -> member.getEff_date().getYear() == year.getValue())
                .toList();
        List<Response> responses = new ArrayList<>();
        filteredMembers.forEach(member -> {
            responses.add(
                    new Response(request.getMemberId(),
                            member.getProd_id(),
                            member.getEff_date().format(formatter),
                            member.getADA())
            );
        });
        return responses;
    }
}
