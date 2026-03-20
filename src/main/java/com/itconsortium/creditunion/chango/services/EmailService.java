package com.itconsortium.creditunion.chango.services;

import com.itconsortium.creditunion.chango.exceptions.EmailSendingException;
import com.itconsortium.creditunion.chango.exceptions.NoTransactionsAvailableException;
import com.itconsortium.creditunion.chango.projections.MemberAccountTransactionSummaryDto;
import com.itconsortium.creditunion.chango.repository.MemberAccountTransactionRepository;
import com.itconsortium.creditunion.chango.repository.MemberRepository;
import com.itconsortium.creditunion.chango.repository.SubscriptionRepository;
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
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private MemberAccountTransactionRepository memberAccountTransactionRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Value("${mailjet.apikey.public}")
    private String API_KEY_PUBLIC;

    @Value("${mailjet.apikey.private}")
    private String API_KEY_PRIVATE;

    private List<MemberAccountTransactionSummaryDto> getLast90DaysStatements(String msisdn) {
        log.info("Getting statements of last 90 days");
        //Get a date 90 days in the past
        LocalDate boundaryDate = LocalDate.now().minusDays(90);

        //Returns the list of transactions after the boundary date for a specific member within a group
        List<MemberAccountTransactionSummaryDto> transactions = memberAccountTransactionRepository.findByTransactionDate(boundaryDate, msisdn);

        if (transactions.isEmpty()) {
            throw new NoTransactionsAvailableException("There are no transaction records");
        } else {
            return transactions;
        }
    }

    private String getEmailMessage(List<MemberAccountTransactionSummaryDto> memberAccountTransactionSummaryDtos) {
        String emailMessage = "";

        for (MemberAccountTransactionSummaryDto statement : memberAccountTransactionSummaryDtos) {
            emailMessage += "On " + statement.getTransactionDate() +
                    "\nTransaction Type: " + statement.getTransactionType() +
                    "\nAmount Transacted: GHS " + statement.getAmount() + "\n\n";
        }
        return emailMessage;
    }

    public MailjetResponse sendEmail(String emailAddress) {
        String msisdn = memberRepository.findMemberByEmailAddress().getMsisdn();
        String emailMessage = getEmailMessage(getLast90DaysStatements(msisdn));

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
                                                    .put("Name", "")))
                                    .put(Emailv31.Message.SUBJECT, "Testing")
                                    .put(Emailv31.Message.TEXTPART, "STATEMENTS\n" + emailMessage)
                            )
                    );
            MailjetResponse response = client.post(request);
            return response;
        } catch (MailjetException | MailjetSocketTimeoutException e){
            throw new EmailSendingException("Failed to send email " + e.getMessage());
        }
    }
}