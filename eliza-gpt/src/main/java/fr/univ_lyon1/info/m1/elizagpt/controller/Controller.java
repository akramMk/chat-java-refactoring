package fr.univ_lyon1.info.m1.elizagpt.controller;

import java.util.ArrayList;
import java.util.List;
import fr.univ_lyon1.info.m1.elizagpt.interfaces.Observable;
import fr.univ_lyon1.info.m1.elizagpt.interfaces.Observer;
import fr.univ_lyon1.info.m1.elizagpt.interfaces.Search;
import fr.univ_lyon1.info.m1.elizagpt.model.MessageProcessor;
import fr.univ_lyon1.info.m1.elizagpt.model.classes.Message;

/**
 * The Controller class serves as an intermediary between the user interface and the underlying
 * data model (MessageProcessor). It implements the Observable interface to allow registration
 * and notification of observers when changes occur in the underlying model.
 *
 */
public final class Controller implements Observable {
    private final  MessageProcessor model;
    private List<Observer> observers;

    /**
     * Constructs a new Controller with the specified MessageProcessor as the underlying model.
     *
     * @param model The MessageProcessor instance to be associated with this Controller.
     */
    public Controller(final MessageProcessor model) {
        this.model = model;
        observers = new ArrayList<>();
    }

    /**
     * Retrieves messages from the underlying model and notifies observers.
     */
    public void getMessages() {
        notifyObserver(model.getMessages());
    }

    /**
     * Sends a message to the underlying model, updates the user text, and retrieves messages.
     *
     * @param text The text of the message to be sent.
     */
    public void sendMessage(final String text) {
        model.usertext(text);
        getMessages();
    }

    /**
     * Deletes a message with the specified ID from the underlying model and retrieves messages.
     *
     * @param id The ID of the message to be deleted.
     */
    public void deleteMessage(final String id) {
        model.deleteMessage(id);
        getMessages();
    }

    /**
     * Undoes the previous search operation and retrieves messages.
     */
    public void undoSearch() {
        getMessages();
    }

    /**
     * Performs a text search based on the provided search strategy and notifies observers
     * with the search results.
     *
     * @param text   The text to be searched.
     * @param search The search strategy to be applied.
     */
    public void searchText(final String text, final Search search) {
        notifyObserver(model.textsearch(text, search));
    }

    public List<Search> getAvailableSearchStrategies() {
        // Get the search strategies from the model
        // Return the list of available search strategies
        // (You may need to modify the return type based on your implementation)
        return model.getSearchStrategy();
    }
    

    @Override
    public void addObserver(final Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(final Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver(final List<Message> message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
