package fr.univ_lyon1.info.m1.elizagpt.model;

import java.util.ArrayList;
import java.util.List;
import fr.univ_lyon1.info.m1.elizagpt.interfaces.Response;
import fr.univ_lyon1.info.m1.elizagpt.interfaces.Search;
import fr.univ_lyon1.info.m1.elizagpt.model.classes.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.classes.User;
import fr.univ_lyon1.info.m1.elizagpt.model.classes.Verb;
import fr.univ_lyon1.info.m1.elizagpt.model.dao.MessageDAO;
import fr.univ_lyon1.info.m1.elizagpt.model.dao.VerbDAO;
import fr.univ_lyon1.info.m1.elizagpt.model.response.ResponseFactory;
import fr.univ_lyon1.info.m1.elizagpt.model.searchStrategy.RegexSearchStrategy;
import fr.univ_lyon1.info.m1.elizagpt.model.searchStrategy.SubstringSearchStrategy;
import fr.univ_lyon1.info.m1.elizagpt.model.searchStrategy.WordCompleteSearchStrategy;
import fr.univ_lyon1.info.m1.elizagpt.model.util.TextProcess;
/**
 * The MessageProcessor class is responsible for 
 * processing user messages and generating appropriate responses.
 * It interacts with a list of messages, a list of verbs, and manages the user context.
 * The class also includes methods for searching, deleting messages,
 * and retrieving messages and verbs.
 */
public class MessageProcessor {

    private MessageDAO messageList;
    private VerbDAO verblist;
    private User user; 
    private Response response;

    /**
     * Constructs a MessageProcessor object, 
     * initializing the message list, verb list, user, and response list.
     */
    public MessageProcessor() {
        verblist = new VerbDAO();
        messageList = new MessageDAO();
        user = new User();
        messageList.addMessage(new Message("Bonjour.", false));
        response = ResponseFactory.createResponseChain();
    }

    /**
     * Processes user input, normalizes the text, and attempts to generate a response.
     * If no specific response is matched, a default response is generated.
     *
     * @param text The user input text.
     */
    public void usertext(final String text) {
        Message result;
        final String normalizedText = TextProcess.normalize(text);
        messageList.addMessage(new Message(normalizedText, true));
        result = response.processUserText(normalizedText, user, getAllVerbs());
        messageList.addMessage(result);
    }

    /**
     * Searches for messages based on a provided search criterion and text.
     *
     * @param text   The text to search for.
     * @param search The search strategy.
     * @return A list of messages that match the search criterion.
     */
    public List<Message> textsearch(final String text, final Search search) {
        List<Message> toDelete = new ArrayList<>();
        for (Message message : messageList.getAllMessages()) {
            if (search.execute(message, text)) {
                toDelete.add(message);
            }
        }
        return toDelete;
    }

    /**
     * Deletes a message with the specified ID from the message list.
     *
     * @param id The ID of the message to be deleted.
     */
    public void deleteMessage(final String id) {
        messageList.deleteMessageById(id);
    }

    /**
     * Retrieves a list of all messages stored in the message list.
     *
     * @return A list of Message objects.
     */
    public List<Message> getMessages() {
        return messageList.getAllMessages();
    }

    /**
     * Retrieves a list of all verbs stored in the verb list.
     *
     * @return A list of Verb objects.
     */
    public List<Verb> getAllVerbs() {
        return verblist.getAllMessages();
    }

    /**
     * Retrieves a list of Search Strategy.
     *
     * @return A list of Search objects.
     */
    public List<Search> getSearchStrategy() {
        List<Search> search = new ArrayList<>();
        search.add(RegexSearchStrategy.getInstance());
        search.add(SubstringSearchStrategy.getInstance());
        search.add(WordCompleteSearchStrategy.getInstance());
        return search;
    }

}
