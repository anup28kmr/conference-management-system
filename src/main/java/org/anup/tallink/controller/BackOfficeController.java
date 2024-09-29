package org.anup.tallink.controller;

import jakarta.validation.Valid;
import org.anup.tallink.dto.AvailableConferenceDto;
import org.anup.tallink.dto.ConferenceDto;
import org.anup.tallink.dto.ConferenceRoomDto;
import org.anup.tallink.dto.UpdateConferenceDto;
import org.anup.tallink.service.ConferenceRoomService;
import org.anup.tallink.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/conferences")
public class BackOfficeController {

    @Autowired
    private ConferenceRoomService conferenceRoomService;
    @Autowired
    private ConferenceService conferenceService;

    @PostMapping("/rooms")
    public ResponseEntity<ConferenceRoomDto> createConferenceRoom(@Valid @RequestBody ConferenceRoomDto conferenceRoomDto) {
        ConferenceRoomDto savedRoom = conferenceRoomService.createConferenceRoom(conferenceRoomDto);
        return new ResponseEntity<>(savedRoom, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<ConferenceDto> createConference(@Valid @RequestBody ConferenceDto conferenceDto) {
        ConferenceDto savedConference = conferenceService.createConference(conferenceDto);
        return new ResponseEntity<>(savedConference, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<ConferenceDto> cancelConference(@Valid @RequestBody ConferenceDto conferenceDto) {
        ConferenceDto updatedConference = conferenceService.cancelConference(conferenceDto);
        return new ResponseEntity<>(updatedConference, HttpStatus.OK);
    }

    @GetMapping("/available")
    public List<AvailableConferenceDto> findAvailableConferences(@RequestBody AvailableConferenceDto availableConferenceDto) {
        return conferenceService.findAvailableConferences(availableConferenceDto);
    }

    @PutMapping("/{conferenceId}")
    public ResponseEntity<ConferenceRoomDto> updateConference(@PathVariable Long conferenceId, @Valid @RequestBody UpdateConferenceDto conferenceDto) {
        ConferenceRoomDto savedRoom = conferenceService.updateConference(conferenceId, conferenceDto);
        return new ResponseEntity<>(savedRoom, HttpStatus.CREATED);
    }
}
