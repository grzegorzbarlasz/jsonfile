package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListPrice {

    private int amount;
    private int amountInMicros;
    private String currencyCode;
}
