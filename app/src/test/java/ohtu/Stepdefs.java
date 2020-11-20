
package ohtu;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import logic.Book;
import logic.LukuvinkkiService;
import static org.junit.Assert.*;
import java.sql.SQLException;

public class Stepdefs {
	Book book;
	LukuvinkkiService service;

	@Given("a name {string}, an author {string} and a page number {int}")
	public void a_name_an_author_and_a_page_number(String name, String author, int pagenumber) {
		this.book = new Book(name, author, pagenumber);
	}

	@When("it is filled with the name of the book")
	public void adding_a_book_into_the_system() throws Exception {
		this.service.addBook(this.book);
	}

	@Then("the book is saved into the system")
	public void the_book_is_saved_into_the_system() {
		try {
			assertEquals(this.service.findOne(this.book.getTitle()).getTitle(), this.book.getTitle());
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}


	//
	//@Given("a name, an author and a page number")
	//public void a_name_an_author_and_a_page_number() {
	//  	// Write code here that turns the phrase above into concrete actions
	//	throw new io.cucumber.java.PendingException();
	//}

	//@When("a book is given these three values and added")
	//public void a_book_is_given_these_three_values_and_added() {
	//	// Write code here that turns the phrase above into concrete actions
	//	throw new io.cucumber.java.PendingException();
	//}

	//@Then("the service should be updated with that book")
	//public void the_service_should_be_updated_with_that_book() {
	//	// Write code here that turns the phrase above into concrete actions
	//	throw new io.cucumber.java.PendingException();
	//}
}

