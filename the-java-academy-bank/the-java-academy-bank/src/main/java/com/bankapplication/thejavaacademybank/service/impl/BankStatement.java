package com.bankapplication.thejavaacademybank.service.impl;


import com.bankapplication.thejavaacademybank.dto.EmailDetails;
import com.bankapplication.thejavaacademybank.entity.Transaction;
import com.bankapplication.thejavaacademybank.entity.User;
import com.bankapplication.thejavaacademybank.respository.TransactionRepository;
import com.bankapplication.thejavaacademybank.respository.UserRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Slf4j
public class BankStatement {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    private static final String FILE = "C:\\Users\\user\\Desktop\\MyStatement.pdf";

    /*
    1.Retrieve list of transactions within a date range given an account number
    2.Generate a pdf file of the transactions
    3.Send the file via email
     */

    public List<Transaction> generateStatement(String accountNumber, String startDate, String endDate) throws FileNotFoundException, DocumentException {
        LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
        LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);

        List<Transaction> transactionList = transactionRepository.findAll().stream()
                .filter(transaction -> transaction.getAccountNumber().equals(accountNumber))
                .filter(transaction -> {
                    LocalDate transactionDate = transaction.getCreatedAt();
                    return transactionDate != null &&
                            !transactionDate.isBefore(start) &&
                            !transactionDate.isAfter(end);
                })
                .collect(Collectors.toList());

        User user = userRepository.findByAccountNumber(accountNumber);
        if (user == null) {
            // Handle the case where user is not found
            // For example, throw an exception or return an appropriate response
            throw new RuntimeException("User not found for account number: " + accountNumber);
        }
        String customerName = user.getFirstName() + " " + user.getOtherName() + " " + user.getLastName();

        Rectangle statementSize = new Rectangle(PageSize.A4);
        Document document = new Document(statementSize);
        OutputStream outputStream = new FileOutputStream(FILE);
        PdfWriter.getInstance(document, outputStream);
        document.open();

        PdfPTable bankInfoTable = new PdfPTable(1);
        PdfPCell bankName = new PdfPCell(new Phrase("The Demo Bank App"));
        bankName.setBorder(0);
        bankName.setBackgroundColor(BaseColor.BLUE);
        bankName.setPadding(20f);

        PdfPCell bankAddress = new PdfPCell(new Phrase("Tahachal-13, Kathmandu, Nepal"));
        bankAddress.setBorder(0);
        bankInfoTable.addCell(bankName);
        bankInfoTable.addCell(bankAddress);

        PdfPTable statementInfo = new PdfPTable(2);
        statementInfo.setWidthPercentage(100);
        PdfPCell startDateCell = new PdfPCell(new Phrase("Start Date: " + startDate));
        startDateCell.setBorder(0);
        PdfPCell endDateCell = new PdfPCell(new Phrase("End Date: " + endDate));
        endDateCell.setBorder(0);
        PdfPCell customerNameCell = new PdfPCell(new Phrase("Customer Name: " + customerName));
        customerNameCell.setBorder(0);
        PdfPCell addressCell = new PdfPCell(new Phrase("Customer Address: " + user.getAddress()));
        addressCell.setBorder(0);

        statementInfo.addCell(startDateCell);
        statementInfo.addCell(endDateCell);
        statementInfo.addCell(customerNameCell);
        statementInfo.addCell(addressCell);

        PdfPTable transactionsTable = new PdfPTable(4);
        transactionsTable.setWidthPercentage(100);
        PdfPCell dateCell = new PdfPCell(new Phrase("DATE"));
        dateCell.setBackgroundColor(BaseColor.BLUE);
        PdfPCell transactionTypeCell = new PdfPCell(new Phrase("TRANSACTION TYPE"));
        transactionTypeCell.setBackgroundColor(BaseColor.BLUE);
        PdfPCell transactionAmountCell = new PdfPCell(new Phrase("TRANSACTION AMOUNT"));
        transactionAmountCell.setBackgroundColor(BaseColor.BLUE);
        PdfPCell statusCell = new PdfPCell(new Phrase("STATUS"));
        statusCell.setBackgroundColor(BaseColor.BLUE);

        transactionsTable.addCell(dateCell);
        transactionsTable.addCell(transactionTypeCell);
        transactionsTable.addCell(transactionAmountCell);
        transactionsTable.addCell(statusCell);

        transactionList.forEach(transaction -> {
            transactionsTable.addCell(new Phrase(transaction.getCreatedAt().toString()));
            transactionsTable.addCell(new Phrase(transaction.getTransactionType()));
            transactionsTable.addCell(new Phrase(transaction.getAmount().toString()));
            transactionsTable.addCell(new Phrase(transaction.getStatus()));
        });

        document.add(bankInfoTable);
        document.add(statementInfo);
        document.add(transactionsTable);

        document.close();

        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(user.getEmail())
                .subject("STATEMENT OF ACCOUNT")
                .messageBody("Kindly find your requested account statement attached!")
                .attachment(FILE)
                .build();

        emailService.sendEmailWithAttachment(emailDetails);

        return transactionList;
    }




}
