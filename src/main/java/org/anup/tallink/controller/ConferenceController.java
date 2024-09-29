package org.anup.tallink.controller;

import jakarta.validation.Valid;
import org.anup.tallink.dto.ConferenceDto;
import org.anup.tallink.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/conferences")
public class ConferenceController {

    @Autowired
    private ConferenceService conferenceService;

    @PostMapping
    public ResponseEntity<ConferenceDto> createConference(@Valid @RequestBody ConferenceDto conferenceDto) {
        ConferenceDto savedConference = conferenceService.createConference(conferenceDto);
        return new ResponseEntity<>(savedConference, HttpStatus.CREATED);
    }
}
