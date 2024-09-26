package stepdefs;

import com.automation.constants.Constants;
import com.automation.functions.Library;
import com.automation.pojos.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.HashMap;
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


    @BeforeStep
    public void startup(Scenario scenario){
       this.scenario = scenario;

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
    public void iEnterTheFollowingData(DataTable data) {
        Map<String,String> map = data.transpose().asMap();
        //gson =new Gson();
        //JsonElement jsonElement = gson.toJsonTree(map);

        //Customer customer = gson.fromJson(jsonElement,Customer.class);
        //scenario.log(customer.toString());
       // payload = gson.toJson( jsonElement,Customer.class);

        //scenario.log(payload);
        ObjectMapper mapper = new ObjectMapper();
        Customer customer = mapper.convertValue(map, Customer.class);
        scenario.log(customer.toString());

       // Customer customer = new Customer(map)
       // scenario.log(customer.toString());


        //Constants.dataMap = map;
        //payload = Library.createPayLoad("src/test/resources/templates/createBooking.tpl",map);
        //scenario.log(payload);
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
        Customer customer = gson.fromJson(response.getBody().prettyPrint(),Customer.class);
        scenario.log(customer.getAdditionalneeds());


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
}
