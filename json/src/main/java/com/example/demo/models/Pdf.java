package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pdf {

    private Boolean isAvailable;
    private String acsTokenLink;
    private String downloadLink;

}
