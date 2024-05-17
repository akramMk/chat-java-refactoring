package fr.univ_lyon1.info.m1.elizagpt.view;

import java.util.List;
import fr.univ_lyon1.info.m1.elizagpt.controller.Controller;
import fr.univ_lyon1.info.m1.elizagpt.interfaces.Observer;
import fr.univ_lyon1.info.m1.elizagpt.interfaces.Search;
import fr.univ_lyon1.info.m1.elizagpt.model.classes.Message;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main class of the View (GUI) of the application.
 */
public class JfxView implements Observer, EventHandler<ActionEvent> {
    private final VBox dialog;
    private TextField text = null;
    private TextField searchText = null;
    private Label searchTextLabel = null;
    private Button sendButton;
    private Button searchButton;
    private Button undoButton;
    private Button deleteButton;
    private ComboBox<Search> strategyComboBox = null;
    private Controller controller = null;

    static final String BASE_STYLE = "-fx-padding: 8px; "
            + "-fx-margin: 5px; "
            + "-fx-background-radius: 15px;";
    static final String USER_STYLE = "-fx-background-color: #5DADE2; " + BASE_STYLE;
    static final String ELIZA_STYLE = "-fx-background-color: #A9A9A9; " + BASE_STYLE;
            

    /**
     * Create the main view of the application.
     */
    public JfxView(
            final Stage stage,
            final int width,
            final int height,
            final Controller controller) {
        
        sendButton = new Button("Send");
        sendButton.setStyle("-fx-background-color: #2ECC71;"); // Green color
        text = new TextField();
        searchButton = new Button("Search");
        searchButton.setStyle("-fx-background-color: #3498DB;"); // Blue color
        searchText = new TextField();
        searchTextLabel = new Label();
        undoButton = new Button("Undo search");
        undoButton.setStyle("-fx-background-color: #E74C3C;"); // Red color
        searchButton.setOnAction(this);
        searchText.setOnAction(this);
        sendButton.setOnAction(this);
        text.setOnAction(this);
        undoButton.setOnAction(this);
        this.controller = controller;
        controller.addObserver(this);
        stage.setTitle("Eliza GPT");
        final VBox root = new VBox(10);
        final Pane search = createSearchWidget();
        root.getChildren().add(search);
        ScrollPane dialogScroll = new ScrollPane();
        dialog = new VBox(10);
        dialogScroll.setContent(dialog);
        // scroll to bottom by default:
        dialogScroll.vvalueProperty().bind(dialog.heightProperty());
        dialog.setStyle("-fx-background-color: #EAEAEA;");  // Set grey background color
        root.getChildren().add(dialogScroll);
        dialogScroll.setFitToWidth(true);
        controller.getMessages();
        final Pane input = createInputWidget();
        root.getChildren().add(input);
        // Everything's ready: add it to the scene and display it
        final Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        text.requestFocus();
        stage.show();
    }

    /**
     * Creates the search widget UI components, including the search input field,
     * search strategy selection combo box, and buttons for executing and undoing
     * searches.
     *
     * @return The Pane containing the search widget.
     */
    private Pane createSearchWidget() {
        final HBox firstLine = new HBox();
        final HBox secondLine = new HBox();
        firstLine.setAlignment(Pos.CENTER);
        secondLine.setAlignment(Pos.CENTER);
        strategyComboBox = new ComboBox<>();
        strategyComboBox.getItems().addAll(controller.getAvailableSearchStrategies());
        if (!strategyComboBox.getItems().isEmpty()) {
            strategyComboBox.setValue(strategyComboBox.getItems().get(0));
        }
        firstLine.getChildren().addAll(searchText, strategyComboBox);
        secondLine.getChildren().addAll(searchButton, searchTextLabel, undoButton);
        final VBox input = new VBox();
        input.getChildren().addAll(firstLine, secondLine);
        return input;
    }

    /**
     * Creates the input widget UI components, including the text input field and
     * the "Send" button.
     *
     * @return The Pane containing the input widget.
     */
    private Pane createInputWidget() {
        final HBox firstLine = new HBox();
        firstLine.setAlignment(Pos.CENTER);
        firstLine.getChildren().addAll(text, sendButton);
        final VBox input = new VBox();
        input.getChildren().addAll(firstLine);
        return input;
    }

    
    /**
     * Updates the view with a list of messages, displaying them in the conversation
     * dialog.
     *
     * @param messages The list of messages to display in the conversation dialog.
     */
    @Override
    public void update(final List<Message> messages) {
        // Clear existing messages in the view
        dialog.getChildren().clear();
        if (!messages.isEmpty()) {
            // Iterate through the list of messages and add them to the view
            for (Message message : messages) {
                HBox outerHBox = new HBox(); // Outer HBox
                HBox innerHBox = new HBox(); // Inner HBox

                final Label label = new Label(message.getContent());
                deleteButton = new Button("x");

                innerHBox.getChildren().addAll(label, deleteButton);
                outerHBox.getChildren().add(innerHBox);

                // Set IDs for HBoxes and Label
                outerHBox.setId(message.getId());
                if (message.isUser()) {
                    outerHBox.setAlignment(Pos.BASELINE_RIGHT);
                    innerHBox.setStyle(USER_STYLE);
                } else {
                    outerHBox.setAlignment(Pos.BASELINE_LEFT);
                    innerHBox.setStyle(ELIZA_STYLE);
                }

                dialog.getChildren().add(outerHBox);

                // Set up click event for message deletion
                deleteButton.setOnAction(e -> {
                    controller.deleteMessage(outerHBox.getId());
                });
            }
        }
    }

    @Override
    public void handle(final ActionEvent event) {
        if (event.getSource() == searchButton || event.getSource() == searchText) {
            // Gestion de l'événement pour le bouton de recherche ou le champ de recherche
            searchTextLabel.setText(searchText.getText().isEmpty() ? "No active search"
                    : "Searching using regular expression: " + searchText.getText());
            controller.searchText(searchText.getText(), strategyComboBox.getValue());
            searchText.setText("");

        } else if (event.getSource() == sendButton || event.getSource() == text) {
            // Gestion de l'événement pour le bouton d'envoi ou le champ de texte
            controller.sendMessage(text.getText());
            text.setText("");
        } else if (event.getSource() == undoButton) {
            // Gestion de l'événement pour le bouton d'undo ou le champ de texte
            controller.undoSearch();
            searchTextLabel.setText("");
        }
    }
}
