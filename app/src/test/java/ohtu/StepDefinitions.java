package ohtu;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import logic.Book;
import ohtu.miniprojekti.TestFXBase;
import org.testfx.api.FxAssert;

import static java.lang.Integer.parseInt;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class IsEverythingCorrect {
    static String isEverythingCorrect(String name, String author, Integer pages) {
        return ("Correct_Name").equals(name) && ("Correct_Author").equals(author) && (pages > 0) ? "Yes" : "No";
    }
}

public class StepDefinitions extends TestFXBase {
    private String name;
    private String author;
    private int pages;
    private String actualAnswer;
    private int listViewItemCount;
    private Book book;
    LukuvinkkiDAO service;
    private String comparableBook;

    @Given("book has name {string} author {string} and pages {int}")
    public void bookHasNameAuthorAndPages(String name, String author, int pages) {
        this.name = name;
        this.author = author;
        this.pages = pages;
    }

    @When("I ask whether they are filled in or not")
    public void iAskWhetherTheyAreFilledInOrNot() {
        if (this.name.isEmpty()) {
            actualAnswer = "not found";
        } else if (this.author.isEmpty()) {
            actualAnswer = "not found";
        } else if (this.pages <= 0) {
            actualAnswer = "No";
        } else {
            actualAnswer = IsEverythingCorrect.isEverythingCorrect(name, author, pages);
        }
    }

    @Then("I should be told {string}")
    public void iShouldBeTold(String expectedAnswer) {
        assertEquals(expectedAnswer, actualAnswer);
    }

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
    public void book_is_not_initialised_with_name_author_and_pages() {
        book = new Book();
    }

    @When("the book is added into the application")
    public void the_book_is_added_into_the_application() {
        try {
            this.service.addBook(book);
            this.comparableBook = this.service.findOne(this.book.getTitle()).getTitle();
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("it should not save the book into the service")
    public void it_should_not_save_the_book_into_the_service() {
        assertEquals(null, this.comparableBook);
    }
}
