package io.remme.java.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x500.style.BCStyle;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum SubjectField {
    CN(BCStyle.CN, "commonName"),
    E(BCStyle.E, "email"),
    C(BCStyle.C, "countryName"),
    L(BCStyle.L, "localityName"),
    POSTAL_ADDRESS(BCStyle.POSTAL_ADDRESS, "postalAddress"),
    POSTAL_CODE(BCStyle.POSTAL_CODE, "postalCode"),
    STREET(BCStyle.STREET, "streetAddress"),
    ST(BCStyle.ST, "stateName"),
    NAME(BCStyle.NAME, "name"),
    SURNAME(BCStyle.SURNAME, "surname"),
    PSEUDONYM(BCStyle.PSEUDONYM, "pseudonym"),
    GENERATION(BCStyle.GENERATION, "generationQualifier"),
    T(BCStyle.T, "title"),
    SERIALNUMBER(BCStyle.SERIALNUMBER, "serial"),
    BUSINESS_CATEGORY(BCStyle.BUSINESS_CATEGORY, "businessCategory");

    private ASN1ObjectIdentifier rdn;
    private String fieldName;

    public static SubjectField getByFieldName(String fieldName) {
        return Stream.of(SubjectField.values()).filter(val -> val.getFieldName().equalsIgnoreCase(fieldName))
                .findFirst().orElse(null);
    }
}
