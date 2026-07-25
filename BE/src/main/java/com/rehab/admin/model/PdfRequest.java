package com.rehab.admin.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PdfRequest {

    private String name;
    private int age;
    private String gender;
    private String diagnosis;
    private String socialInteraction;
    private String respectiveLanguage;
    private String expressiveLanguage;
    private String summary;
    private String schoolPerformance;
    private String recommendations;

}
