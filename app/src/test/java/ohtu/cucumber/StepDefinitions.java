package ohtu.cucumber;

import gui.GUI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import logic.*;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ohtu.junit.TestFXBase;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StepDefinitions extends TestFXBase {

    private final int errorMessageInd = 8;

    @Given("application has opened")
    public void applicationHasOpened() throws Exception {
        ApplicationTest.launch(GUI.class);
    }

    @When("add book button is clicked")
    public void clickAddBookButton() {
        clickOn("#add");
    }

    @When("form is filled with {string} as name and {string} as author and {string} as page count")
    public void fillFormWith(String name, String author, String pageCount) {
        TextField nameField = find("#name");
        nameField.setText(name);
        TextField authorField = find("#author");
        authorField.setText(author);
        TextField pageCountField = find("#pageCount");
        pageCountField.setText(pageCount);

    }

    @When("form is submitted")
    public void submitForm() {
        clickOn("#submit");
    }

    @When("a book with {string} as name and {string} as author and {string} as page count is added")
    public void applicationHasOpenedWithBooksSaved(String name, String author, String pageCount) {
        clickAddBookButton();
        fillFormWith(name, author, pageCount);
        submitForm();
    }

    @Then("book list contains a book with {string} as name and {string} as author and {int} as page count")
    public void bookListContainsAddedBook(String name, String author, int pageCount) {
        ListView<Book> listView = find("#listview");
        Book book = new Book(name, author, pageCount);
        assertTrue(listView.getItems().contains(book));
    }
    
    @Then("view contains error message {string}")
    public void viewContainsErrorMessage(String message) {
        VBox addView = find("#addview");
        Label error = (Label) addView.getChildren().get(errorMessageInd);
        assertTrue(error.getText().equals(message));
        returnToMainPage();
    }

    @Then("the book list is empty")
    public void bookListIsEmpty() {
        checkThatBookListSizeIs(0);
    }

    @Then("the book list contains {int} books")
    public void checkThatBookListSizeIs(int count) {
        ListView<Book> listView = find("#listview");
        assertEquals(count, listView.getItems().size());
    }
    
    public void returnToMainPage() {
        clickOn("#back");
    }

    public void closeApplication() {
        clickOn("#exit");
    }
    
}