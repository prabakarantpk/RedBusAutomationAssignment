package com.redbus;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class redBusSmokeTestAutomation {

    public static void main(String[] args) {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");

        WebDriver wd = new ChromeDriver(chromeOptions);
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(30));

        wd.get("https://www.redbus.in");

        By sourceButtonLocator = By.xpath("//div[contains(@class,\"srcDestWrapper\") and @role=\"button\"]");
        WebElement sourceButton = wait.until(ExpectedConditions.visibilityOfElementLocated(sourceButtonLocator));
        sourceButton.click();

        By searchSuggestionSectionLocator = By.xpath("//div[contains(@class,\"searchSuggestionWrapper\")]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchSuggestionSectionLocator));

        selectLocation(wd, wait, "Bangalore");// For location
        selectLocation(wd, wait, "Erode"); // to location


        By searchButtonLocator = By.xpath("//button[contains(@class,\"searchButtonWrapper\")]");
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(searchButtonLocator));
        searchButton.click();

        By primoButtonLocator = By.xpath("//div[contains(text(),\"Primo\")]");
        WebElement primoButton = wait.until(ExpectedConditions.elementToBeClickable(primoButtonLocator));
        primoButton.click();

        By tuppleWrapperLocator = By.xpath("//li[contains(@class,\"tupleWrapper\")]");// Found the row locator
        By busesNameLocator = By.xpath(".//div[contains(@class,\"travelsName\")] ");// bus name locator
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tuppleWrapperLocator));

        By departureTimeFromSource = By.xpath("//div[text()='Departure time from source']");
        WebElement departureTimeFromSourceButton = wait.until(ExpectedConditions.elementToBeClickable(departureTimeFromSource));
        departureTimeFromSourceButton.click();

        By eveningButtonLocator = By.xpath("(//div[text()='Evening'])[1]");
        WebElement eveningButton = wait.until(ExpectedConditions.elementToBeClickable(eveningButtonLocator));
        eveningButton.click();

        By subTitleLocator = By.xpath("//span[contains(@class,\"subtitle\")]");

        WebElement subTitle = null;
        if (wait.until(ExpectedConditions.textToBePresentInElementLocated(subTitleLocator, "buses"))) {
            subTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(subTitleLocator));

        }
        assert subTitle != null;
        System.out.println(subTitle.getText());

        JavascriptExecutor js = (JavascriptExecutor) wd;

        while (true) { // Lazy Loading

            List<WebElement> rowList = wait
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tuppleWrapperLocator));
            List<WebElement> endOfList = wd.findElements(By.xpath("//span[contains(text(),\"End of list\")]"));

            if (!endOfList.isEmpty()) {
                break; // exit condition for the while loop
            }

            js.executeScript("arguments[0].scrollIntoView({behavior:'smooth'})", rowList.get(rowList.size() - 3));

        }

        List<WebElement> rowList = wait
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tuppleWrapperLocator));
        for (WebElement row : rowList) {
            String busName = row.findElement(busesNameLocator).getText();
            System.out.println(busName);
        }

        System.out.println("Total Number of Buses Loaded with Primo and Evening Filter is " + rowList.size());
        wd.quit();
    }

    public static void selectLocation(WebDriver wd, WebDriverWait wait, String locationData) {
        WebElement searchTextBoxElement = wd.switchTo().activeElement(); // give me that textbox!!
        searchTextBoxElement.sendKeys(locationData);
        By searchCategoryLocator = By.xpath(String.format("//div[contains(@class,'listHeader') and text()='%s']", locationData));

        WebElement searchResult = wait.until(ExpectedConditions.elementToBeClickable(searchCategoryLocator));
        searchResult.click();
    }

}