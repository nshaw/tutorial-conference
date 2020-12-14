package com.nps.jhsimple.web.rest;

import com.nps.jhsimple.domain.Conference;
import com.nps.jhsimple.repository.ConferenceRepository;
import com.nps.jhsimple.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.nps.jhsimple.domain.Conference}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ConferenceResource {

    private final Logger log = LoggerFactory.getLogger(ConferenceResource.class);

    private static final String ENTITY_NAME = "conferenceConference";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ConferenceRepository conferenceRepository;

    public ConferenceResource(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    /**
     * {@code POST  /conferences} : Create a new conference.
     *
     * @param conference the conference to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new conference, or with status {@code 400 (Bad Request)} if the conference has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/conferences")
    public ResponseEntity<Conference> createConference(@RequestBody Conference conference) throws URISyntaxException {
        log.debug("REST request to save Conference : {}", conference);
        if (conference.getId() != null) {
            throw new BadRequestAlertException("A new conference cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Conference result = conferenceRepository.save(conference);
        return ResponseEntity.created(new URI("/api/conferences/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /conferences} : Updates an existing conference.
     *
     * @param conference the conference to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated conference,
     * or with status {@code 400 (Bad Request)} if the conference is not valid,
     * or with status {@code 500 (Internal Server Error)} if the conference couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/conferences")
    public ResponseEntity<Conference> updateConference(@RequestBody Conference conference) throws URISyntaxException {
        log.debug("REST request to update Conference : {}", conference);
        if (conference.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Conference result = conferenceRepository.save(conference);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, conference.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /conferences} : get all the conferences.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of conferences in body.
     */
    @GetMapping("/conferences")
    public List<Conference> getAllConferences() {
        log.debug("REST request to get all Conferences");
        return conferenceRepository.findAll();
    }

    /**
     * {@code GET  /conferences/:id} : get the "id" conference.
     *
     * @param id the id of the conference to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the conference, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/conferences/{id}")
    public ResponseEntity<Conference> getConference(@PathVariable Long id) {
        log.debug("REST request to get Conference : {}", id);
        Optional<Conference> conference = conferenceRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(conference);
    }

    /**
     * {@code DELETE  /conferences/:id} : delete the "id" conference.
     *
     * @param id the id of the conference to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/conferences/{id}")
    public ResponseEntity<Void> deleteConference(@PathVariable Long id) {
        log.debug("REST request to delete Conference : {}", id);

        conferenceRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
