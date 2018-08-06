package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VolumeInfo {

    private String title;
    private String publisher;
    private List<IndustryIdentifiers> industryIdentifiers;
    private ReadingModes readingModes;
    private String printType;
    private String maturityRating;
    private Boolean allowAnonLogging;
    private String contentVersion;
    private ImageLinks imageLinks;
    private String language;
    private String previewLink;
    private String infoLink;
    private String canonicalVolumeLink;
    private List<String> authors;
    private String publishedDate;
    private String description;
    private int pageCount;
    private List<String> categories;
    private Double averageRating;
    private Double ratingsCount;
    private PanelizationSummary panelizationSummary;
    private String subtitle;

}
