package fr.univ_lyon1.info.m1.elizagpt.model.searchStrategy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import fr.univ_lyon1.info.m1.elizagpt.interfaces.Search;
import fr.univ_lyon1.info.m1.elizagpt.model.classes.Message;

/**
 * Implementation of the Search interface using regular expressions.
 */
public final class RegexSearchStrategy implements Search {
    
    private static Search instance;

    // Private constructor to prevent external instantiation
    private RegexSearchStrategy() {
    }

    /**
     * Method to get the singleton instance.
     */
    public static Search getInstance() {
        if (instance == null) {
            instance = new RegexSearchStrategy();
        }
        return instance;
    }

    /**
     * Executes a search using a regular expression pattern on the content of a message.
     *
     * @param message    The message to search within.
     * @param searchText The text to search for using the regular expression.
     * @return True if the regular expression pattern is found in the message content,
     * otherwise false.
     */
    @Override
    public Boolean execute(final Message message, final String searchText) {
        // Compile the regular expression pattern with case insensitivity
        Pattern regexPattern = Pattern.compile(".*" + searchText + ".*", Pattern.CASE_INSENSITIVE);
        
        // Create a matcher for the message content
        Matcher matcher = regexPattern.matcher(message.getContent());
        
        // Return true if the pattern is found in the content
        return matcher.find();
    }

    /**
     * Returns a string representation of this regular expression search strategy.
     *
     * @return The string "Expression régulière".
     */
    @Override
    public String toString() {
        return "Expression régulière";
    }
}
