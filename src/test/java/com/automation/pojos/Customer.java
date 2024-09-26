package com.automation.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.poifs.crypt.agile.AgileEncryptionHeader;

import java.util.List;


@Data
@NoArgsConstructor
@JsonIgnoreProperties
public class Customer {

    String firstname;
    String lastname;
    String totalprice;
    String depositpaid;
    BookingDates bookingDates;
    String additionalneeds;

}
