package com.automation.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jdk.jfr.MetadataDefinition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Student {

    private String firstname;
    private String lastname;
    private List<Address> address;
    private List<PhoneNumber> phoneNumber;


}
