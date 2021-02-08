package Tests;

import Pages.CollectionsPage;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CollectionsTest extends BaseTest {
    LoginPage loginPage;
    CollectionsPage collectionsPage;

    @Test
    public void redirectToCoursesPresentInEmptyCollectionTest() {
        loginPage = new LoginPage(driver);
        collectionsPage = new CollectionsPage(driver);

        loginPage.successfulLogin();
        collectionsPage.openCollections();
        Assert.assertTrue(collectionsPage.getRedirectToCoursesButton().isDisplayed());
    }

    @Test
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