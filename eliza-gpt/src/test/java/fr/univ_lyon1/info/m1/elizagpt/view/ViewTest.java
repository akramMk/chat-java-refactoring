// package fr.univ_lyon1.info.m1.elizagpt.view;

// import javafx.scene.control.Button;
// import javafx.scene.control.ComboBox;
// import javafx.scene.control.TextField;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.VBox;
// import javafx.stage.Stage;
// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.testfx.util.WaitForAsyncUtils;
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.Mockito.mock;
// import static org.mockito.Mockito.reset;
// import static org.mockito.Mockito.verify;
// import java.lang.reflect.Field;
// import java.util.ArrayList;
// import java.util.List;
// import fr.univ_lyon1.info.m1.elizagpt.controller.Controller;
// import fr.univ_lyon1.info.m1.elizagpt.interfaces.Search;
// import fr.univ_lyon1.info.m1.elizagpt.model.classes.Message;
// import javafx.application.Platform;
// import javafx.event.ActionEvent;

// /**
//  * This class contains test cases for the {@link JfxView} class, which is part
//  * of the
//  * JavaFX-based user interface for the ElizaGPT application.
//  * The tests cover message display, sending messages, deleting messages, and
//  * searching text.
//  * These tests utilize JavaFX testing tools and Mockito for mocking
//  * dependencies.
//  */
// public class ViewTest {
//     private JfxView jfxView;
//     private Controller mockController;
//     private Stage mockStage;

//     /**
//      * Initializes the JavaFX toolkit before running the test cases. This method is
//      * annotated
//      * with {@code @BeforeAll} to ensure it is executed only once before all test
//      * cases in the
//      * test class.
//      * <p>
//      * This method is necessary to initialize the JavaFX toolkit before any JavaFX
//      * components
//      * are created or accessed in the test cases. The initialization is done using
//      * {@link Platform#startup(Runnable)} on the JavaFX application thread.
//      * </p>
//      * <p>
//      * Note: The {@code Platform.startup} method is a no-op, as the purpose of this
//      * method is
//      * to trigger the initialization of the JavaFX toolkit before running the tests.
//      * </p>
//      *
//      * @throws IllegalStateException if the JavaFX toolkit initialization fails
//      */
//     @BeforeAll
//     public static void initJavaFX() {
//         // Initialize JavaFX toolkit
//         Platform.startup(() -> {
//             // No-op
//         });
//     }

//     /**
//      * Initialize the necessary components for testing before each individual test
//      * case.
//      * This includes creating a mock {@link Controller}, a mock {@link Stage}, and
//      * initializing the {@link JfxView} on the JavaFX application thread.
//      *
//      * @throws Exception if an exception occurs during the setup process
//      */
//     @BeforeEach
//     public void setUp() {
//         mockController = mock(Controller.class);
//         mockStage = mock(Stage.class);

//         // Use Platform.runLater to initialize JavaFX components on the JavaFX
//         // application thread
//         Platform.runLater(() -> {
//             jfxView = new JfxView(mockStage, 800, 600, mockController);
//         });

//         // Wait for JavaFX initialization to complete
//         WaitForAsyncUtils.waitForFxEvents();
//     }

//     /**
//      * Test the display of messages in the {@link JfxView} by updating it with a
//      * list of messages
//      * and checking if the messages are correctly rendered in the view.
//      *
//      * @throws NoSuchFieldException   if a field could not be found during
//      *                                reflection
//      * @throws IllegalAccessException if a field could not be accessed during
//      *                                reflection
//      */
//     @Test
//     public void testMessageDisplay() throws NoSuchFieldException, IllegalAccessException {
//         // Create a list of messages for testing
//         List<Message> messages = new ArrayList<>();
//         messages.add(new Message("User Message", true));
//         messages.add(new Message("Eliza Message", false));

//         // Call the update method to display messages
//         jfxView.update(messages);

//         // Use reflection to access the private VBox field
//         Field dialogField = JfxView.class.getDeclaredField("dialog");
//         dialogField.setAccessible(true);
//         VBox dialogVBox = (VBox) dialogField.get(jfxView);

//         // Verify that the messages are displayed in the view
//         assertEquals(messages.size(), dialogVBox.getChildren().size());
//     }

//     /**
//      * Test that sending a message from the {@link JfxView} triggers the appropriate
//      * method
//      * in the associated {@link Controller}.
//      *
//      * @throws NoSuchFieldException     if a field could not be found during
//      *                                  reflection
//      * @throws SecurityException        if a security violation occurred during
//      *                                  reflection
//      * @throws IllegalArgumentException if an illegal argument was passed during
//      *                                  reflection
//      * @throws IllegalAccessException   if a field could not be accessed during
//      *                                  reflection
//      */
//     @Test
//     public void testSendMessageCallsController()
//             throws NoSuchFieldException, SecurityException, 
//             IllegalArgumentException, IllegalAccessException {
//         // Get the text field from the view
//         // Use reflection to access the private textField field
//         Field textFieldField = JfxView.class.getDeclaredField("text");
//         textFieldField.setAccessible(true);

//         // Get the text field from the view using reflection
//         TextField textField = (TextField) textFieldField.get(jfxView);
//         // Set some text in the text field
//         textField.setText("Test Message");

//         // Trigger an action (e.g., pressing Enter key)
//         jfxView.handle(new ActionEvent(textField, null));

//         // Verify that the controller's sendMessage method is called with the correct
//         // argument
//         verify(mockController).sendMessage("Test Message");

//     }

//     /**
//      * Test that deleting a message from the {@link JfxView} triggers the
//      * appropriate method
//      * in the associated {@link Controller}.
//      *
//      * @throws NoSuchFieldException   if a field could not be found during
//      *                                reflection
//      * @throws IllegalAccessException if a field could not be accessed during
//      *                                reflection
//      */
//     @Test
//     public void testDeleteMessageCallsController() 
//     throws NoSuchFieldException, IllegalAccessException {
//         // Create a list of messages for testing
//         List<Message> messages = new ArrayList<>();
//         Message testMessage = new Message("Test Message", true);
//         messages.add(testMessage);

//         // Call the update method to display the test message
//         jfxView.update(messages);

//         // Use reflection to access the private VBox field
//         Field dialogField = JfxView.class.getDeclaredField("dialog");
//         dialogField.setAccessible(true);
//         VBox dialogVBox = (VBox) dialogField.get(jfxView);

//         // Get the delete button associated with the test message
//         // Assuming the test message is the first message
//         HBox messageHBox = (HBox) dialogVBox.getChildren().get(0); 
//         HBox innerHBox = (HBox) messageHBox.getChildren().get(0);
//         Button deleteButton = (Button) innerHBox.getChildren().get(1);

//         // Trigger an action (e.g., clicking the delete button)
//         deleteButton.getOnAction().handle(null);

//         // Verify that the controller's deleteMessage method is called with the correct
//         // argument
//         verify(mockController).deleteMessage(testMessage.getId());

//     }

//     /**
//      * Test that searching for text from the {@link JfxView} triggers the
//      * appropriate method
//      * in the associated {@link Controller}.
//      *
//      * @throws NoSuchFieldException   if a field
//      *                                could not be found during
//      *                                reflection
//      * @throws IllegalAccessException if a field 
//      *                                could not be accessed during
//      *                                reflection
//      */
//     @Test
//     public void testSearchTextCallsController() 
//     throws NoSuchFieldException, IllegalAccessException {
//         // Get the search text field from the view
//         // Use reflection to access the private searchText field
//         Field searchTextField = JfxView.class.getDeclaredField("searchText");
//         searchTextField.setAccessible(true);

//         // Get the search text field from the view using reflection
//         TextField searchText = (TextField) searchTextField.get(jfxView);

//         // Set some text in the search text field
//         searchText.setText("Test Search Text");

//         // Trigger an action (e.g., pressing Enter key)
//         jfxView.handle(new ActionEvent(searchText, null));

//         // Use reflection to access the private ComboBox field
//         Field strategyComboBoxField = JfxView.class.getDeclaredField("strategyComboBox");
//         strategyComboBoxField.setAccessible(true);
//         ComboBox<Search> strategyComboBox = 
//         (ComboBox<Search>) strategyComboBoxField.get(jfxView);

//         // Verify that the controller's searchText method is called with the correct
//         // arguments
//         verify(mockController).searchText("Test Search Text", strategyComboBox.getValue());
//     }

//     /**
//      * Reset the mock {@link Controller} after each test to ensure independence
//      * between tests.
//      */
//     @AfterEach
//     public void tearDown() {
//         reset(mockController);
//     }
// }
