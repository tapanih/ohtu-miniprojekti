package ohtu;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import logic.*;
import dao.*;
import ohtu.miniprojekti.TestFXBase;
import org.testfx.api.FxAssert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StepDefinitions extends TestFXBase {
    private int listViewItemCount;
    private Book book;
    BookmarkDao service;
    private String comparableBook;

    @Given("application has opened")
    public void applicationHasOpened() {
        ListView<Book> listView = find("#listview");
        this.listViewItemCount = listView.getItems().size();
    }

    @When("add book button is clicked")
    public void addBookButtonIsClicked() {
        clickOn("#add");
    }

    @When("form is filled with {string} as name and {string} as author and {string} as page count")
    public void formIsFilled(String name, String author, String pageCount) {
        TextField nameField = find("#name");
        nameField.setText(name);
        TextField authorField = find("#author");
        authorField.setText(author);
        TextField pageCountField = find("#pageCount");
        pageCountField.setText(pageCount);

        clickOn("#submit");
    }

    @Then("book list contains a book with {string} as name and {string} as author and {int} as page count")
    public void bookListContainsAddedBook(String name, String author, int pageCount) {
        ListView<Book> listView = find("#listview");
        Book book = new Book(name, author, pageCount);
        assertTrue(listView.getItems().contains(book));
        assertEquals(listViewItemCount + 1, listView.getItems().size());
    }
    @Given("book is not initialised with name author and pages")
    public void bookIsNotInitializedWithNameAuthorAndPages() {
        book = new Book();
    }

    @When("the book is added into the application")
    public void theBookIsAddedIntoTheApplication() {
        try {
            this.service.addBook(book);
            this.comparableBook = this.service.findOne(this.book.getTitle()).getTitle();
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("it should not save the book into the service")
    public void itShouldNotSaveTheBookIntoTheService() {
        assertEquals(null, this.comparableBook);
    }
}
