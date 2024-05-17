package fr.univ_lyon1.info.m1.elizagpt.interfaces;

import java.util.List;
import fr.univ_lyon1.info.m1.elizagpt.model.classes.Message;

/**
 * Interface for implementing the Observer pattern to receive updates on a list of messages.
 */
public interface Observer {

    /**
     * Updates the observer with a new list of messages.
     *
     * @param messages The list of messages to be provided as an update.
     */
    void update(List<Message> messages);
}
