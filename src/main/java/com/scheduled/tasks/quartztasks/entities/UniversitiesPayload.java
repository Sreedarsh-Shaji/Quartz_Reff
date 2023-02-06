package com.scheduled.tasks.quartztasks.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UniversitiesPayload {

    @JsonProperty("web_pages")
    private List<String> webPages;
    @JsonProperty("state-province")
    private String stateProvince;
    @JsonProperty("alpha_two_code")
    private String alphaTwoCode;
    private String name;
    private String country;
    private List<String> domains;

}
