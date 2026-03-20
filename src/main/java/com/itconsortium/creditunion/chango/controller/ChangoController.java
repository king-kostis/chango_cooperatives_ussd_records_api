package com.itconsortium.creditunion.chango.controller;

import com.itconsortium.creditunion.chango.dto.EmailRequestDto;
import com.itconsortium.creditunion.chango.projections.MemberAccountTransactionSummaryDto;
import com.itconsortium.creditunion.chango.projections.SubscriptionSummaryDto;
import com.itconsortium.creditunion.chango.services.EmailService;
import com.itconsortium.creditunion.chango.services.MemberAccountTransactionService;
import com.itconsortium.creditunion.chango.services.SubscriptionService;
import com.mailjet.client.MailjetResponse;
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
    private MemberAccountTransactionService memberAccountTransactionService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/miniStatement/{groupId}/{msisdn}")
    public ResponseEntity<List<MemberAccountTransactionSummaryDto>> getLast5Statements(@PathVariable Long groupId, @PathVariable String msisdn){
        return ResponseEntity.ok(memberAccountTransactionService.getLast5Statements(groupId, msisdn));
    }

    @PostMapping("/sendEmail")
    public ResponseEntity<MailjetResponse> sendStatementsEmail(@RequestBody EmailRequestDto emailRequestDto){
        String emailAddress = emailRequestDto.getEmailAddress();
        return ResponseEntity.ok(emailService.sendEmail(emailAddress));
    }

    @GetMapping("/subscriptionById/{id}")
    public ResponseEntity<SubscriptionSummaryDto> getSubscriptionById(@PathVariable Long id){
        return ResponseEntity.ok(subscriptionService.getSubscriptionDetailsById(id));
    }
    @GetMapping("/subscriptionByMsisdn/{msisdn}")
    public ResponseEntity<SubscriptionSummaryDto> getSubcriptionByMsisdn(@PathVariable String msisdn){
        return ResponseEntity.ok(subscriptionService.getSubscriptionDetailsByMsisdn(msisdn));
    }
}
