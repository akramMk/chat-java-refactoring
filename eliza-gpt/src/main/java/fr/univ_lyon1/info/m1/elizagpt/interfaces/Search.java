package fr.univ_lyon1.info.m1.elizagpt.interfaces;

import fr.univ_lyon1.info.m1.elizagpt.model.classes.Message;

/**
 * Interface for performing search operations based on a given message and search text.
 */
public interface Search {

    /**
     * Executes a search operation based on the provided message and search text.
     *
     * @param message The Message object to be used in the search operation.
     * @param searchText The text to be used as a search query.
     * @return Boolean indicating the success or failure of the search operation.
     */
    Boolean execute(Message message, String searchText);

    /**
     * Returns a string representation of the Search object.
     *
     * @return A string representation of the Search object.
     */
    String toString();
}
