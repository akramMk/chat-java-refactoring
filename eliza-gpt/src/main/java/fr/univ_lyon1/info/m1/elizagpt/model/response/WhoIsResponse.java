package fr.univ_lyon1.info.m1.elizagpt.model.response;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import fr.univ_lyon1.info.m1.elizagpt.interfaces.Response;
import fr.univ_lyon1.info.m1.elizagpt.model.classes.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.classes.User;
import fr.univ_lyon1.info.m1.elizagpt.model.classes.Verb;
import fr.univ_lyon1.info.m1.elizagpt.model.util.TextProcess;

/**
 * Represents a response to user input related to ocean information.
 */
public class WhoIsResponse implements Response {

    private Response nextHandler;

    /**
     * Sets the next handler in the Chain of Responsibility.
     *
     * @param nextHandler The next handler to be set.
     */
    @Override
    public void setNextHandler(final Response nextHandler) {
        this.nextHandler = nextHandler;
    }

    /**
     * Processes the user's text and generates a response.
     *
     * @param normalizedText The normalized text of the user's input.
     * @param user           The user for whom the response is generated.
     * @return A Message object containing the response to the user's input.
     */
    @Override
    public Message processUserText(final String normalizedText, final User user, 
    final List<Verb> listVerb) {
            Pattern pattern = Pattern.compile("Qui est le plus (.*) \\?", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(normalizedText);
        if (matcher.matches()) {
            String result = ("Le plus " + matcher.group(1) 
            + " est bien sûr votre enseignant de MIF01 !");

            return new Message(TextProcess.normalize(result), false);

        }
        return (nextHandler != null) ? nextHandler.processUserText(normalizedText, user, listVerb) 
        : null;  
    }
}
