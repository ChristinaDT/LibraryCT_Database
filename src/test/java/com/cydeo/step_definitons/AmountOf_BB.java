package com.cydeo.step_definitons;

import com.cydeo.Utils.BrowserUtil;
import com.cydeo.Utils.DB_Util;
import com.cydeo.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class AmountOf_BB {

    LoginPage loginPage = new LoginPage();
    String expectedBB; //BB means Borrowed Books

    @Given("I am in the homepage of library app")
    public void i_am_in_the_homepage_of_library_app() {
        loginPage.login();
    }

    @When("I take borrowed books number")
    public void i_take_borrowed_books_number() {
        BrowserUtil.waitFor(5);
        expectedBB = loginPage.borrowedBooksNumber.getText();
        System.out.println("expected Borrowed Books = " + expectedBB);
    }

    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {

        String query = "select count(*) from book_borrow where is_returned=0";
        DB_Util.runQuery(query);
        String actualBB = DB_Util.getCellValue(1, 1); // BB means Borrowed Books

        System.out.println("actual Borrowed Books = " + actualBB);

        Assert.assertEquals(expectedBB, actualBB);


    }


}
