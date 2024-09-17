import com.automation.constants.Constants;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.xml.XmlTest;

import java.util.Map;

@CucumberOptions(
        plugin={ "pretty","json:target/cucumber.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        features="src/test/resources/features",
        tags=""
)
@Slf4j
public class TestRunnerParallel extends AbstractTestNGCucumberTests {

    @BeforeClass(alwaysRun = true)
    @Override
    public void setUpClass(ITestContext context) {
        super.setUpClass(context);

        XmlTest currentXml = context.getCurrentXmlTest();
        Map<String,String> params = currentXml.getAllParameters();
        Constants.configuration = params;
        params.forEach((k,v)->log.info(k +":" +v));


    }

    @DataProvider(parallel = true)
    @Override
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
