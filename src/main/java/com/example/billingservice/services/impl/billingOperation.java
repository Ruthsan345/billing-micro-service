package com.example.billingservice.services.impl;

import com.example.billingservice.api.Billing;
import com.example.billingservice.helper.PdfGenerator;
import com.example.billingservice.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
public class billingOperation implements Billing {
    @Autowired
    PdfGenerator pdfGenerator;

    @Autowired private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}") private String sender;


    @Override
    public ResponseEntity<String> generatePDFBill(Bill bill) {
        try {
            String fileId= pdfGenerator.pdfGenerator(bill);
            Thread.sleep(5000);
            sendInvoiceMail(fileId, "ruthruthsan@gmail.com",bill.isWholesalerOrRetailer());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<String> sendInvoiceMail(String invoiceId, String mailId, Boolean isWholesaler ) {
        try {
            System.out.print(invoiceId);
            Thread.sleep(5000);
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper;

            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(mailId);
            mimeMessageHelper.setText("blibli.com - Invoice");
            mimeMessageHelper.setSubject("Greetings thanks for purchasing at Blibli.com\nVisit us again!");

            String filename;
            if(isWholesaler){ filename ="./wholesaler_purchase_pdfs/"+invoiceId;}
            else{ filename = "./retailer_purchase_pdfs/"+invoiceId;}

            FileSystemResource file = new FileSystemResource(new File(filename));

            mimeMessageHelper.addAttachment(file.getFilename(), file);

            javaMailSender.send(mimeMessage);
            System.out.print("mail Function invoked");
            return new ResponseEntity<>("Mail sent successfully", HttpStatus.OK);
        }

        catch (Exception e) {
            new ResponseEntity<>("Error while sending mail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }


}
