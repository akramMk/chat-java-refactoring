package fr.univ_lyon1.info.m1.elizagpt.interfaces;

import java.util.List;

/**
 * Generic interface for Data Access Objects.
 *
 * @param <T> Type of the entity.
 * 
 *  @param <T2> Type of the second entity.
 */
public interface DAO<T, T2> {

    /**
     * Retrieves a copy of all entities stored in the DAO.
     *
     * @return A list containing all entities.
     */
    List<T> getAllMessages();

    /**
     * Adds a new entity to the DAO.
     *
     * @param entity The entity object to be added.
     */
    void addMessage(T entity);

    /**
     * Retrieves an entity by its unique identifier.
     *
     * @param id The unique identifier of the entity.
     * @return The entity object with the specified ID, or null if not found.
     */
    T getMessageById(T2 id);

    /**
     * Deletes an entity by its unique identifier.
     *
     * @param id The unique identifier of the entity to be deleted.
     */
    void deleteMessageById(T2 id);
}
