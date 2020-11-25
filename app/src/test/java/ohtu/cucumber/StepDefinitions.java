package ohtu.cucumber;

import gui.GUI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import logic.*;
import javafx.scene.control.Label;
import ohtu.junit.TestFXBase;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StepDefinitions extends TestFXBase {

    @Given("application has opened")
    public void applicationHasOpened() throws Exception {
        ApplicationTest.launch(GUI.class);
        WaitForAsyncUtils.waitForFxEvents();
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

    @When("the delete button next to a book with {string} as name and {string} as author and {int} as page count is pressed")
    public void theDeleteButtonNextToBookIsPressed(String name, String author, int pageCount) {
        ListView<Book> listView = find("#listview");
        Book book = new Book(name, author, pageCount);
        int i = listView.getItems().indexOf(book);
        book = listView.getItems().get(i);
        clickOn("#delete" + book.getId());
    }

    @Then("book list contains a book with {string} as name and {string} as author and {int} as page count")
    public void bookListContainsAddedBook(String name, String author, int pageCount) {
        ListView<Book> listView = find("#listview");
        Book book = new Book(name, author, pageCount);
        assertTrue(listView.getItems().contains(book));
    }

    @Then("the first element of the book list is a book with {string} as name and {string} as author " +
            "and {int} as page count")
    public void theFirstElementIsABookWith(String name, String author, int pageCount) {
        theBookAtIndexEquals(0, new Book(name, author, pageCount));
    }

    @Then("the second element of the book list is a book with {string} as name and {string} as author " +
            "and {int} as page count")
    public void theSecondElementIsABookWith(String name, String author, int pageCount) {
        theBookAtIndexEquals(1, new Book(name, author, pageCount));
    }

    @Then("the third element of the book list is a book with {string} as name and {string} as author " +
            "and {int} as page count")
    public void theThirdElementIsABookWith(String name, String author, int pageCount) {
        theBookAtIndexEquals(2, new Book(name, author, pageCount));
    }

    private void theBookAtIndexEquals(int index, Book book) {
        ListView<Book> listView = find("#listview");
        assertTrue(listView.getItems().get(index).equals(book));
    }
    
    @Then("view contains error message {string}")
    public void viewContainsErrorMessage(String message) {
        Label error = find("#errorMessage");
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