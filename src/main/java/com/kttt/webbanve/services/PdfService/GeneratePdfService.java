package com.kttt.webbanve.services.PdfService;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfWriter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Service;
import java.io.*;
import java.nio.file.FileSystems;


@Service
public class GeneratePdfService {
    public void htmlToPdf(String processedHtml,String order) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter pdfWriter = new PdfWriter(byteArrayOutputStream);
        DefaultFontProvider defaultFont = new DefaultFontProvider(false,true,false);
        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setFontProvider(defaultFont);
        HtmlConverter.convertToPdf(processedHtml,pdfWriter,converterProperties);
        String path = FileSystems.getDefault().getPath(new String("./")).toAbsolutePath().getParent() + "\\src\\main\\resources\\static\\PdfOrders\\" + order + ".pdf";
        FileOutputStream fout = new FileOutputStream(path);
        byteArrayOutputStream.writeTo(fout);
        byteArrayOutputStream.close();
        byteArrayOutputStream.flush();
        fout.close();
    }
    public void addImgToPDF(String qrcode) throws Exception{
        String pathImg = FileSystems.getDefault().getPath(new String("./")).toAbsolutePath().getParent() + "\\src\\main\\resources\\static\\qrcodes\\" + qrcode + ".png";
        String pathPdf = FileSystems.getDefault().getPath(new String("./")).toAbsolutePath().getParent() + "\\src\\main\\resources\\static\\PdfOrders\\" + qrcode + ".pdf";
        File filePdf = new File(pathPdf);
        PDDocument doc = new PDDocument().load(filePdf);
        PDPage page = new PDPage();
        doc.addPage(page);
        PDImageXObject image = PDImageXObject.createFromFile(pathImg,doc);
        PDPageContentStream contents  = new PDPageContentStream(doc,page);
        contents.drawImage(image,150f,300f,300,300);
        contents.close();
        doc.save(filePdf);
        doc.close();
    }
}
