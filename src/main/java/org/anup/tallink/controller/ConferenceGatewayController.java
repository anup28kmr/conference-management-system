package org.anup.tallink.controller;

import jakarta.validation.Valid;
import org.anup.tallink.dto.AvailableConferenceDto;
import org.anup.tallink.dto.FeedbackDto;
import org.anup.tallink.dto.RegistrationRequestDto;
import org.anup.tallink.dto.RegistrationResponseDto;
import org.anup.tallink.service.ConferenceGatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/conferences")
public class ConferenceGatewayController {

    @Autowired
    private ConferenceGatewayService conferenceGatewayService;

    // Find available conferences within a date-time range
    @GetMapping("/available")
    public List<AvailableConferenceDto> findAvailableConferences(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDateTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDateTime) {
        return conferenceGatewayService.findAvailableConferences(startDateTime, endDateTime);
    }

    // Self-registration for a conference
    @PostMapping("/{conferenceId}/register")
    public RegistrationResponseDto selfRegister(@PathVariable Long conferenceId, @RequestBody @Valid RegistrationRequestDto registrationRequest) {
        return conferenceGatewayService.selfRegister(conferenceId, registrationRequest);
    }

    // Cancel registration using participant code
    @DeleteMapping("/cancel")
    public ResponseEntity<String> cancelRegistration(@RequestParam String participantCode) {
        conferenceGatewayService.cancelRegistration(participantCode);
        return ResponseEntity.ok("Registration canceled successfully.");
    }

    // Send feedback for a conference using participant code
    @PostMapping("/feedback")
    public ResponseEntity<String> sendFeedback(@RequestParam String participantCode, @RequestBody @Valid FeedbackDto feedback) {
        conferenceGatewayService.sendFeedback(participantCode, feedback);
        return ResponseEntity.ok("Feedback submitted successfully.");
    }
}
