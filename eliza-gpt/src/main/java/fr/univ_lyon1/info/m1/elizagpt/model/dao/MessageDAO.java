package fr.univ_lyon1.info.m1.elizagpt.model.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.univ_lyon1.info.m1.elizagpt.interfaces.DAO;
import fr.univ_lyon1.info.m1.elizagpt.model.classes.Message;

/**
 * The MessageDAO class represents a Data Access Object for managing Message objects.
 */
public class MessageDAO implements DAO<Message, String> {
    private List<Message> messageList;

    /**
     * Constructs a new MessageDAO object with an empty list of messages.
     */
    public MessageDAO() {
        messageList = new ArrayList<>();
    }

     /**
     * Retrieves a copy of all messages stored in the DAO.
     *
     * @return A list containing all messages.
     */
    public List<Message> getAllMessages() {
        return new ArrayList<>(messageList);
    }

    /**
     * Adds a new message to the DAO.
     *
     * @param message The Message object to be added.
     */
    public void addMessage(final Message message) {
        messageList.add(message);
    }

    /**
     * Retrieves a message by its unique identifier.
     *
     * @param id The unique identifier of the message.
     * @return The Message object with the specified ID, or null if not found.
     */
    public Message getMessageById(final String id) {
        for (Message message : messageList) {
            if (message.getId().equals(id)) {
                return message;
            }
        }
        return null;
    }

    /**
     * Deletes a message by its unique identifier.
     *
     * @param id The unique identifier of the message to be deleted.
     */
    public void deleteMessageById(final String id) {
        Iterator<Message> iterator = messageList.iterator();
        while (iterator.hasNext()) {
            Message message = iterator.next();
            if (message.getId().equals(id)) {
                iterator.remove();
                break; // Stop after deleting the first matching message
            }
        }
    }
}
