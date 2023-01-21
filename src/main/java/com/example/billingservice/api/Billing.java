package com.example.billingservice.api;

import com.example.billingservice.model.Bill;
import org.springframework.http.ResponseEntity;

public interface Billing {

    ResponseEntity<String> generatePDFBill(Bill bill);

    ResponseEntity<String> sendInvoiceMail(String invoiceId, String mailId, Boolean isWholesaler);
}
