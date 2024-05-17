package fr.univ_lyon1.info.m1.elizagpt.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import fr.univ_lyon1.info.m1.elizagpt.interfaces.Observer;
import fr.univ_lyon1.info.m1.elizagpt.interfaces.Search;
import fr.univ_lyon1.info.m1.elizagpt.model.MessageProcessor;
import fr.univ_lyon1.info.m1.elizagpt.model.classes.Message;
/**
 * Unit tests for the {@link Controller} class.
 */
public class ControllerTest {
    private Controller controller;
    private MessageProcessor mockedModel;
    private Observer mockedObserver;

    /**
     * Set up the test environment by creating instances of {@link Controller},
     * {@link MessageProcessor}, and {@link Observer}.
     */
    @BeforeEach
    public void setUp() {
        mockedModel = mock(MessageProcessor.class);
        controller = new Controller(mockedModel);
        mockedObserver = mock(Observer.class);
        controller.addObserver(mockedObserver);
    }

    /**
     * Test the undo search functionality.
     */
    @Test
    public void testUndoSearch() {
        // Assuming your undoSearch() method simply calls getMessages()
        List<Message> expectedMessages = Arrays.asList(
                new Message("Bonjour.", true),
                new Message("Hello", true),
                new Message("Hi", false)
        );

        // Mocking the behavior of the MessageProcessor
        when(mockedModel.getMessages()).thenReturn(expectedMessages);

        // Performing the action
        controller.undoSearch();

        // Verifying that the observer was notified with the expected messages
        verify(mockedObserver).update(expectedMessages);
    }

    /**
     * Test the send message functionality.
     */
    @Test
    public void testSendMessage() {
        // Assuming your sendMessage() method simply calls usertext() and then getMessages()
        String text = "Hello";
        List<Message> expectedMessages = Arrays.asList(
                new Message("Bonjour.", true),
                new Message("Hello", true),
                new Message("Hi", false)
        );

        // Mocking the behavior of the MessageProcessor
        when(mockedModel.getMessages()).thenReturn(expectedMessages);

        // Performing the action
        controller.sendMessage(text);

        // Verifying that the observer was notified with the expected messages
        verify(mockedObserver).update(expectedMessages);

        // Verifying that the usertext method was called with the correct argument
        verify(mockedModel).usertext(text);
    }

    /**
     * Test the delete message functionality.
     */
    @Test
    public void testDeleteMessage() {
        String messageIdToDelete = "123";

        // Performing the action
        controller.deleteMessage(messageIdToDelete);

        // Verifying that the deleteMessage method was called with the correct argument
        verify(mockedModel).deleteMessage(messageIdToDelete);

        // Verifying that getMessages was called after deleting a message
        verify(mockedModel).getMessages();
    }

    /**
     * Test the search text functionality.
     */
    @Test
    public void testSearchText() {
        String searchText = "Hello";
        Search searchStrategy = mock(Search.class);

        // Assuming your textsearch method simply calls notifyObserver
        List<Message> expectedSearchResults = Arrays.asList(
                new Message("Hello", true),
                new Message("Hello, world!", false)
        );

        // Mocking the behavior of the MessageProcessor
        // Adjust this based on the actual behavior of your textsearch method
        when(mockedModel.textsearch(searchText, searchStrategy)).thenReturn(expectedSearchResults);

        // Performing the action
        controller.searchText(searchText, searchStrategy);

        // Verifying that the observer was notified with the expected search results
        verify(mockedObserver).update(expectedSearchResults);
    }
}
