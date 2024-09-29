package org.anup.tallink.repository;

import org.anup.tallink.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    @Query("SELECT COUNT(p) FROM Participant p WHERE p.conference.id = :conferenceId")
    long countParticipantsByConferenceId(@Param("conferenceId") Long conferenceId);

    Optional<Participant> findByUniqueCode(String uniqueCode);
}
