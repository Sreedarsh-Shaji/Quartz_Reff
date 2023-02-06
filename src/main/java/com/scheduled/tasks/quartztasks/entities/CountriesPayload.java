package com.scheduled.tasks.quartztasks.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountriesPayload {
    String status;
    String statusCode;
    String version;
    String access;
    Countries data;
}
