package org.anup.tallink.repository;

import org.anup.tallink.entity.Conference;
import org.anup.tallink.entity.ConferenceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Long> {
    Optional<Conference> findByIdAndStatus(Long id, ConferenceStatus scheduled);

    Optional<Conference> findByConferenceRoomId(Long conferenceRoomId);

//    List<AvailableConferenceDto> findAvailableConferences(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
