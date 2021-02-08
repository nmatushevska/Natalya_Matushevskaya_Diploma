package Tests;

import Pages.LoginPage;
import Pages.ProfilePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTest extends BaseTest {
    LoginPage loginPage;
    ProfilePage profilePage;

    @Test
    public void addAndDeleteAboutInfoTest() {
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);

        loginPage.successfulLogin();
        profilePage.openProfile();
        profilePage.openProfileEditMode();
        profilePage.addAboutInfo();
        profilePage.cancelChanges();
        Assert.assertTrue(profilePage.getDefaultAboutMessage().isDisplayed());

        profilePage.openProfileEditMode();
        profilePage.addAboutInfo();
        profilePage.applyChanges();
        Assert.assertTrue(profilePage.getChangedAboutMessage().isDisplayed());

        profilePage.openProfileEditMode();
        profilePage.clearAboutInfo();
        profilePage.applyChanges();
        Assert.assertTrue(profilePage.getDefaultAboutMessage().isDisplayed());
    }

    @Test
    public void changeGenderTest() {
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);

        loginPage.successfulLogin();
        profilePage.openProfile();
        Assert.assertTrue(profilePage.getNoneGenderInfo().isDisplayed());

        profilePage.openProfileEditMode();
        profilePage.changeToFemaleGender();
        profilePage.applyChanges();
        Assert.assertTrue(profilePage.getFemaleGenderInfo().isDisplayed());

        profilePage.openProfileEditMode();
        profilePage.changeToMaleGender();
        profilePage.applyChanges();
        Assert.assertTrue(profilePage.getMaleGenderInfo().isDisplayed());

        profilePage.openProfileEditMode();
        profilePage.resetGender();
        profilePage.applyChanges();
        Assert.assertTrue(profilePage.getNoneGenderInfo().isDisplayed());
    }

    @Test
    public void changeEducationTest() {
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);

        loginPage.successfulLogin();
        profilePage.openProfile();
        Assert.assertTrue(profilePage.getNoEducationInfo().isDisplayed());

        profilePage.openProfileEditMode();
        profilePage.setHigherEducation();
        profilePage.applyChanges();
        Assert.assertTrue(profilePage.getHigherEducationInfo().isDisplayed());

        profilePage.openProfileEditMode();
        profilePage.setMasterEducation();
        profilePage.applyChanges();
        Assert.assertTrue(profilePage.getMasterEducationInfo().isDisplayed());

        profilePage.openProfileEditMode();
        profilePage.resetEducation();
        profilePage.applyChanges();
        Assert.assertTrue(profilePage.getNoEducationInfo().isDisplayed());
    }
}