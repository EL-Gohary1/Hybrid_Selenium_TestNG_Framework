package com.tutorialsninja.pages;

import com.tutorialsninja.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {

    @FindBy(id = "input-firstname")
    WebElement fnTextBox;

    @FindBy(id = "input-lastname")
    WebElement lnTextBox;

    @FindBy(id = "input-email")
    WebElement emailTextBox;

    @FindBy(id = "input-telephone")
    WebElement telephoneTextBox;

    @FindBy(id = "input-password")
    WebElement passwordTextBox;

    @FindBy(id = "input-confirm")
    WebElement confirmPasswordTextBox;

    @FindBy(css = "input[name='agree']")
    WebElement policyCheckBox;

    @FindBy(css = ".btn.btn-primary")
    WebElement registerBtn;

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void clickRegisterBtn() {
        registerBtn.click();
    }

    public void enterFirstName(String firstName) {
        fnTextBox.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        lnTextBox.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        emailTextBox.sendKeys(email);
    }

    public void enterTel(String tel) {
        telephoneTextBox.sendKeys(tel);
    }

    public void selectPolicy() {
        policyCheckBox.click();
    }

    public void enterPassword(String password) {
        passwordTextBox.sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        confirmPasswordTextBox.sendKeys(confirmPassword);
    }

    public void userRegistration(String firstName, String lastName, String email,String tel, String password, String confirmPassword) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterEmail(email);
        enterTel(tel);
        selectPolicy();
        enterPassword(password);
        enterConfirmPassword(confirmPassword);
        clickRegisterBtn();
    }
}
