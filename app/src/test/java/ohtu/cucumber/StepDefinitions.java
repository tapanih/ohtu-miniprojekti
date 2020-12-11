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
        clickOn("#deleteBook" + book.getId());
    }

    @When("the delete button next to a bookmark with {string} as title and {string} as hyperlink is pressed")
    public void theDeleteButtonNextToBookmarkIsPressed(String title, String hyperlink) {
        ListView<Bookmark> listView = find("#listview");
        Bookmark article = new Article(title, hyperlink);
        int i = listView.getItems().indexOf(article);
        article = listView.getItems().get(i);
        clickOn("#deleteArticle" + article.getId());
    }

    @When("the details button next to a book with {string} as title and {string} as author and {int} as page count is clicked")
    public void bookDetailsButtonIsPressed(String title, String author, int pageCount) {
        ListView<Bookmark> listView = find("#listview");
        Bookmark book = new Book(title, author, pageCount);
        int i = listView.getItems().indexOf(book);
        book = listView.getItems().get(i);
        clickOn("#itemBook" + book.getId());
    }

    @When("the details button next to an article with {string} as title and {string} as hyperlink is clicked")
    public void articleDetailsButtonIsPressed(String title, String hyperlink) {
        ListView<Bookmark> listView = find("#listview");
        Bookmark article = new Article(title, hyperlink);
        int i = listView.getItems().indexOf(article);
        article = listView.getItems().get(i);
        clickOn("#itemArticle" + article.getId());
    }

    @When("the delete button next to a tag named {string} is clicked")
    public void deleteButtonNextToTagIsClicked(String tag) {
        clickOn("#delete" + tag);
    }

    @When("{string} is entered as a new tag to be added")
    public void tagTextFieldIsFilledWith(String tag) {
        TextField textField = find("#addTag");
        textField.setText(tag);
    }

    @When("button to add the entered tag is clicked")
    public void buttonToAddTagIsClicked() {
        clickOn("Lisää tagi");
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
    
    @When("search by tags is clicked")
    public void searchByTagsIsClicked() {
        clickOn("Hae tagin perusteella");
    }

    @When("search by books is clicked")
    public void searchByBooksIsClicked() {
        clickOn("Hae otsikon perusteella");
    }

    @When("the return button is pressed")
    public void theReturnButtonIsPressed(){
        clickOn("Takaisin");
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
        assertEquals(listView.getItems().get(index - 1), book);
    }

    @Then("the element number {int} of the bookmark list is a bookmark with {string} as title and {string} as hyperlink")
    public void theArticleAtIndexEquals(int index, String title, String hyperlink) {
        ListView<Bookmark> listView = find("#listview");
        Bookmark article = new Article(title, hyperlink);
        assertEquals(listView.getItems().get(index - 1), article);
    }
    
    @Then("view contains error message {string}")
    public void viewContainsErrorMessage(String message) {
        Label error = find("#errorMessage");
        assertEquals(error.getText(), message);
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

    @Then("the tag list contains {int} tags")
    public void checkThatTagListSizeIs(int count) {
        ListView<String> listView = find("#taglistview");
        assertEquals(count, listView.getItems().size());
    }
    
    public void returnToMainPage() {
        clickOn("#back");
    }

    public void closeApplication() {
        clickOn("#exit");
    }
    
}
