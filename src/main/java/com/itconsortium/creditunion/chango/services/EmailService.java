package com.itconsortium.creditunion.chango.services;

import com.itconsortium.creditunion.chango.dto.EmailResponseDto;
import com.itconsortium.creditunion.chango.exceptions.EmailSendingException;
import com.itconsortium.creditunion.chango.exceptions.NoTransactionsAvailableException;
import com.itconsortium.creditunion.chango.model.Member;
import com.itconsortium.creditunion.chango.projections.MemberTransactionSummaryDto;
import com.itconsortium.creditunion.chango.repository.MemberTransactionRepository;
import com.itconsortium.creditunion.chango.repository.MemberRepository;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import lombok.extern.slf4j.Slf4j;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.ClientOptions;
import com.mailjet.client.resource.Emailv31;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class EmailService {
    @Autowired
    private RecurringDebitService recurringDebitService;

    @Autowired
    private MemberTransactionRepository memberTransactionRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Value("${mailjet.apikey.public}")
    private String API_KEY_PUBLIC;

    @Value("${mailjet.apikey.private}")
    private String API_KEY_PRIVATE;



    private List<MemberTransactionSummaryDto> getLast90DaysStatements(String msisdn, Long groupId) {
        log.info("Getting statements of last 90 days");
        //Return a date 90 days from current days
        LocalDate boundaryDate = LocalDate.now().minusDays(90);

        //Return the list of transactions after the boundary date
        List<MemberTransactionSummaryDto> transactions = memberTransactionRepository.findByTransactionDate(boundaryDate, msisdn, groupId);

        if (transactions.isEmpty()) {
            throw new NoTransactionsAvailableException("There are no transaction records");
        } else {
            return transactions;
        }
    }

    //Method to generate email message
    private String getEmailMessage(List<MemberTransactionSummaryDto> memberTransactionSummaryDtos) {
        String emailMessage = "";

        for (var transaction : memberTransactionSummaryDtos) {
            emailMessage += "On " + transaction.getCreated() +
                    "\nTransaction Type: " + transaction.getTransactionType() +
                    "\nAmount Transacted: " + transaction.getMemberCurrency() + " " + transaction.getAmount() + "\n\n";
        }
        return emailMessage;
    }

    public EmailResponseDto sendEmail(String emailAddress, Long groupId) {
        Member member = memberRepository.findMemberByEmailAddress(emailAddress);

        String msisdn = member.getMsisdn();
        String emailMessage = getEmailMessage(getLast90DaysStatements(msisdn, groupId));
        String memberName = member.getFirstName();

        try {
            MailjetClient client = new MailjetClient(API_KEY_PUBLIC, API_KEY_PRIVATE, new ClientOptions("v3.1"));
            MailjetRequest request = new MailjetRequest(Emailv31.resource)
                    .property(Emailv31.MESSAGES, new JSONArray()
                            .put(new JSONObject()
                                    .put(Emailv31.Message.FROM, new JSONObject()
                                            .put("Email", "jesseqty74@gmail.com")
                                            .put("Name", "Chango Cooperatives"))
                                    .put(Emailv31.Message.TO, new JSONArray()
                                            .put(new JSONObject()
                                                    .put("Email", emailAddress)
                                                    .put("Name", memberName)))
                                    .put(Emailv31.Message.SUBJECT, "Testing")
                                    .put(Emailv31.Message.TEXTPART, "TRANSACTIONS\n\n" + emailMessage)
                            )
                    );
            MailjetResponse response = client.post(request);
            if(response.getStatus() == 200){
                return new EmailResponseDto("succesful", "Email sent to " + emailAddress);
            } else {
                throw  new EmailSendingException("Failed to sent email");
            }
        } catch (MailjetException | MailjetSocketTimeoutException e){
            throw new EmailSendingException("Failed to send email " + e.getMessage());
        }
    }
}