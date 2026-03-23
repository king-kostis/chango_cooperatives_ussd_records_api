package com.itconsortium.creditunion.chango.controller;

import com.itconsortium.creditunion.chango.dto.EmailRequestDto;
import com.itconsortium.creditunion.chango.dto.EmailResponseDto;
import com.itconsortium.creditunion.chango.dto.RecurringDetailsDto;
import com.itconsortium.creditunion.chango.projections.MemberTransactionSummaryDto;
import com.itconsortium.creditunion.chango.projections.RecurringDebitSummary;
import com.itconsortium.creditunion.chango.services.EmailService;
import com.itconsortium.creditunion.chango.services.MemberTransactionService;
import com.itconsortium.creditunion.chango.services.RecurringDebitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/chango/myRecords")
public class ChangoController {

    @Autowired
    private MemberTransactionService memberTransactionService;

    @Autowired
    private RecurringDebitService recurringDebitService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/miniStatement/{groupId}/{msisdn}")
    public ResponseEntity<List<MemberTransactionSummaryDto>> getLast5Statements(@PathVariable Long groupId, @PathVariable String msisdn){
        return ResponseEntity.ok(memberTransactionService.getLast5Statements(groupId, msisdn));
    }

    @PostMapping("/sendEmail")
    public ResponseEntity<EmailResponseDto> sendStatementsEmail(@RequestBody EmailRequestDto emailRequestDto){
        String emailAddress = emailRequestDto.getEmailAddress();
        Long groupId = emailRequestDto.getGroupId();
        return ResponseEntity.ok(emailService.sendEmail(emailAddress, groupId));
    }

    @GetMapping("/subscriptionById/{id}")
    public ResponseEntity<RecurringDetailsDto> getSubscriptionById(@PathVariable Long id){
        return ResponseEntity.ok(recurringDebitService.getRecurringDetailsById(id));
    }
    @GetMapping("/subscriptionByMsisdn/{msisdn}")
    public ResponseEntity<RecurringDetailsDto> getSubscriptionByMsisdn(@PathVariable String msisdn){
        return ResponseEntity.ok(recurringDebitService.getRecurringDetailsByMsisdn(msisdn));
    }
}
