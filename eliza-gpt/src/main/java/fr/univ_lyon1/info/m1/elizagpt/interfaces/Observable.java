package fr.univ_lyon1.info.m1.elizagpt.interfaces;

import java.util.List;
import fr.univ_lyon1.info.m1.elizagpt.model.classes.Message;

/**
 * Interface for implementing the Observable pattern to manage a list of observers.
 */
public interface Observable {

    /**
     * Adds an observer to the list of observers.
     *
     * @param observer The observer to be added.
     */
    void addObserver(Observer observer);

    /**
     * Removes an observer from the list of observers.
     *
     * @param observer The observer to be removed.
     */
    void removeObserver(Observer observer);

    /**
     * Notifies all registered observers with a list of messages.
     *
     * @param messages The list of messages to be sent to the observers.
     */
    void notifyObserver(List<Message> messages);
}
