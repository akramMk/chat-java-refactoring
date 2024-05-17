package fr.univ_lyon1.info.m1.elizagpt.model.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import fr.univ_lyon1.info.m1.elizagpt.interfaces.DAO;
import fr.univ_lyon1.info.m1.elizagpt.model.classes.Verb;

/**
 * The VerbDAO class is responsible for handling data access operations for Verb objects.
 * It reads verb data from a CSV file and provides methods to retrieve the list of verbs.
 */
public class VerbDAO implements DAO<Verb, Verb> {

    /**
     * List containing Verb objects retrieved from the CSV file.
     */
    private List<Verb> verbList;

    /**
     * Constructs a new VerbDAO object.
     * Reads verb data from a CSV file and initializes the verbList.
     */
    public VerbDAO() {
        try {
            verbList = 
            Files.lines(
                Paths.get("src/main/java/fr/univ_lyon1/info/m1/elizagpt/model/ressource/verb.csv"))
                    .skip(1) // Skip the header
                    .map(line -> line.split(",", -1)) // Include empty values
                    .filter(parts -> parts.length >= 10 
                    && (!parts[5].trim().isEmpty() || !parts[9].trim().isEmpty()))
                    .map(parts -> new Verb(parts[5], parts[9]))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the list of all Verb objects.
     *
     * @return The list of all Verb objects.
     */
    @Override
    public List<Verb> getAllMessages() {
        return verbList;
    }

     /**
     * Add a erb to list.
     *
     */
    @Override
    public void addMessage(final Verb entity) {
        verbList.add(entity);
    }

     /**
     * Ger verb objects.
     *
     * @return Verb.
     */
    @Override
    public Verb getMessageById(final Verb id) {
            return verbList.get(verbList.indexOf(id));
    }

    /**
     * 
     * delete a Verb.
     */
    @Override
    public void deleteMessageById(final Verb id) {
        verbList.remove(id);
    }
}
