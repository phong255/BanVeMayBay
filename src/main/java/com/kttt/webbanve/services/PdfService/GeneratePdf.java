package com.kttt.webbanve.services.PdfService;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfWriter;

import org.springframework.stereotype.Service;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.FileSystems;


@Service
public class GeneratePdf {
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

}
