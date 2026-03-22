package com.tutorialsninja.util;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScreenshotHelper {

	// Method to take screenshot when the test cases fail
	public static String captureScreenshot(WebDriver driver , String screenshotName)
	{
		Path dest = Paths.get("./screenshots",screenshotName+".png");
		try {
			Files.createDirectories(dest.getParent());
			FileOutputStream out = new FileOutputStream(dest.toString());
			out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
			out.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return dest.toString();
	}
}
