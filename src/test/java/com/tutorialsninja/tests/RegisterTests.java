package com.tutorialsninja.tests;

import com.tutorialsninja.base.BaseTest;
import com.tutorialsninja.dataproviders.RegisterDataProvider;
import com.tutorialsninja.pages.HomePage;
import com.tutorialsninja.pages.RegisterPage;
import com.tutorialsninja.pojo.RegistrationForm;
import com.tutorialsninja.util.ExcelUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterTests extends BaseTest {

    @Test(dataProvider = "validRegistrationData", dataProviderClass = RegisterDataProvider.class)
    public void verifyRegisteringByProvidingOnlyMandatoryFields(RegistrationForm  registrationForm) {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickRegisterLink();
        RegisterPage registerPage = new RegisterPage(getDriver());
        registerPage.userRegistration(registrationForm.getFirstName(),registrationForm.getLastName(),registrationForm.getEmail(), registrationForm.getTelephone(), registrationForm.getPassword(),registrationForm.getPassword());
        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertNotNull(currentUrl);
        Assert.assertTrue(currentUrl.contains("success"));
        ExcelUtils.writeToExcel(registrationForm.toArray());
    }
}
