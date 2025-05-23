package com.udea.fsi.web.rest;

import com.udea.fsi.domain.Contrato;
import com.udea.fsi.repository.ContratoRepository;
import com.udea.fsi.service.ContratoService;
import com.udea.fsi.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.udea.fsi.domain.Contrato}.
 */
@RestController
@RequestMapping("/api/contratoes")
public class ContratoResource {

    private static final Logger LOG = LoggerFactory.getLogger(ContratoResource.class);

    private static final String ENTITY_NAME = "contrato";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ContratoService contratoService;

    private final ContratoRepository contratoRepository;

    public ContratoResource(ContratoService contratoService, ContratoRepository contratoRepository) {
        this.contratoService = contratoService;
        this.contratoRepository = contratoRepository;
    }

    /**
     * {@code POST  /contratoes} : Create a new contrato.
     *
     * @param contrato the contrato to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contrato, or with status {@code 400 (Bad Request)} if the contrato has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Contrato> createContrato(@RequestBody Contrato contrato) throws URISyntaxException {
        LOG.debug("REST request to save Contrato : {}", contrato);
        if (contrato.getId() != null) {
            throw new BadRequestAlertException("A new contrato cannot already have an ID", ENTITY_NAME, "idexists");
        }
        contrato = contratoService.save(contrato);
        return ResponseEntity.created(new URI("/api/contratoes/" + contrato.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, contrato.getId().toString()))
            .body(contrato);
    }

    /**
     * {@code PUT  /contratoes/:id} : Updates an existing contrato.
     *
     * @param id the id of the contrato to save.
     * @param contrato the contrato to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contrato,
     * or with status {@code 400 (Bad Request)} if the contrato is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contrato couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Contrato> updateContrato(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Contrato contrato
    ) throws URISyntaxException {
        LOG.debug("REST request to update Contrato : {}, {}", id, contrato);
        if (contrato.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contrato.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contratoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        contrato = contratoService.update(contrato);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contrato.getId().toString()))
            .body(contrato);
    }

    /**
     * {@code PATCH  /contratoes/:id} : Partial updates given fields of an existing contrato, field will ignore if it is null
     *
     * @param id the id of the contrato to save.
     * @param contrato the contrato to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contrato,
     * or with status {@code 400 (Bad Request)} if the contrato is not valid,
     * or with status {@code 404 (Not Found)} if the contrato is not found,
     * or with status {@code 500 (Internal Server Error)} if the contrato couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Contrato> partialUpdateContrato(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Contrato contrato
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Contrato partially : {}, {}", id, contrato);
        if (contrato.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contrato.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contratoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Contrato> result = contratoService.partialUpdate(contrato);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contrato.getId().toString())
        );
    }

    /**
     * {@code GET  /contratoes} : get all the contratoes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contratoes in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Contrato>> getAllContratoes(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        LOG.debug("REST request to get a page of Contratoes");
        Page<Contrato> page = contratoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /contratoes/:id} : get the "id" contrato.
     *
     * @param id the id of the contrato to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contrato, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Contrato> getContrato(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Contrato : {}", id);
        Optional<Contrato> contrato = contratoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(contrato);
    }

    /**
     * {@code DELETE  /contratoes/:id} : delete the "id" contrato.
     *
     * @param id the id of the contrato to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContrato(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Contrato : {}", id);
        contratoService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
