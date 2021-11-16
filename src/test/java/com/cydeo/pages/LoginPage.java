package com.cydeo.pages;

import com.cydeo.Utils.ConfigReader;
import com.cydeo.Utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public LoginPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//*[@id=\"inputEmail\"]")
    public WebElement emailBox;

    @FindBy(id = "inputPassword")
    public WebElement passwordBox;

    @FindBy(tagName = "button")
    public WebElement loginButton;

    @FindBy(id = "borrowed_books")
    public WebElement borrowedBooksNumber;

    public void login() {

        String username = ConfigReader.read("usernameUi");
        String password = ConfigReader.read("passwordUi");

        emailBox.sendKeys(username);
        passwordBox.sendKeys(password);
        loginButton.click();

    }


}
