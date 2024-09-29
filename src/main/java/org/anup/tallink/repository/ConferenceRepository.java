package org.anup.tallink.repository;

import org.anup.tallink.dto.AvailableConferenceDto;
import org.anup.tallink.entity.Conference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Long> {

//    List<AvailableConferenceDto> findAvailableConferences(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
