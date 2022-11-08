package com.assignment.ADA.module;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Member {
    private String prod_id;
    private LocalDate eff_date;
    private double ded_amount;
    private Frequency frequency;
    private double ADA;
}
