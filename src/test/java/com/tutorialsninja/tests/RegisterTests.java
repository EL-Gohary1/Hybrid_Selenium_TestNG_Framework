package com.tutorialsninja.tests;

import com.tutorialsninja.Base.BaseTest;
import com.tutorialsninja.pages.HomePage;
import com.tutorialsninja.pages.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterTests extends BaseTest {

    @Test
    public void verifyRegisteringByProvidingOnlyMandatoryFields(){
        HomePage homePage = new HomePage(getDriver());
        homePage.clickRegisterLink();
        RegisterPage registerPage = new RegisterPage(getDriver());
        registerPage.userRegistration("ahmed","mohamed","ahmedmohamed989911@gmail.com","01145614734","95123","95123");
        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertNotNull(currentUrl);
        Assert.assertTrue(currentUrl.contains("success"));
    }
}
