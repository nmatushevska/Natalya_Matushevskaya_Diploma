package Tests;

import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{

    LoginPage loginPage;

    @Test(groups = "one", description = "Successful logging in to the app")
    public void successfulLoginTest(){
        loginPage = new LoginPage(driver);

        loginPage.successfulLogin();
        Assert.assertNotNull(loginPage.getUserName());
    }

    @Test(groups = "one", description ="Failed logging in to the app due to invalid password")
    public void failedLogin(){
        loginPage = new LoginPage(driver);

        loginPage.failedLogin();
        Assert.assertTrue(loginPage.getLogInErrorMessage().isDisplayed());
    }
}