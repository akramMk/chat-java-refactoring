package fr.univ_lyon1.info.m1.elizagpt.model.response;

import java.util.List;
import java.util.Random;
import fr.univ_lyon1.info.m1.elizagpt.interfaces.Response;
import fr.univ_lyon1.info.m1.elizagpt.model.classes.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.classes.User;
import fr.univ_lyon1.info.m1.elizagpt.model.classes.Verb;
import fr.univ_lyon1.info.m1.elizagpt.model.util.TextProcess;

/**
 * Represents a response to user input related to ocean information.
 */
public class DefaultResponse implements Response {

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
        Random random = new Random();
        String[] responses = {
            "Il fait beau aujourd'hui, vous ne trouvez pas ?",
            "Je ne comprends pas.",
            "Hmmm, hmm ...",
            (user.getName() != null) ?  "Qu'est-ce qui vous fait dire cela, " 
            + user.getName() + " ?" : "Qu'est-ce qui vous fait dire cela ?"
    };

        int randomIndex = random.nextInt(responses.length);
        String result = responses[randomIndex];
        return new Message(TextProcess.normalize(result), false);
    }
}
