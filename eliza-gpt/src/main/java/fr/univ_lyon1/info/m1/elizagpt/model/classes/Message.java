package fr.univ_lyon1.info.m1.elizagpt.model.classes;

/**
 * Represents a message in the chat application.
 */
public class Message {
    private static int nextId = 0;
    private final String id;
    private final String content;
    private final boolean isUser; // true if is user

    /**
     * Constructs a Message object with the specified content and user status.
     *
     * @param content The content of the message.
     * @param isUser  A boolean indicating whether the message is from a user (true) or not (false).
     */
    public Message(final String content, final boolean isUser) {
        this.id = Integer.toString((nextId++));
        this.content = content;
        this.isUser = isUser;
    }

    /**
     * Gets the unique identifier of the message.
     *
     * @return The message ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the content of the message.
     *
     * @return The content of the message.
     */
    public String getContent() {
        return content;
    }

    /**
     * Checks if the message is from a user.
     *
     * @return True if the message is from a user, false otherwise.
     */
    public boolean isUser() {
        return isUser;
    }
}
