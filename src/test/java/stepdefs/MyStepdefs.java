package stepdefs;

import com.automation.constants.Constants;
import com.automation.functions.Library;
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

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;



public class MyStepdefs {
    private Scenario scenario;
    private Response response;
    private RequestSpecification request;
    private Map<String,String> headers;
    private String payload;
    private String myBookingId;

    @BeforeStep
    public void startup(Scenario scenario){
       this.scenario = scenario;
    }


    @When("I get a author using id as {string} and url {string}")
    public void iGetAAuthorUsingIdAsAndUrl(String arg0, String arg1) {
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


    @Then("The system respond with status code {int}")
    public void theSystemRespondWithStatusCode(int arg0) {
        assertThat(response.getStatusCode(),is(arg0));
    }

    @Given("I have a booking system endpoint {string}")
    public void iHaveABookingSystemEndpoint(String arg0) {
        scenario.log("Base Uri :"+ Constants.configuration.get("url") + arg0);

    }

    @When("I enter the following data")
    public void iEnterTheFollowingData(DataTable data) {
        Map<String,String> map = data.transpose().asMap();
        payload = Library.createPayLoad("src/test/resources/templates/createBooking.tpl",map);
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
    }

    @And("the system displayes the booking ID")
    public void theSystemDisplayesTheBookingID() {
        String bookingId = response.getBody().jsonPath().getString("bookingid");
        scenario.log(bookingId);
        assertThat(bookingId,is(notNullValue()));
    }
}
