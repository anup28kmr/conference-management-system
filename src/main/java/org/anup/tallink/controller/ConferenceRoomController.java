package org.anup.tallink.controller;

import jakarta.validation.Valid;
import org.anup.tallink.dto.ConferenceRoomDto;
import org.anup.tallink.service.ConferenceRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/conference-rooms")
public class ConferenceRoomController {

    @Autowired
    private ConferenceRoomService conferenceRoomService;

    @PostMapping
    public ResponseEntity<ConferenceRoomDto> createConferenceRoom(@Valid @RequestBody ConferenceRoomDto conferenceRoomDto) {
        ConferenceRoomDto savedRoom = conferenceRoomService.createConferenceRoom(conferenceRoomDto);
        return new ResponseEntity<>(savedRoom, HttpStatus.CREATED);
    }
}
