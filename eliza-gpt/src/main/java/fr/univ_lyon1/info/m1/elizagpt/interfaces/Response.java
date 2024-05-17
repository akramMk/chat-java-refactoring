package fr.univ_lyon1.info.m1.elizagpt.interfaces;

import java.util.List;
import fr.univ_lyon1.info.m1.elizagpt.model.classes.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.classes.User;
import fr.univ_lyon1.info.m1.elizagpt.model.classes.Verb;

/**
 * Interface for generating responses based on user input.
 */
public interface Response {

    /**
     * Processes the user's normalized text and generates an appropriate response.
     *
     * @param normalizedText The normalized text of the user's input.
     * @param user The User object representing the current user.
     * @param listVerb List of Verb objects representing the verbs in the user's input.
     * @return A Message object representing the generated response.
     */
    Message processUserText(String normalizedText, User user, List<Verb> listVerb);
    /**
     * Set the next handler instance.
     */
    void setNextHandler(Response nextHandler);
}
