package com.automation.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@JsonIgnoreProperties(ignoreUnknown = true)


@AllArgsConstructor
@Data
@NoArgsConstructor
public class PhoneNumber {

    String mobileNumber;
    String homeNumber;

}
