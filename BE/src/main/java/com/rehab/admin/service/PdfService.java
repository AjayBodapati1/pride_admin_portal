package com.rehab.admin.service;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;
import com.lowagie.text.pdf.draw.LineSeparator;
import com.rehab.admin.execptions.LogoNotFoundException;
import com.rehab.admin.model.PdfRequest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayOutputStream;

import static java.awt.Color.BLACK;

@Service
public class PdfService {

    public byte[] generatePdf(PdfRequest request) {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Document document = new Document(PageSize.A4, 40, 40, 40, 40);

        PdfWriter.getInstance(document, outputStream);

        document.open();

        // ==================== Fonts ====================
        Font hospitalFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BLACK);
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, Color.BLUE);
        Font sectionFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 13, Color.DARK_GRAY);
        Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

        // ==================== Header ====================
        PdfPTable header = new PdfPTable(2);
        header.setWidthPercentage(100);
        header.setWidths(new float[]{1f, 5f});
        header.getDefaultCell().setBorder(Rectangle.NO_BORDER);

        Image logo = null;
        try {
            logo = Image.getInstance( new ClassPathResource("logo.png").getURL());
        } catch (Exception e) {
            throw new LogoNotFoundException("Logo not found");
        }

        logo.scaleAbsolute(60, 60);

        PdfPCell logoCell = new PdfPCell(logo);
        logoCell.setBorder(Rectangle.NO_BORDER);
        logoCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        PdfPCell titleCell = new PdfPCell();
        titleCell.setBorder(Rectangle.NO_BORDER);
        titleCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        Paragraph hospital =
                new Paragraph("Pride Audiology & Child Development Center",
                        hospitalFont);

        hospital.setSpacingAfter(5);
        titleCell.addElement(hospital);

        header.addCell(logoCell);
        header.addCell(titleCell);

        document.add(header);

        LineSeparator line = new LineSeparator();
        line.setLineWidth(1f);
        line.setPercentage(100);
        line.setLineColor(BLACK);

        document.add(new Paragraph(" "));
        document.add(new Chunk(line));

        // ==================== Report ====================
        Paragraph reportTitle = new Paragraph("Speech and Language Therapy Progress Report", titleFont);
        reportTitle.setAlignment(Element.ALIGN_CENTER);
        document.add(reportTitle);

        // ==================== Student Details ====================
        Paragraph studentDetails = new Paragraph();
        studentDetails.setSpacingBefore(10);
        studentDetails.setSpacingAfter(15);
        studentDetails.setLeading(22);

        studentDetails.add(new Chunk("Name : " , sectionFont));
        studentDetails.add(new Chunk(request.getName(), normalFont));
        studentDetails.add(Chunk.NEWLINE);

        studentDetails.add(new Chunk("Age : " , sectionFont));
        studentDetails.add(new Chunk(String.valueOf(request.getAge()), normalFont));
        studentDetails.add(Chunk.NEWLINE);

        studentDetails.add(new Chunk("Gender : " , sectionFont));
        studentDetails.add(new Chunk(request.getGender(), normalFont));

        document.add(studentDetails);

        // ==================== Professional Diagnosis ====================
        document.add(new Paragraph("Professional Diagnosis", sectionFont));
        document.add(new Paragraph(request.getDiagnosis(), normalFont));
        document.add(new Paragraph(" "));

        // ==================== Therapy Progress ====================
        Paragraph therapyProgress = new Paragraph("Therapy Progress", titleFont);
        therapyProgress.setAlignment(Element.ALIGN_CENTER);
        document.add(therapyProgress);
        document.add(new Paragraph(" "));

        // ==================== Attention and Social Interaction ====================
        document.add(new Paragraph("Attention and Social Interaction", sectionFont));
        document.add(new Paragraph(request.getSocialInteraction(), normalFont));
        document.add(new Paragraph(" "));

        // ==================== Receptive Language ====================
        document.add(new Paragraph("Receptive Language", sectionFont));
        document.add(new Paragraph(request.getRespectiveLanguage(), normalFont));
        document.add(new Paragraph(" "));

        // ==================== Expressive Language ====================
        document.add(new Paragraph("Expressive Language", sectionFont));
        document.add(new Paragraph(request.getExpressiveLanguage(), normalFont));
        document.add(new Paragraph(" "));

        // ==================== School Performance ====================
        document.add(new Paragraph("School Performance", sectionFont));
        document.add(new Paragraph(request.getSchoolPerformance(), normalFont));
        document.add(new Paragraph(" "));

        // ==================== Summary ====================
        document.newPage();

        document.add(new Paragraph("Summary", sectionFont));
        document.add(new Paragraph(request.getSummary(), normalFont));
        document.add(new Paragraph(" "));

        // ==================== Recommendations ====================
        document.add(new Paragraph("Recommendations", sectionFont));
        document.add(new Paragraph(request.getRecommendations(), normalFont));
        document.add(new Paragraph(" "));

        // ==================== Signature ====================
        PdfPTable signTable = new PdfPTable(3);
        signTable.setWidthPercentage(100);
        signTable.setSpacingBefore(40);

        // 1. Pathologist Cell
        PdfPCell pathologist = new PdfPCell();
        pathologist.setBorder(Rectangle.NO_BORDER);
        Paragraph line1 = new Paragraph("____________________");
        line1.setAlignment(Element.ALIGN_CENTER);
        Paragraph label1 = new Paragraph("Speech-Language Pathologist.");
        label1.setAlignment(Element.ALIGN_CENTER);
        pathologist.addElement(line1);
        pathologist.addElement(label1);

        // 2. Registration Number Cell
        PdfPCell registrationNumber = new PdfPCell();
        registrationNumber.setBorder(Rectangle.NO_BORDER);
        Paragraph line2 = new Paragraph("____________________");
        line2.setAlignment(Element.ALIGN_CENTER);
        Paragraph label2 = new Paragraph("Registration No.");
        label2.setAlignment(Element.ALIGN_CENTER);
        registrationNumber.addElement(line2);
        registrationNumber.addElement(label2);

        // 3. Signature Cell
        PdfPCell sign = new PdfPCell();
        sign.setBorder(Rectangle.NO_BORDER);
        Paragraph line3 = new Paragraph("____________________");
        line3.setAlignment(Element.ALIGN_CENTER);
        Paragraph label3 = new Paragraph("Signature");
        label3.setAlignment(Element.ALIGN_CENTER);
        sign.addElement(line3);
        sign.addElement(label3);

        signTable.addCell(pathologist);
        signTable.addCell(registrationNumber);
        signTable.addCell(sign);

        document.add(signTable);

        document.close();

        return outputStream.toByteArray();
    }

}