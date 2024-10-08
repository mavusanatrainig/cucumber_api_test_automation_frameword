package stepdefs;

import com.automation.constants.Constants;
import com.automation.functions.Library;
import com.automation.pages.DatabaseSetup;
import com.automation.pojos.*;
import com.beust.ah.A;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.math3.analysis.function.Add;
import com.automation.pages.ProductPage;
import io.cucumber.datatable.DataTable;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;





public class MyStepdefs {
    private Scenario scenario;
    private Response response;
    private RequestSpecification request;
    private Map<String,String> headers;
    private String payload;
    private Gson gson;
    Connection connection;
    private DatabaseSetup databaseSetup;
    private ProductPage productPage;




    @BeforeStep
    public void startup(Scenario scenario) throws SQLException {
       this.scenario = scenario;
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/training", "vusi", "password");
    }


    @When("I get a author using id as {string} and url {string}")
    public void iGetAAuthorUsingIdAsAndUrl(String arg0, String arg1) {

        RestAssured.baseURI="https://restful-booker.herokuapp.com";
        headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        request = RestAssured.given();
        response = request
                .headers(headers)
                .get(arg1 + arg0);

        scenario.log(arg1 + arg0);
        scenario.log(response.getBody().prettyPrint());
    }


    @And("Validate that the first name is {string}")
    public void validateThatTheFirstNameIs(String arg0) {
       String firstname = response.getBody().jsonPath().getString("[0].firstName");
       scenario.log(firstname);
    }


    @Then("The system responds with status code {int}")
    public void theSystemRespondsWithStatusCode(int arg0) {
        assertThat(response.getStatusCode(),is(arg0));
    }

    @Given("I have a booking system endpoint {string}")
    public void iHaveABookingSystemEndpoint(String arg0) {
        scenario.log("Base Uri :"+ Constants.configuration.get("url") + arg0);

    }

    @When("I enter the following data")
    public void iEnterTheFollowingData(DataTable data) throws JsonProcessingException {
        Map<String,String> map = data.transpose().asMap();
        Map<String,String> subMap  =  new HashMap<>();
        map.forEach((k,v)->{
            if(k.equals("checkin")){
                subMap.put(k,v);

            }else if(k.equals("checkout")){
                subMap.put(k,v);

            }
        });

        scenario.log(subMap.toString());
        scenario.log(map.toString());


        ObjectMapper mapper = new ObjectMapper();
        //JsonElement jsonElement = gson.toJsonTree(map);

        BookingDates bookingDates = mapper.convertValue(subMap, BookingDates.class);
        Customer customer = mapper.convertValue(map,Customer.class);

        customer.setBookingdates(bookingDates);
       // scenario.log(customer.toString());
       // payload = gson.toJson( jsonElement,Customer.class);

        //scenario.log(payload);
        //ObjectMapper mapper = new ObjectMapper();
       // Customer customer = mapper.convertValue(map, Customer.class);
        scenario.log(customer.toString());
        scenario.log(bookingDates.toString());

       // Customer customer = new Customer(map)
       // scenario.log(customer.toString());


        payload = mapper.writeValueAsString(customer);
        //Constants.dataMap = map;
        //payload = Library.createPayLoad("src/test/resources/templates/createBooking.tpl",map);
        scenario.log(payload);
    }

    @And("I post the data to the system")
    public void iPostTheDataToTheSystem() {

        headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        request = RestAssured.given();
        response = request
                .headers(headers)
                .body(payload)
                .post(  "/booking");


        scenario.log(response.getBody().prettyPrint());
        //Customer customer = gson.fromJson(response.getBody().prettyPrint(),Customer.class);
        //scenario.log(customer.getAdditionalneeds());


    }

    @And("the system displayes the booking ID")
    public void theSystemDisplayesTheBookingID() {
        Constants.bookingID = response.getBody().jsonPath().getString("bookingid");
        scenario.log(Constants.bookingID);

        assertThat(Constants.bookingID,is(notNullValue()));
    }

    @When("I get a booking using bookingid")
    public void iGetABookingUsingBookingid() {

        System.out.println( "/booking/"+Constants.bookingID);
        scenario.log("/booking/" +Constants.bookingID);
       // RestAssured.baseURI="https://restful-booker.herokuapp.com";
        headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        request = RestAssured.given();
        response = request
                .headers(headers)
                .get(  "/booking/"+Constants.bookingID);

       // scenario.log(Constants.bookingID);
        scenario.log("Response Get "+ response.getBody().prettyPrint());

    }





    @And("Validate the booking id")
    public void validateTheBookingId() {

        assertThat(response.body().jsonPath().getString("totalprice"),is(Constants.dataMap.get("totalprice")));
    }

    @And("Validate the lastname")
    public void validateTheLastname() {
        assertThat(response.body().jsonPath().getString("lastname"),is(Constants.dataMap.get("lastname")));

    }

    @Given("I am")
    public void iAm() {

    }

    @When("I get data")
    public void iGetData(DataTable dataTable) throws JsonProcessingException {
        Map<String,String > data = dataTable.transpose().asMap();

        ObjectMapper objectMapper = new ObjectMapper();

        Faker faker = new Faker();

        Address address = objectMapper.convertValue(data, Address.class);

        PhoneNumber phoneNumber= objectMapper.convertValue(data, PhoneNumber.class);
        System.out.println(phoneNumber);
        List<Address> addressList = new ArrayList<>();
        addressList.add(address);
        List<PhoneNumber> phoneNumberList = new ArrayList<>();
        phoneNumberList.add(phoneNumber);

        Student student = objectMapper.convertValue(data,Student.class);
        student.setFirstname(faker.name().firstName())  ;
        student.setAddress(addressList);
        student.setPhoneNumber(phoneNumberList);

                //objectMapper.convertValue(data,Student.class);
        payload = objectMapper.writeValueAsString(student);
        scenario.log(payload);
        System.out.println(payload);
    }


    @Given("I have an apple")
    public void iHaveAnApple() throws SQLException {
        databaseSetup = new DatabaseSetup();
        connection = databaseSetup.getConnection();
    }

    @When("I bite it using the data")
    public void iBiteItUsingTheData(DataTable table) throws SQLException {
        Map<String, String> map = table.transpose().asMap(String.class, String.class);


        productPage = new ProductPage(connection);
        List<Products> products = productPage.getProductsById(map.get("product_id"));

        products.forEach(product -> System.out.println(product.toString()));
    }


    @Given("I close the connection")
    public void closeDatabaseConnection() throws SQLException {
        if (databaseSetup != null) {
            connection.close();
        }
    }
}