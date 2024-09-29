package org.anup.tallink.repository;

import org.anup.tallink.entity.ConferenceRoom;
import org.anup.tallink.entity.RoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConferenceRoomRepository extends JpaRepository<ConferenceRoom, Long> {

    Optional<ConferenceRoom> findByName(String name);

    Optional<ConferenceRoom> findByIdAndStatus(Long conferenceRoomId, RoomStatus roomStatus);
}
