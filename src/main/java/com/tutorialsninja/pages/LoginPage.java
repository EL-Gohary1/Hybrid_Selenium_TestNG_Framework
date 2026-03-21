package com.tutorialsninja.pages;

import com.tutorialsninja.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends BasePage {

    @FindBy(id = "input-email")
    WebElement emailField;

    @FindBy(id = "input-password")
    WebElement passwordField;

    @FindBy(xpath ="//input[@value='Login']")
    WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void clickLoginButton() {
        loginButton.click();
    }
    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }
    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void performLogin(String email,  String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

}
