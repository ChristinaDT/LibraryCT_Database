package com.cydeo.step_definitons;

import com.cydeo.Utils.ConfigReader;
import com.cydeo.Utils.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AmountOfPeople_BB {

    int PeopleBB; //BB means Borrowed Books

    @Given("Establish the database connection")
    public void establish_the_database_connection() {
        DB_Util.createConnection(ConfigReader.read("DBurl"),
                ConfigReader.read("DBusername"), ConfigReader.read("DBpassword"));
    }

    @When("I execute query to inner join users and book_borrow on Id")
    public void i_execute_query_to_inner_join_users_and_book_borrow_on_id() {

        String query = "select count(*) from book_borrow where user_id";
        DB_Util.runQuery(query);
        String users = DB_Util.getCellValue(1, 1);
        System.out.println("Total users who has borrowed books including the one who has returned " + users);

        String query2 = "select count(*) from book_borrow where is_returned=1";
        DB_Util.runQuery(query2);
        String notBorrowedBooks = DB_Util.getCellValue(1, 1);
        System.out.println("Total users who has already returned books " + notBorrowedBooks);

        PeopleBB = Integer.parseInt(users) - Integer.parseInt(notBorrowedBooks);

    }

    @Then("verify amount of people who had borrowed books")
    public void verify_amount_of_people_who_had_borrowed_books() {

        System.out.println("Amount of people who had borrowed books on DB: " + PeopleBB);


    }

}
