package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static stepDefinitions.Hooks.driver;
import static stepDefinitions.Hooks.log;

public class LoginPageSteps {

    @When("I open {string}")
    public void i_open(String url) {
        log.info("Navigating to", url);
        driver.get(url);
    }

    @Then("I verify Login details displayed")
    public void i_verify_login_details_displayed() {
        WebElement userNameTxt = driver.findElement(By.xpath("//*[@name='username']"));
        WebElement passwordTxt = driver.findElement(By.xpath("//*[@name='password']"));
        WebElement loginBtn = driver.findElement(By.xpath("//*[@type='submit']"));
        Assert.assertTrue("Username is not displayed", userNameTxt.isDisplayed());
        Assert.assertTrue("Password is not displayed", passwordTxt.isDisplayed());
        Assert.assertTrue("Login button is not displayed", loginBtn.isDisplayed());
    }

}
