package org.anup.tallink.service;

import jakarta.validation.Valid;
import org.anup.tallink.dto.AvailableConferenceDto;
import org.anup.tallink.dto.ConferenceDto;
import org.anup.tallink.dto.ConferenceRoomDto;
import org.anup.tallink.dto.UpdateConferenceDto;
import org.anup.tallink.entity.Conference;
import org.anup.tallink.entity.ConferenceRoom;
import org.anup.tallink.entity.ConferenceStatus;
import org.anup.tallink.entity.RoomStatus;
import org.anup.tallink.exceptions.ConferenceNotFoundException;
import org.anup.tallink.exceptions.InvalidRoomStatusException;
import org.anup.tallink.exceptions.ResourceNotFoundException;
import org.anup.tallink.repository.ConferenceRepository;
import org.anup.tallink.repository.ConferenceRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConferenceService {

    @Autowired
    private ConferenceRepository conferenceRepository;

    @Autowired
    private ConferenceRoomRepository conferenceRoomRepository;

    public ConferenceDto createConference(ConferenceDto conferenceDto) throws InvalidRoomStatusException {
        ConferenceRoom room = conferenceRoomRepository.findById(conferenceDto.getConferenceRoomId())
                .orElseThrow(() -> new ResourceNotFoundException("Conference room not found"));

        if (!room.getStatus().equals(RoomStatus.AVAILABLE)) {
            throw new InvalidRoomStatusException("Room is not available for booking");
        }

        Conference conference = new Conference();
        conference.setConferenceRoom(room);
        conference.setDateTime(conferenceDto.getDateTime());
        conference.setMaxParticipants(conferenceDto.getMaxParticipants());
        conference.setStatus(ConferenceStatus.SCHEDULED);
        conference.getConferenceRoom().setStatus(RoomStatus.BOOKED);
        Conference savedConference = conferenceRepository.save(conference);

        return convertToDto(savedConference);
    }

    private ConferenceDto convertToDto(Conference conference) {
        ConferenceDto dto = new ConferenceDto();
        dto.setConferenceRoomId(conference.getConferenceRoom().getId());
        dto.setDateTime(conference.getDateTime());
        dto.setMaxParticipants(conference.getMaxParticipants());
        return dto;
    }

    public ConferenceDto cancelConference(ConferenceDto conferenceDto) {
        return null;
    }

    public List<AvailableConferenceDto> findAvailableConferences(AvailableConferenceDto availableConferenceDto) {
        return null;
    }

    public ConferenceRoomDto updateConference(Long conferenceId, UpdateConferenceDto conferenceDto) {

        Optional<Conference> conference = conferenceRepository.findByIdAndStatus(conferenceId, ConferenceStatus.SCHEDULED);
        if (conference.isEmpty()) {
            throw new ConferenceNotFoundException("Conference not found");
        }

        if(conferenceDto.getDateTime() != null){

            conference.get().setDateTime(conferenceDto.getDateTime());
            Conference savedConference = conferenceRepository.save(conference.get());

            return convertRoomToDto(savedConference.getConferenceRoom());
        }
        else if (conferenceDto.getConferenceRoomId() != null && conferenceDto.getConferenceRoomId() >0) {
            Optional<ConferenceRoom> room = conferenceRoomRepository.findByIdAndStatus(conferenceDto.getConferenceRoomId(),RoomStatus.AVAILABLE);
            if (room.isEmpty()) {
                throw new ResourceNotFoundException("Conference room not found");
            }

            conference.get().setConferenceRoom(room.get());
            room.get().setStatus(RoomStatus.BOOKED);
            Conference savedConference = conferenceRepository.save(conference.get());
            return convertRoomToDto(savedConference.getConferenceRoom());
        }
        else {
            throw new IllegalArgumentException("Either date or conference room id should be provided");
        }
    }

    private ConferenceRoomDto convertRoomToDto(ConferenceRoom room) {
        ConferenceRoomDto dto = new ConferenceRoomDto();
        dto.setName(room.getName());
        dto.setLocation(room.getLocation());
        dto.setStatus(room.getStatus());
        dto.setMaxCapacity(room.getMaxCapacity());
        return dto;
    }
}
