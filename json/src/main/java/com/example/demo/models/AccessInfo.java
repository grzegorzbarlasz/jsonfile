package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccessInfo {

    private String country;
    private String viewability;
    private boolean embeddable;
    private boolean publicDomain;
    private String textToSpeechPermission;
    private Epub epub;
    private Pdf pdf;
    private String webReaderLink;
    private String accessViewStatus;
    private boolean quoteSharingAllowed;

}
