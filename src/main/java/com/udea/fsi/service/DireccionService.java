package com.udea.fsi.service;

import com.udea.fsi.domain.Direccion;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.udea.fsi.domain.Direccion}.
 */
public interface DireccionService {
    /**
     * Save a direccion.
     *
     * @param direccion the entity to save.
     * @return the persisted entity.
     */
    Direccion save(Direccion direccion);

    /**
     * Updates a direccion.
     *
     * @param direccion the entity to update.
     * @return the persisted entity.
     */
    Direccion update(Direccion direccion);

    /**
     * Partially updates a direccion.
     *
     * @param direccion the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Direccion> partialUpdate(Direccion direccion);

    /**
     * Get all the direccions.
     *
     * @return the list of entities.
     */
    List<Direccion> findAll();

    /**
     * Get all the Direccion where Departamento is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Direccion> findAllWhereDepartamentoIsNull();

    /**
     * Get the "id" direccion.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Direccion> findOne(Long id);

    /**
     * Delete the "id" direccion.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
