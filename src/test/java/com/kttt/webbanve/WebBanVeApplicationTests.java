package com.kttt.webbanve;


import com.kttt.webbanve.services.PdfService.GeneratePdfService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.FileSystems;

@SpringBootTest
class WebBanVeApplicationTests {
	@Autowired
	GeneratePdfService generatePdfService;
	@Test
	void contextLoads() throws Exception {
		generatePdfService.addImgToPDF("HD550121126070");
    }

}
