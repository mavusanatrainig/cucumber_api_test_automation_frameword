import com.automation.constants.Constants;

import io.cucumber.java.After;
import io.cucumber.java.Before;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.log4j.Logger;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Hooks {


    @Before
    public void setup () throws MalformedURLException {

            log.info("Initialize base URL");
            RestAssured.baseURI=Constants.configuration.get("url");

    }

    @After
    public void closure(){

        log.info("Scenario successfully completed");

    }
}
