package com.example.billingservice.controller;


import com.example.billingservice.api.Billing;
import com.example.billingservice.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RequestMapping("wholesaler/api/")
@RestController()
public class MailingController {
    @Autowired
    Billing billingOp;

    @PostMapping("/generatePDFBill")
    public ResponseEntity<String> generatePDFBill(@RequestBody Bill bill) {
        billingOp.generatePDFBill(bill);
        return new ResponseEntity<>("Bill generation success", HttpStatus.OK);
    }

    @PostMapping("/sendInvoiceMail")
    public ResponseEntity<String> sendInvoiceMail(@RequestParam String invoiceId, @RequestParam String mailId, @RequestParam Boolean isWholesaler) {
        billingOp.sendInvoiceMail(invoiceId, mailId, isWholesaler);
        return new ResponseEntity<>("Mail sent successfully", HttpStatus.OK);
    }

//    @GetMapping("/displayWholesalerById")
//    public Wholesaler displayWholesaler(@RequestParam int wholesalerId) {
//        return wholesalerOp.displayWholesaler(wholesalerId);
//    }

}


