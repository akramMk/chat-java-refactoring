package fr.univ_lyon1.info.m1.elizagpt.model.classes;

/**
 * The {@code Verb} class represents a verb with conjugation information 
 * for the first singular and second plural forms.
 * This class provides methods to retrieve the conjugation details.
 */
public class Verb {

    /**
     * The conjugation for the first person singular form of the verb.
     */
    private final String firstSingular;

    /**
     * The conjugation for the second person plural form of the verb.
     */
    private final String secondPlural;

    /**
     * Constructs a new {@code Verb} instance with the specified conjugation details.
     *
     * @param firstSingular The conjugation for the first person singular form.
     * @param secondPlural  The conjugation for the second person plural form.
     */
    public Verb(final String firstSingular, final String secondPlural) {
        this.firstSingular = firstSingular;
        this.secondPlural = secondPlural;
    }

    /**
     * Gets the conjugation for the first person singular form of the verb.
     *
     * @return The conjugation for the first person singular form.
     */
    public String getFirstSingular() {
        return firstSingular;
    }

    /**
     * Gets the conjugation for the second person plural form of the verb.
     *
     * @return The conjugation for the second person plural form.
     */
    public String getSecondPlural() {
        return secondPlural;
    }
}
