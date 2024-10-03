import com.automation.pojos.*;
import com.beust.ah.A;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Executable {
    public static void main(String[] args) {

        Address address = new Address("289 Zwide Road", "Midrand", "Gauteng", "2045");
        PhoneNumber phoneNumber = new PhoneNumber("0834439043", "01163633234");
        List<Address> addressList = new ArrayList<>();
        List<PhoneNumber> phoneNumberList = new ArrayList<>();

        addressList.add(address);
        phoneNumberList.add(phoneNumber);

        Student student = new Student("Vusi","Nkosi",addressList,phoneNumberList);

//        addressList.add(address);
//        phoneNumberList.add(phoneNumber);
//
         // Customer customer = new Customer("Vusi","Pelo","2000","true",new BookingDates("23-09-2024","30-09-2024"),"Breakfast");
//
//
//
        Gson gson = new Gson();
        String customer_json = gson.toJson(student);
//
     System.out.println(customer_json);
//    }
    }
}
