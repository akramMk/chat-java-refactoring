package fr.univ_lyon1.info.m1.elizagpt.model.searchStrategy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import fr.univ_lyon1.info.m1.elizagpt.interfaces.Search;
import fr.univ_lyon1.info.m1.elizagpt.model.classes.Message;

/**
 * Implementation of the Search interface using regular expressions.
 */
public final class WordCompleteSearchStrategy implements Search {

    private static Search instance;

    // Private constructor to prevent external instantiation
    private WordCompleteSearchStrategy() {
    }

    /**
     * Method to get the singleton instance.
     */
    public static Search getInstance() {
        if (instance == null) {
            instance = new WordCompleteSearchStrategy();
        }
        return instance;
    }

    /**
     * Executes a search using a regular expression pattern on the content of a message.
     *
     * @param message    The message to search within.
     * @param searchText The text to search for using the Complete word.
     * @return True if the complete word pattern is found in the message content, otherwise false.
     */
    @Override
    public Boolean execute(final Message message, final String searchText) {
        // Use \b to match whole words in the regular expression
        Pattern pattern = Pattern.compile("\\b" + searchText + "\\b", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(message.getContent());
        return matcher.find();
    }

    /**
     * Returns a string representation of this regular expression search strategy.
     *
     * @return The string "Mot complet".
     */
    @Override
    public String toString() {
        return "Mot complet";
    }
}
