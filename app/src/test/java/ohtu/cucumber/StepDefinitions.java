package ohtu.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import logic.*;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ohtu.junit.TestFXBase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StepDefinitions extends TestFXBase {
    
    private int listViewItemCount;
    private final int errorMessageInd = 8;

    @Given("application has opened")
    public void applicationHasOpened() {
        ListView<Book> listView = find("#listview");
        this.listViewItemCount = listView.getItems().size();
        
        //Tulostetaan, mitä listassa näkyy
        System.out.println(listViewItemCount);
        ObservableList<Book> books = listView.getItems();
        for(Book book : books){
            System.out.println(book);
        }
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
        System.out.println("OK!");
        ListView<Book> listView = find("#listview");
        System.out.println("OK!");
        Book book = new Book(name, author, pageCount);
        assertTrue(listView.getItems().contains(book));
        assertEquals(listViewItemCount + 1, listView.getItems().size());
    }
    
    @Then("view contains error message {string}")
    public void viewContainsErrorMessage(String message) {
        VBox addView = find("#addview");
        Label error = (Label) addView.getChildren().get(errorMessageInd);
        assertTrue(error.getText().equals(message));
        returnToMainPage();
    }
    
    public void returnToMainPage() {
        clickOn("#back");
    }
    
    public void closeApplication() {
        clickOn("#exit");
    }
    
}