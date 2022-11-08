package com.assignment.ADA.module;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private Long memberId;
    private String strProgramId;
    private String eff_date;
    private double ada;
}
