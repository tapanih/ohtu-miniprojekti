package gui;

import dao.BookmarkDao;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import logic.Bookmark;
import java.util.Optional;

public class TagCell extends ListCell<String> {
    
    private String tag;
    private final GridPane pane;
    private final ListView<String> listView;
    private final Label tagLabel;
    private final Button deleteButton;

    public TagCell(Bookmark bookmark, ListView<String> listView, ObservableList<String> tags, BookmarkDao service) {
        super();
        this.listView = listView;
        deleteButton = new Button("Poista");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Haluatko varmasti poistaa kyseisen tagin?");

        deleteButton.setOnAction(event -> {
            Optional<ButtonType> result = alert.showAndWait();

            // check if "X" or "Cancel" is pressed
            if (result.isEmpty() || result.get() == ButtonType.CANCEL) {
                return;
            }
            service.deleteTag(bookmark, this.tag);
            tags.remove(this.tag);
        });

        pane = new GridPane();
        tagLabel = new Label();
        HBox deleteButtonBox = new HBox();
        deleteButtonBox.getChildren().add(deleteButton);
        HBox labelBox = new HBox();
        pane.add(labelBox, 0, 0);
        ColumnConstraints contraints = new ColumnConstraints();
        contraints.setHgrow(Priority.ALWAYS);
        pane.getColumnConstraints().add(contraints);
        labelBox.getChildren().add(tagLabel);
        pane.add(deleteButtonBox, 2, 0);
    }

    @Override
    public void updateItem(String tag, boolean empty) {
        super.updateItem(tag, empty);
        this.tag = tag;
        if (empty || tag == null) {
            setText(null);
            setGraphic(null);
            updateListView(listView);
        } else {
            tagLabel.setText(tag);
            deleteButton.setId("delete" + tag);
            setGraphic(pane);
            updateListView(listView);
        }
    }
}
