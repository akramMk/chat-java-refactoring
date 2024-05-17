package fr.univ_lyon1.info.m1.elizagpt.model.searchStrategy;

import fr.univ_lyon1.info.m1.elizagpt.interfaces.Search;
import fr.univ_lyon1.info.m1.elizagpt.model.classes.Message;

/**
 * Implementation of the Search interface using regular expressions.
 */
public final class SubstringSearchStrategy implements Search {

    private static Search instance;

    // Private constructor to prevent external instantiation
    private SubstringSearchStrategy() {
    }

    /**
     * Method to get the singleton instance.
     */    
    public static Search getInstance() {
        if (instance == null) {
            instance = new SubstringSearchStrategy();
        }
        return instance;
    }

    /**
     * Executes a search using a regular expression pattern on the content of a message.
     *
     * @param message    The message to search within.
     * @param searchText The text to search for using the Substring expression.
     * @return True if the substring expression pattern is found in the message content,
     * otherwise false.
     */
    @Override
    public Boolean execute(final Message message, final String searchText) {
        return message.getContent().contains(searchText);
    }

    /**
     * Returns a string representation of this regular expression search strategy.
     *
     * @return The string "Sous-chaîne".
     */
    @Override
    public String toString() {
        return "Sous-chaîne";
    }
}
