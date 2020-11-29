package ohtu.junit;

import gui.GUI;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.junit.*;

import org.testfx.api.FxAssert;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.util.WaitForAsyncUtils;

public class TestFXBase extends ApplicationTest {

    @Before
    public void setUpClass() throws Exception {
        System.setProperty("isTestEnvironment", "true");
        ApplicationTest.launch(GUI.class);
    }

    @Override
    public void start(Stage stage) {
        stage.show();
    }

    @Test
    public void thereIsAButtonForAddingBooks() {
        WaitForAsyncUtils.waitForFxEvents();
        FxAssert.verifyThat(".button", LabeledMatchers.hasText("Lisää kirja"));
    }
    
    @Test
    public void thereIsLabelForSearchingForASpecificBook() {
        WaitForAsyncUtils.waitForFxEvents();
        FxAssert.verifyThat(".label", LabeledMatchers.hasText("Etsi nimellä"));
    }

    public <T extends Node> T find(final String query) {
        return lookup(query).query();
    }
    
}
