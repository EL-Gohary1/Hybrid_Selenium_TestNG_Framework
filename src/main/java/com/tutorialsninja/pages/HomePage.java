package com.tutorialsninja.pages;

import com.tutorialsninja.Base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(linkText = "My Account")
    WebElement myAccountLink;

    @FindBy(linkText = "Register")
    WebElement registerLink;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickRegisterLink() {
        myAccountLink.click();
        registerLink.click();
    }


}
