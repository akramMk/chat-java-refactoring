package fr.univ_lyon1.info.m1.elizagpt.model.util;

import java.util.List;
import fr.univ_lyon1.info.m1.elizagpt.model.classes.Verb;

/**
 * Utility class for text processing operations.
 * Provides methods for normalizing text and converting 1st-person sentences to plural 2nd person.
 */
public final class TextProcess {
    private TextProcess() {
    }

    /**
     * Normlize the text: remove extra spaces, add a final dot if missing.
     * 
     * @param text
     * @return normalized text.
     */
    public static String normalize(final String text) {
        return text.replaceAll("\\s+", " ")
                .replaceAll("^\\s+", "")
                .replaceAll("\\s+$", "")
                .replaceAll("[^\\.!?:]$", "$0.");
    }
    /**
     * Turn a 1st-person sentence (Je ...) into a plural 2nd person (Vous ...).
     * The result is not capitalized to allow forming a new sentence.
     *
     *
     * @param text
     * @return The 2nd-person sentence.
     */
    public static String firstToSecondPerson(final List<Verb> verbList, final String text) {
        String processedText = text
                .replace("mon ", "votre ")
                .replace("ma ", "votre ")
                .replace("mes ", "vos ")
                .replace("moi", "vous");

        for (Verb v : verbList) {
            processedText = processedText.replaceAll(
                    "[Jj]e " + v.getFirstSingular(),
                    "vous " + v.getSecondPlural());
        }
        
        return processedText;
    }
}
