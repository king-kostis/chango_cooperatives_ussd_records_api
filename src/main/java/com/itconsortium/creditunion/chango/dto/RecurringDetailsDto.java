package com.itconsortium.creditunion.chango.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecurringDetailsDto {
    private String groupName;
    private String frequency;
    private String durationOfRecurrence;
}
