package com.slim.javaee.core.repository;

/**
 * Classe Dao générique exposant les méthodes classiques du CRUD.
 *
 * @param <T> Entité manipulée
 * @param <P> Clé primaire de l'entité
 */
public interface IJpaRepository<T, P extends Serializable> {

    /**
     * Supprime l'entité t.
     *
     * @param id Id de l'entité à supprimer
     */
    void deleteById(P id);

    /**
     * Persiste une nouvelle entité.
     * @param entity L'entité à persister.
     */
    void create(T entity);


    /**
     * Persiste une liste de nouvelles entités.
     * @param entites La liste des entités à persister.
     */
    void create(Set<T> entites);

    /**
     * Supprime toutes les entités.
     * @return Nombre d'entités supprimées
     */
    int deleteAll();

    /**
     * Retourne un objet depuis la base de données à partir de son id.
     *
     * @param id identifiant de l'objet
     * @return Entité à partir de son id
     */
    Optional<T> findById(P id);

    /**
     * Supprime l'objet t.
     *
     * @param entity Objet à supprimer
     * @return Objet mis à jour
     */
    T update(T entity);

    /**
     * Retourne la liste des objets enregistrés en base.
     *
     * @return Liste des objets.
     */
    Set<T> listAll(Integer startPosition, Integer maxResult);

    /**
     * Retourne la liste des objets enregistrés en base.
     * @return Liste de tous les objets de la base.
     */
    Set<T> listAll();
}
