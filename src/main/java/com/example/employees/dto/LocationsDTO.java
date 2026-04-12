package com.example.employees.dto;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationsDTO {
    private int locationId;
    private String city;
}
