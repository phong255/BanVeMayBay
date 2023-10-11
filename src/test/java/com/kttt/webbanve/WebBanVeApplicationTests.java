package com.kttt.webbanve;

import com.itextpdf.text.DocumentException;
import com.kttt.webbanve.models.Flight;
import com.kttt.webbanve.models.Plane;
import com.kttt.webbanve.models.PlaneFlight;
import com.kttt.webbanve.services.FlightServiceImpl;
import com.kttt.webbanve.services.PdfService.AddImage;
import com.kttt.webbanve.services.PdfService.GeneratePdf;
import com.kttt.webbanve.services.MailSenderService;
import com.kttt.webbanve.services.PlaneFlightServiceImpl;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@SpringBootTest
class WebBanVeApplicationTests {
	@Autowired
	MailSenderService mss;
	@Autowired
	GeneratePdf generatePdf;
	@Autowired
	AddImage addImage;
	@Autowired
	FlightServiceImpl fls;
	@Autowired
	PlaneFlightServiceImpl pfs;
	@Test
	void contextLoads() {
		Page<PlaneFlight> planeFlightPage = pfs.getAllFlight(1,1);
		List<Plane> planeList =  pfs.convertToListFlight(planeFlightPage.getContent());
		HashSet<Plane> listfl = new HashSet<>();
		for (Plane p :
				planeList) {
			listfl.add(p);
		}
		for(Plane f:listfl){
			System.out.println(f.getPlaneID() + "  " + f.getFlights().get(0).getFlightID());
		}
    }

}
