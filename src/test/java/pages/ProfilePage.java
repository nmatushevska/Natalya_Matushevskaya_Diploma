package pages;

import io.qameta.allure.Step;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


@Log4j2
public class ProfilePage extends BasePage {

    @FindBy(xpath = "//a[text()=' Профиль ']")
    WebElement profileButton;

    @FindBy(xpath = "//button[text()='Редактировать']")
    WebElement editProfileButton;

    @FindBy(xpath = "//button[text()='Отмена']")
    WebElement cancelChangesButton;

    @FindBy(xpath = "//button[text()='Сохранить']")
    WebElement saveChangesButton;

    @FindBy(id = "about")
    WebElement aboutInputField;

    @Getter
    @FindBy(xpath = "//span[text()='Нет информации']")
    WebElement defaultAboutMessage;

    @Getter
    @FindBy(xpath = "//span[text()='Test account for diploma project']")
    WebElement changedAboutMessage;

    @Getter
    @FindBy(xpath = "//label[text()=' Мужчина ']")
    WebElement maleGenderRadioButton;

    @Getter
    @FindBy(xpath = "//label[text()=' Не выбран ']")
    WebElement noneGenderRadioButton;

    @Getter
    @FindBy(xpath = "//label[text()=' Женщина ']")
    WebElement femaleGenderRadioButton;

    @Getter
    @FindBy(xpath = "//span[text()='Не выбран']")
    WebElement noneGenderInfo;

    @Getter
    @FindBy(xpath = "//span[text()='Мужчина']")
    WebElement maleGenderInfo;

    @Getter
    @FindBy(xpath = "//span[text()='Женщина']")
    WebElement femaleGenderInfo;

    @FindBy(id = "education")
    WebElement educationDropdownMenu;

    @Getter
    @FindBy(xpath = "//span[text()='Не выбрано']")
    WebElement noEducationInfo;

    @Getter
    @FindBy(xpath = "//span[text()='Высшее образование']")
    WebElement higherEducationInfo;

    @Getter
    @FindBy(xpath = "//span[text()='Магистратура']")
    WebElement masterEducationInfo;

    private final String aboutText = "Test account for diploma project";

    public ProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("Opening user's profile page")
    public void openProfile() {
        openAccountTab(profileButton);
        log.info("User's profile page is opened");
    }

    @Step("Opening editing mode of user's profile")
    public void openProfileEditMode() {
        editProfileButton.click();
        log.info("Profile editing mode is opened");
    }

    @Step("Clicking to apply changes")
    public void applyChanges() {
        saveChangesButton.click();
        log.info("Changes are saved");
    }

    @Step("Clicking to cancel changes")
    public void cancelChanges() {
        cancelChangesButton.click();
        log.info("Changes are cancelled");
    }

    @Step("Adding info to the About field")
    public void addDefaultAboutInfo() {
        aboutInputField.sendKeys(aboutText);
        log.info("Text " + aboutText + " is added to About field");
    }

    @Step("Clearing the about field")
    public void clearAboutInfo() {
        aboutInputField.sendKeys(Keys.CONTROL + "a");
        aboutInputField.sendKeys(Keys.DELETE);
        log.info("About field is cleared");
    }

    @Step("Changing gender to female")
    public void changeToFemaleGender() {
        femaleGenderRadioButton.click();
        log.info("Female gender is selected");
    }

    @Step("Changing gender to male")
    public void changeToMaleGender() {
        maleGenderRadioButton.click();
        log.info("Male gender is selected");
    }

    @Step("Changing gender to none selected option")
    public void resetGender() {
        noneGenderRadioButton.click();
        log.info("No gender info option is selected");
    }

    @Step("Changing education to Higher")
    public void setHigherEducation() {
        Select select = new Select(educationDropdownMenu);
        select.selectByVisibleText(" Высшее образование ");
        log.info("Higher education option is selected");
    }

    @Step("Changing education to Master")
    public void setMasterEducation() {
        Select select = new Select(educationDropdownMenu);
        select.selectByVisibleText(" Магистратура ");
        log.info("Master education is selected");
    }

    @Step("Setting education to no selected option")
    public void resetEducation() {
        Select select = new Select(educationDropdownMenu);
        select.selectByVisibleText(" Не выбрано ");
        log.info("No education option is selected");
    }
}