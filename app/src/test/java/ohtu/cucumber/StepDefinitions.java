package ohtu.cucumber;

import gui.GUI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
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
        clickOn("#addbook");
    }
    
    @When("add article button is clicked")
    public void clickAddArticleButton() {
        clickOn("#addarticle");
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
    
    @When("form is filled with {string} as title and {string} as hyperlink")
    public void fillFormWith(String title, String hyperlink) {
        TextField titleField = find("#title");
        titleField.setText(title);
        TextField hyperlinkField = find("#hyperlink");
        hyperlinkField.setText(hyperlink);
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
    
    @When("an article with {string} as title and {string} as hyperlink is added") 
    public void applicationHasOpenedWithArticlesSaved(String title, String hyperlink) {
        clickAddArticleButton();
        fillFormWith(title, hyperlink);
        submitForm();
    }

    @When("the delete button next to a bookmark with {string} as name and {string} as author and {int} as page count is pressed")
    public void theDeleteButtonNextToBookmarkIsPressed(String name, String author, int pageCount) {
        ListView<Bookmark> listView = find("#listview");
        Bookmark book = new Book(name, author, pageCount);
        int i = listView.getItems().indexOf(book);
        book = listView.getItems().get(i);
        clickOn("#delete" + book.getId());
    }

    @When("the delete button next to a bookmark with {string} as title and {string} as hyperlink is pressed")
    public void theDeleteButtonNextToBookmarkIsPressed(String title, String hyperlink) {
        ListView<Bookmark> listView = find("#listview");
        Bookmark article = new Article(title, hyperlink);
        int i = listView.getItems().indexOf(article);
        article = listView.getItems().get(i);
        clickOn("#delete" + article.getId());
    }

    @When("{string} is clicked in the confirmation dialog")
    public void buttonWithMessageIsClicked(String message) {
        clickOn(message);
    }

    @When("{string} is entered as the search term")
    public void textIsEnteredAsTheSearchTerm(String text) {
        clickOn("#search").write(text);
    }

    @When("the search field is cleared")
    public void clearTheSearchField() {
        TextField searchField = find("#search");
        String text = searchField.getText();
        clickOn("#search");

        // setting text to "" or null did not work so I did it like this
        for (int i = 0; i < text.length(); i++) {
            push(KeyCode.BACK_SPACE);
        }
    }

    @Then("bookmark list contains a book with {string} as name and {string} as author and {int} as page count")
    public void bookListContainsAddedBook(String name, String author, int pageCount) {
        ListView<Bookmark> listView = find("#listview");
        Book book = new Book(name, author, pageCount);
        assertTrue(listView.getItems().contains(book));
    }
    
    @Then("bookmark list contains an article with {string} as title and {string} as hyperlink")
    public void bookListContainsAddedBook(String title, String hyperlink) {
        ListView<Bookmark> listView = find("#listview");
        Bookmark article = new Article(title, hyperlink);
        assertTrue(listView.getItems().contains(article));
    }
    
    
    @Then("the element number {int} of the bookmark list is a bookmark with {string} as name and {string} as author and {int} as page count")
    public void theBookAtIndexEquals(int index, String name, String author, int pageCount) {
        ListView<Bookmark> listView = find("#listview");
        Bookmark book = new Book(name, author, pageCount);
        assertTrue(listView.getItems().get(index - 1).equals(book));
    }

    @Then("the element number {int} of the bookmark list is a bookmark with {string} as title and {string} as hyperlink")
    public void theArticleAtIndexEquals(int index, String title, String hyperlink) {
        ListView<Bookmark> listView = find("#listview");
        Bookmark article = new Article(title, hyperlink);
        assertTrue(listView.getItems().get(index - 1).equals(article));
    }
    
    @Then("view contains error message {string}")
    public void viewContainsErrorMessage(String message) {
        Label error = find("#errorMessage");
        assertTrue(error.getText().equals(message));
        returnToMainPage();
    }

    @Then("the bookmark list is empty")
    public void bookmarkListIsEmpty() {
        checkThatBookmarkListSizeIs(0);
    }

    @Then("the bookmark list contains {int} bookmarks")
    public void checkThatBookmarkListSizeIs(int count) {
        ListView<Bookmark> listView = find("#listview");
        assertEquals(count, listView.getItems().size());
    }
    
    public void returnToMainPage() {
        clickOn("#back");
    }

    public void closeApplication() {
        clickOn("#exit");
    }
    
}