package org.anup.tallink.service;

import lombok.extern.slf4j.Slf4j;
import org.anup.tallink.dto.ConferenceRoomDto;
import org.anup.tallink.entity.ConferenceRoom;
import org.anup.tallink.exceptions.RoomAlreadyExistsException;
import org.anup.tallink.repository.ConferenceRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class ConferenceRoomService {

    @Autowired
    private ConferenceRoomRepository conferenceRoomRepository;

    public ConferenceRoomDto createConferenceRoom(ConferenceRoomDto conferenceRoomDto) {
        ConferenceRoom room = new ConferenceRoom();
        room.setName(conferenceRoomDto.getName());
        room.setLocation(conferenceRoomDto.getLocation());
        room.setStatus(conferenceRoomDto.getStatus());
        room.setMaxCapacity(conferenceRoomDto.getMaxCapacity());

        Optional<ConferenceRoom> conferenceRoom = conferenceRoomRepository.findByName(room.getName());
        if (conferenceRoom.isPresent()) {
            log.error("Conference room :{} already exists", room.getName());
            throw new RoomAlreadyExistsException("Conference room already exists");
        }
        ConferenceRoom savedRoom = conferenceRoomRepository.save(room);
        return convertToDto(savedRoom);
    }

    private ConferenceRoomDto convertToDto(ConferenceRoom room) {
        ConferenceRoomDto dto = new ConferenceRoomDto();
        dto.setName(room.getName());
        dto.setLocation(room.getLocation());
        dto.setStatus(room.getStatus());
        dto.setMaxCapacity(room.getMaxCapacity());
        return dto;
    }
}
