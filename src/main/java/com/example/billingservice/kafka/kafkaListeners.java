package com.example.billingservice.kafka;

import com.example.billingservice.api.Billing;
import com.example.billingservice.model.Bill;
import com.example.billingservice.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.example.billingservice.controller.MailingController;

@Service
public class kafkaListeners {

        @Autowired
        ObjectMapper objectMapper;

        @Autowired
        Billing billOp;

        @KafkaListener(topics = "SEND.BILLING.INFO", groupId = "warehouse")
        public void getProductDetail(ConsumerRecord<?,String> consumerRecord){
            Bill bill = null;
            try{
                bill = objectMapper.readValue(consumerRecord.value(), new TypeReference<Bill>() {
                });
                billOp.generatePDFBill(bill);
                System.out.print("\n\n--------------------->"+bill.getBillAmount()+" "+bill.getClientName()+" "+bill.getGrandBillAmount());


            }catch(Exception e){
                System.out.print(e);
            }
        }


}
