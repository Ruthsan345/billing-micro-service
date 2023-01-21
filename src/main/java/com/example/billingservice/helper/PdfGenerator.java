package com.example.billingservice.helper;
import com.example.billingservice.model.Bill;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class PdfGenerator {
   public String pdfGenerator(Bill bill)  throws IOException {
        try {
            Document my_pdf_report = new Document();

            UUID randomUUID = UUID.randomUUID();
            String filename= bill.getClientId() + "_" + randomUUID.toString().replaceAll("_", "") + ".pdf";

            if (bill.isWholesalerOrRetailer()) {
                PdfWriter.getInstance(my_pdf_report, new FileOutputStream("./wholesaler_purchase_pdfs/" +filename));
            } else {
                PdfWriter.getInstance(my_pdf_report, new FileOutputStream("./retailer_purchase_pdfs/" + filename));
            }
            my_pdf_report.open();
            PdfPTable my_report_table = new PdfPTable(2);
            PdfPCell table_cell;
            Paragraph preface = new Paragraph();
            Paragraph preface1 = new Paragraph();
            preface.setSpacingAfter(4);
            preface.setSpacingBefore(1);
            preface.setIndentationLeft(200);
            preface.setIndentationRight(0);
            preface.setAlignment(Element.ALIGN_CENTER);
            preface.add(new Paragraph("QUINBAY - BLIBLI.COM\n"));
            preface1.setIndentationLeft(170);
            // preface1.setSpacingBefore(10);
            if (bill.isWholesalerOrRetailer()) {
                preface1.add(new Paragraph("Product Purchase Infomartion For Wholesaler"));
            } else {
                preface1.add(new Paragraph("Product Purchase Infomartion for Retailer"));
                // addEmptyLine(preface, 2);
            }
            preface1.setSpacingAfter(25);
            my_pdf_report.add(preface);
            my_pdf_report.add(preface1);


            my_report_table.setSpacingAfter(10);
            my_report_table.setSpacingBefore(4);
            my_report_table.getRowHeight(10);

            table_cell = new PdfPCell(new Phrase("CLIENT ID :"));
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase(Integer.toString(bill.getClientId())));
            my_report_table.addCell(table_cell);


            table_cell = new PdfPCell(new Phrase("CLIENT NAME:"));
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase(bill.getClientName()));
            my_report_table.addCell(table_cell);


            table_cell = new PdfPCell(new Phrase("PRODUCT ID:"));
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase(bill.getProduct().proId));
            my_report_table.addCell(table_cell);


            table_cell = new PdfPCell(new Phrase("PRODUCT NAME:"));
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase(bill.getProduct().proName));
            my_report_table.addCell(table_cell);

            table_cell = new PdfPCell(new Phrase("QUANTITY"));
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase(Integer.toString(bill.getQuantity())));
            my_report_table.addCell(table_cell);

            table_cell = new PdfPCell(new Phrase("BILL AMOUNT "));
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase(Integer.toString(bill.getBillAmount())));
            my_report_table.addCell(table_cell);

            table_cell = new PdfPCell(new Phrase("DISCOUNT PERCENTAGE "));
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase(Integer.toString(bill.getDiscountPercentage()) + "%"));
            my_report_table.addCell(table_cell);

            table_cell = new PdfPCell(new Phrase("GST PERCENTAGE: "));
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("18%"));
            my_report_table.addCell(table_cell);

            table_cell = new PdfPCell(new Phrase("GST AMOUNT: "));
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase(Float.toString(bill.getGstAmount())));
            my_report_table.addCell(table_cell);

            table_cell = new PdfPCell(new Phrase("Grand Total Amount: "));
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase(Float.toString(bill.getGrandBillAmount())));
            my_report_table.addCell(table_cell);


            Paragraph preface2 = new Paragraph();
            Paragraph preface3 = new Paragraph();
            preface2.setSpacingAfter(4);
            preface2.setSpacingBefore(1);
            preface2.setIndentationLeft(200);
            preface2.setIndentationRight(0);
            preface2.setSpacingBefore(80);
            preface2.setAlignment(400);
            preface2.setAlignment(Element.ALIGN_BOTTOM);
            preface3.setIndentationLeft(60);
            preface3.setIndentationRight(0);
            preface3.setSpacingBefore(30);
            preface3.add(new Paragraph(" THANKS FOR PURCHASING...KINDLY VISIT US AGAIN!"));

            my_pdf_report.add(my_report_table);
            my_pdf_report.add(preface3);
            my_pdf_report.add(preface2);
            my_pdf_report.close();
            return filename;

        } catch (DocumentException e) {
            e.printStackTrace();
            return null;
        }


    }
}
