package com.tutorialsninja.util;

import com.tutorialsninja.pojo.RegistrationForm;

public class TestDataGenerator {

    public static RegistrationForm getValidRegisterData() {
        return new RegistrationForm(TestDataHelper.generateRandomFirstName(), TestDataHelper.generateRandomLastName(),TestDataHelper.generateRandomEmail(), TestDataHelper.generateRandomTelephoneNumber() ,TestDataHelper.generateRandomPassword());

    }

}
