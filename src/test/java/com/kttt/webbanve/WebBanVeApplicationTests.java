package com.kttt.webbanve;


import com.kttt.webbanve.services.GeneratePdfService;
import com.kttt.webbanve.services.MailSenderService;
import com.kttt.webbanve.services.TicketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest
class WebBanVeApplicationTests {
	@Autowired
	GeneratePdfService generatePdfService;

	@Autowired
	TicketService ticketService;
	@Autowired
	MailSenderService mailSenderService;

	@Test
	void contextLoads() throws Exception {
	}
}
