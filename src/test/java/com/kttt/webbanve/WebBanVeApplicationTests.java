package com.kttt.webbanve;

import com.itextpdf.text.DocumentException;
import com.kttt.webbanve.services.PdfService.AddImage;
import com.kttt.webbanve.services.PdfService.GeneratePdf;
import com.kttt.webbanve.services.MailSenderService;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class WebBanVeApplicationTests {
	@Autowired
	MailSenderService mss;
	@Autowired
	GeneratePdf generatePdf;
	@Autowired
	AddImage addImage;
	@Test
	void contextLoads() {
		try{
			addImage.insertQrOrder("HD550121123866");
			mss.sendMailWithAttachment("daumano69@gmail.com","Cảm ơn bạn đã tin tưởng đặt vé tại đại lý GOGO !","Chi tiết đơn hàng","HD550121123866");
		}
		catch (MessagingException e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
