package com.udea.fsi.web.rest;

import com.udea.fsi.domain.Tarea;
import com.udea.fsi.repository.TareaRepository;
import com.udea.fsi.service.TareaService;
import com.udea.fsi.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.udea.fsi.domain.Tarea}.
 */
@RestController
@RequestMapping("/api/tareas")
public class TareaResource {

    private static final Logger LOG = LoggerFactory.getLogger(TareaResource.class);

    private static final String ENTITY_NAME = "tarea";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TareaService tareaService;

    private final TareaRepository tareaRepository;

    public TareaResource(TareaService tareaService, TareaRepository tareaRepository) {
        this.tareaService = tareaService;
        this.tareaRepository = tareaRepository;
    }

    /**
     * {@code POST  /tareas} : Create a new tarea.
     *
     * @param tarea the tarea to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tarea, or with status {@code 400 (Bad Request)} if the tarea has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Tarea> createTarea(@RequestBody Tarea tarea) throws URISyntaxException {
        LOG.debug("REST request to save Tarea : {}", tarea);
        if (tarea.getId() != null) {
            throw new BadRequestAlertException("A new tarea cannot already have an ID", ENTITY_NAME, "idexists");
        }
        tarea = tareaService.save(tarea);
        return ResponseEntity.created(new URI("/api/tareas/" + tarea.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, tarea.getId().toString()))
            .body(tarea);
    }

    /**
     * {@code PUT  /tareas/:id} : Updates an existing tarea.
     *
     * @param id the id of the tarea to save.
     * @param tarea the tarea to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tarea,
     * or with status {@code 400 (Bad Request)} if the tarea is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tarea couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Tarea> updateTarea(@PathVariable(value = "id", required = false) final Long id, @RequestBody Tarea tarea)
        throws URISyntaxException {
        LOG.debug("REST request to update Tarea : {}, {}", id, tarea);
        if (tarea.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tarea.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tareaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        tarea = tareaService.update(tarea);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tarea.getId().toString()))
            .body(tarea);
    }

    /**
     * {@code PATCH  /tareas/:id} : Partial updates given fields of an existing tarea, field will ignore if it is null
     *
     * @param id the id of the tarea to save.
     * @param tarea the tarea to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tarea,
     * or with status {@code 400 (Bad Request)} if the tarea is not valid,
     * or with status {@code 404 (Not Found)} if the tarea is not found,
     * or with status {@code 500 (Internal Server Error)} if the tarea couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Tarea> partialUpdateTarea(@PathVariable(value = "id", required = false) final Long id, @RequestBody Tarea tarea)
        throws URISyntaxException {
        LOG.debug("REST request to partial update Tarea partially : {}, {}", id, tarea);
        if (tarea.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tarea.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tareaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Tarea> result = tareaService.partialUpdate(tarea);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tarea.getId().toString())
        );
    }

    /**
     * {@code GET  /tareas} : get all the tareas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tareas in body.
     */
    @GetMapping("")
    public List<Tarea> getAllTareas() {
        LOG.debug("REST request to get all Tareas");
        return tareaService.findAll();
    }

    /**
     * {@code GET  /tareas/:id} : get the "id" tarea.
     *
     * @param id the id of the tarea to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tarea, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Tarea> getTarea(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Tarea : {}", id);
        Optional<Tarea> tarea = tareaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tarea);
    }

    /**
     * {@code DELETE  /tareas/:id} : delete the "id" tarea.
     *
     * @param id the id of the tarea to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarea(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Tarea : {}", id);
        tareaService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
