package Pages;

import io.qameta.allure.Step;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Log4j2
@Getter
public class CollectionsPage extends BasePage {

    @FindBy(xpath = "//a[@class='m-header__user']")
    WebElement accountDropDownMenu;

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
        WebDriverWait wait = new WebDriverWait(driver, 5);
        actions.moveToElement(accountDropDownMenu).perform();
        wait.until(ExpectedConditions.visibilityOf(collectionsButton));
        actions.moveToElement(collectionsButton).click().perform();
        log.info("Collections tab is opened");
    }

    @Step("Clicking on re-direct button to open Courses page")
    public void goToCoursesPage() {
        if (redirectToCoursesButton.isDisplayed()) {
            redirectToCoursesButton.click();
            log.info("Tap on redirect link to the Courses page");
        }
    }

    @Step("Opening random Course page")
    public void openRandomCourse() {
        randomCourseButton.click();
        log.info("Open random course page");
    }

    @Step("Adding random course to user's collections")
    public void addCourseToCollections() {
        addCourseToCollectionButton.click();
        log.info("Adding opened course to user's collection");
    }

    @Step("Deleting course from user's collections")
    public void deleteCourseFromCollections() {
        deleteCourseFromCollectionsButton.click();
        confirmDeleteCourse.click();
        log.info("Deleting course from collection");
    }
}
