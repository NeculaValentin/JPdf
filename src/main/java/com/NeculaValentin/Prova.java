package com.NeculaValentin;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import com.itextpdf.text.*;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class Prova {
    private static String FILE = "test.pdf";
    private static Font redbigFont = new Font(Font.FontFamily.HELVETICA, 52,  Font.BOLD, BaseColor.RED);
    private static Font bigFont = new Font(Font.FontFamily.HELVETICA, 40,  Font.BOLD);
    private static Font subBoldFont  = new Font(Font.FontFamily.HELVETICA, 16 ,Font.BOLD);
    private static Font subFont  = new Font(Font.FontFamily.HELVETICA, 16);
    private static Font redsubFont  = new Font(Font.FontFamily.HELVETICA, 14,  Font.BOLD, BaseColor.RED);
    private static Font smallBold  = new Font(Font.FontFamily.COURIER, 14,  Font.BOLD, BaseColor.LIGHT_GRAY);

    public static void main(String[] args) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            aggiungiMetaDati(document);
            aggiungiPrefazione(document);
            aggiungiContenuto(document);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* iText permette di aggiungere metadati al pdf che possono essere
     * visualizzati in Adobe Reader da File -> Proprietà */
    private static void aggiungiMetaDati(Document document) {
        document.addTitle("PDF");
        document.addSubject("Uso di iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Jia Hao-Valentin");
        document.addCreator("Necula-Ruan");
    }

    private static void aggiungiPrefazione(Document document) throws DocumentException, IOException {
        Paragraph prefazione = new Paragraph();

        // Aggiungiamo frase con due font diversi
        Phrase phrase = new Phrase();
        phrase.add(new Chunk("THE EVOLUTION OF ",  bigFont));
        phrase.add(new Chunk("PDF", redbigFont));

        // Aggiungiamo il titolo fatto dalla frase
        Paragraph para = new Paragraph();
        para.add(phrase);

        //Allineamento centrale
        para.setAlignment(Element.ALIGN_CENTER);
        prefazione.add(para);
        aggiungiLineaVuota(prefazione, 1);

        //Aggiunta immagine
        String imageFile = "immag.PNG";
        Image image = Image.getInstance(imageFile);
        float scaler = ((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin() ) / image.getWidth()) * 100;
        image.scalePercent(scaler);
        prefazione.add(image);

        // Questa linea scrive "Documento generato da: nome utente, data"
        prefazione.add(new Paragraph("Documento generato da: " + System.getProperty("user.name") + ", " + new Date(), smallBold));

        Anchor anchor = new Anchor("Zuccante.it",smallBold);
        anchor.setName("LINK");

        anchor.setReference("https://www.zuccante.it/");
        prefazione.add(anchor);

        // Aggiunta al documento
        document.add(prefazione);

    }

    private static void aggiungiContenuto(Document document) throws DocumentException, IOException {
        Paragraph questionParagraph = new Paragraph("WHAT IS PDF?", bigFont);
        questionParagraph.setAlignment(Element.ALIGN_CENTER);
        aggiungiLineaVuota(questionParagraph, 1);
        Chapter chapterOne = new Chapter(questionParagraph,1);

        Paragraph answerParagraph = new Paragraph();
        Phrase answerPhrase = new Phrase();
        answerPhrase.add(new Chunk("PDF stands for ", subBoldFont));
        answerPhrase.add(new Chunk("PORTABLE DOCUMENT FORMAT", redsubFont));
        answerParagraph.setAlignment(Element.ALIGN_CENTER);
        answerParagraph.add(answerPhrase);
        chapterOne.add(answerParagraph);

        Paragraph one = new Paragraph();
        Phrase onePhrase = new Phrase("It is essentially an electronic paper", subFont);
        one.add(onePhrase);
        Image imageOne = Image.getInstance("CatturaUNO.PNG");
        aggiungiLineaVuota(one, 1);
        float scaler = ((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin() ) / imageOne.getWidth()) * 12;
        imageOne.scalePercent(scaler);
        one.add(imageOne);

        Paragraph two = new Paragraph();
        Phrase twoPhrase = new Phrase("It can be viewed on every device", subFont);
        two.add(twoPhrase);
        Image imageTwo = Image.getInstance("Cattura2.PNG");
        aggiungiLineaVuota(two, 1);
        float scaler2 = ((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin() ) / imageTwo.getWidth()) * 12;
        imageTwo.scalePercent(scaler2);
        two.add(imageTwo);

        Paragraph three = new Paragraph();
        Phrase threePhrase = new Phrase("It does not require specialized software or hardware", subFont);
        three.add(threePhrase);
        Image imageThree = Image.getInstance("Cattura3.PNG");
        aggiungiLineaVuota(three, 1);
        float scaler3 = ((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin() ) / imageThree.getWidth()) * 12;
        imageThree.scalePercent(scaler3);
        three.add(imageThree);

        Paragraph four = new Paragraph();
        Phrase fourPhrase = new Phrase("It preserves content and formatting", subFont);
        four.add(fourPhrase);
        Image imageFour = Image.getInstance("Cattura4.PNG");
        aggiungiLineaVuota(four, 1);
        float scaler4 = ((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin() ) / imageFour.getWidth()) * 12;
        imageFour.scalePercent(scaler4);
        four.add(imageFour);

        chapterOne.addSection(one);
        chapterOne.addSection(two);
        chapterOne.addSection(three);
        chapterOne.addSection(four);
        document.add(chapterOne);

    }

    private static void creaTabella(Section section) throws BadElementException {
        PdfPTable tabella = new PdfPTable(3);

        PdfPCell c1 = new PdfPCell(new Phrase("Titolo 1"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setGrayFill(0.8f);
        tabella.addCell(c1);

        c1 = new PdfPCell(new Phrase("Titolo 2"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setGrayFill(0.8f);
        tabella.addCell(c1);

        c1 = new PdfPCell(new Phrase("Titolo 3"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setGrayFill(0.8f);
        tabella.addCell(c1);
        tabella.setHeaderRows(1);

        tabella.addCell("1.0");
        tabella.addCell("1.1");
        tabella.addCell("1.2");
        tabella.addCell("2.1");
        tabella.addCell("2.2");
        tabella.addCell("2.3");
        section.add(tabella);
    }

    private static void aggiungiLineaVuota(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}



