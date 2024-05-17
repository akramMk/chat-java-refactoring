package fr.univ_lyon1.info.m1.elizagpt.model.classes;

/**
 * The User class represents a user in the application.
 */
public final class User {

    // User's name
    private String name;

    /**
     * public constructor to prevent direct instantiation of the User class.
     */
    public User() {
        this.name = null;
    }

    /**
     * Gets the name of the user.
     *
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name The new name to set for the user.
     */
    public void setName(final String name) {
        this.name = name;
    }
}
