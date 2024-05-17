package fr.univ_lyon1.info.m1.elizagpt.model.response;

import java.util.List;
import java.util.Random;
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
public class IamResponse implements Response {

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
        Pattern pattern = Pattern.compile("(Je .*)\\.", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(normalizedText);
        if (matcher.matches()) {
            Random random = new Random();
            String[] array = {"Pourquoi dites-vous que ", "Pourquoi pensez-vous que ",
             "Êtes-vous sûr que "};
            // Generate a random index within the range of the array length
            int randomIndex = random.nextInt(array.length);
            final String startQuestion = array[randomIndex];
            String result = (startQuestion 
            + TextProcess.firstToSecondPerson(listVerb, matcher.group(1)) + " ?");
            return new Message(TextProcess.normalize(result), false);
        }
        // If the query is not handled by this handler, pass it to the next handler in the chain
        return (nextHandler != null) ? nextHandler.processUserText(normalizedText, user, listVerb) 
        : null;  
    }
}
