package com.tutorialsninja.util;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestDataHelper {

    // Using a single instance of Faker to generate random data for tests
    //private final static Faker FAKER = Faker.instance();

    // Using ThreadLocal to ensure that each thread gets its own instance of Faker, which is important for thread safety when running tests in parallel
    // The initialValue() method is overridden to create a new instance of Faker for each thread when it is accessed for the first time.
    // This ensures that each thread has its own separate instance of Faker, preventing any potential issues with shared state when running tests in parallel.
    /* private static final ThreadLocal<Faker> fakerThreadLocal = new ThreadLocal<Faker>() {
        @Override
        protected Faker initialValue() {
            return new Faker(); // Create a new instance of Faker for each thread
        }
    };*/

    // Alternatively, you can use the withInitial() method of ThreadLocal to achieve the same result in a more concise way:
    // Method Reference is used to create a new instance of Faker for each thread when it is accessed for the first time.
    // This is a more concise way to achieve the same result as the previous implementation using an anonymous class.
    private static final ThreadLocal<Faker> fakerThreadLocal = ThreadLocal.withInitial(Faker::new);


    // Method to get the Faker instance
    public static Faker getFaker() {
        return fakerThreadLocal.get();
    }

    // Method to unload the Faker instance from the ThreadLocal variable to prevent memory leaks
    public static void unload() {
        fakerThreadLocal.remove();
    }

    // Method to generate a random first name
    public static String generateRandomFirstName() {
        return fakerThreadLocal.get().name().firstName();
    }

    // Method to generate a random last name
    public static String generateRandomLastName() {
        return fakerThreadLocal.get().name().lastName();
    }

    // Method to generate a random email address
    public static String generateRandomEmail() {
        return fakerThreadLocal.get().internet().emailAddress();
    }

    public static String generateRandomTelephoneNumber() {
        return fakerThreadLocal.get().phoneNumber().cellPhone();
    }

    // Method to generate a random password
    public static String generateRandomPassword() {
        return fakerThreadLocal.get().internet().password(8, 16); // Password between 8 and 16 characters
    }

    // Method to generate a random number
    public static long generateRandomNumber() {
        return fakerThreadLocal.get().number().randomNumber();
    }

    // Method to generate a date in the future
    public static String generateFutureDate(int days, DateTimeFormatter formatter) {
        return LocalDate.now()
                        .plusDays(days).format(formatter);
    }
}
