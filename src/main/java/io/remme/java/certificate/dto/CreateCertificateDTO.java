package io.remme.java.certificate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCertificateDTO {
    private String commonName;
    private String email;
    private String countryName;
    private String localityName;
    private String postalAddress;
    private String postalCode;
    private String streetAddress;
    private String stateName;
    private String name;
    private String surname;
    private String pseudonym;
    private String generationQualifier;
    private String title;
    private String serial;
    private String businessCategory;
    private Integer validity;
    private Integer validAfter;
}
