package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CollectionsPage;
import pages.LoginPage;

public class CollectionsTest extends BaseTest {
    LoginPage loginPage;
    CollectionsPage collectionsPage;

    @Test(groups = "one", description = "Verifying that hyperlink to the Courses page is present in empty collections",
    priority = 2)
    public void redirectToCoursesPresentInEmptyCollectionTest() {
        loginPage = new LoginPage(driver);
        collectionsPage = new CollectionsPage(driver);

        loginPage.successfulLogin();
        collectionsPage.openCollections();
        Assert.assertTrue(collectionsPage.getRedirectToCoursesButton().isDisplayed());
    }

    @Test(groups = "two", description = "Verifying possibility to add course to collections and delete it", priority = 2)
    public void addAndDeleteCourseTest() {
        loginPage = new LoginPage(driver);
        collectionsPage = new CollectionsPage(driver);

        loginPage.successfulLogin();
        collectionsPage.openCollections();
        collectionsPage.goToCoursesPage();
        collectionsPage.openRandomCourse();
        collectionsPage.addCourseToCollections();
        collectionsPage.openCollections();
        Assert.assertTrue(collectionsPage.getDeleteCourseFromCollectionsButton().isDisplayed());

        collectionsPage.deleteCourseFromCollections();
        Assert.assertTrue(collectionsPage.getRedirectToCoursesButton().isDisplayed());
    }
}