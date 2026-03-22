package com.tutorialsninja.tests;

import com.tutorialsninja.base.BaseTest;
import com.tutorialsninja.pages.HomePage;
import com.tutorialsninja.pages.LoginPage;
import com.tutorialsninja.pojo.RegistrationForm;
import com.tutorialsninja.util.JsonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test
    public void verifyLoggingUsingValidCredentialTest(){
        HomePage homePage = new HomePage(getDriver());
        homePage.clickLoginLink();
        LoginPage loginPage = new LoginPage(getDriver());
        RegistrationForm user = JsonUtils.getJsonNode(0);
        loginPage.performLogin(user.getEmail(), "59");
        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertNotNull(currentUrl);
        Assert.assertTrue(currentUrl.contains("account/account"));
    }

}
