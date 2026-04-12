package com.example.employees.dto;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobsDTO {
    private String jobId;
    private String jobTitle;

}
