package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class Hooks {
    public static final List<String[]> testResults = new CopyOnWriteArrayList<>();
    public static final Logger log = LogManager.getLogger(Hooks.class);
    public static WebDriver driver;
    static Scenario scenarioName;


    @Before
    public static void setUp(Scenario scenario) {
        try {
            System.setProperty("systeminfo.user", System.getProperty("os.name"));
            log.info("Killing Driver before test execution");
            Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
            Runtime.getRuntime().exec("taskkill /F /IM msedgedriver.exe /T");
            Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe /T");
            scenarioName = scenario;
//            String browser = "edge";
            String browser = System.getProperty("browser");
            switch (browser.toLowerCase()) {
                case "chrome" -> driver = new ChromeDriver();
                case "firefox" -> driver = new FirefoxDriver();
                case "edge" -> driver = new EdgeDriver();
                case "safari" -> driver = new SafariDriver();
                case "ie" -> driver = new InternetExplorerDriver();
                default -> throw new IllegalArgumentException("Unexpected value: " + browser);
            }
            log.info(browser + " browser is launched");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        } catch (Exception e) {
            log.error("setUp Exception: {}", e);
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            String status = scenario.getStatus().toString();
            testResults.add(new String[]{scenario.getName(), status});
            driver.quit();
            log.info("Killing Driver after test execution");
            Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
            Runtime.getRuntime().exec("taskkill /F /IM msedgedriver.exe /T");
            Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe /T");
        } catch (Exception e) {
            log.error("tearDown Exception: {}", e);
        }
    }

    @AfterStep
    public void addScreenshot(Scenario scenario) {
        try {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        } catch (WebDriverException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}