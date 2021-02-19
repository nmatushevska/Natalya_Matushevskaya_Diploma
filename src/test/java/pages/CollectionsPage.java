package pages;

import io.qameta.allure.Step;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Log4j2
@Getter
public class CollectionsPage extends BasePage {

    @FindBy(xpath = "//a[text()=' Коллекции ']")
    WebElement collectionsButton;

    @FindBy(xpath = "//a[text()=' Выбрать, с чего начать ']")
    WebElement redirectToCoursesButton;

    @FindBy(xpath = "//a[@class='course-card__title']")
    WebElement randomCourseButton;

    @FindBy(xpath = "//a[text()=' Сохранить курс ']")
    WebElement addCourseToCollectionButton;

    @FindBy(xpath = "//a[text()=' Отписаться от Курса ']")
    WebElement deleteCourseFromCollectionsButton;

    @FindBy(xpath = "//a[text()=' Да, хочу отписаться ']")
    WebElement confirmDeleteCourse;

    public CollectionsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("Opening Collections tab in user account")
    public void openCollections() {
        openAccountTab(collectionsButton);
        log.info("Collections tab is opened");
    }

    @Step("Clicking on re-direct button to open Courses page")
    public void goToCoursesPage() {
        redirectToCoursesButton.click();
        log.info("Tap on redirect link to the Courses page");
    }

    @Step("Opening random Course page")
    public void openRandomCourse() {
        randomCourseButton.click();
        log.info("Open random course page");
    }

    @Step("Adding random course to user's collections")
    public void addCourseToCollections() {
        WebDriverWait waitForPageIsLoaded = new WebDriverWait(driver, 10);
        waitForPageIsLoaded.until(
                webDriver -> Objects.equals(((JavascriptExecutor) webDriver).
                                executeScript("return document.readyState"),"complete"));
        addCourseToCollectionButton.click();
        log.info("Adding opened course to user's collection");
    }

    @Step("Deleting course from user's collections")
    public void deleteCourseFromCollections() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        deleteCourseFromCollectionsButton.click();
        confirmDeleteCourse.click();
        log.info("Deleting course from collection");
    }
}
