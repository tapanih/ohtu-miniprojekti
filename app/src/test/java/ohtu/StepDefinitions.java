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
}
