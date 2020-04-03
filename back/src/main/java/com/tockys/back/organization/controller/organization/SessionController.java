package com.tockys.back.organization.controller.organization;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tockys.back.core.helper.Helper;
import com.tockys.back.session.dto.SessionDTO;
import com.tockys.back.session.model.Session;
import com.tockys.back.session.service.SessionService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SessionController {
	
	@Autowired
	private SessionService sessionService;
	
	@RequestMapping(value= "/organization/{id}/session/", method = RequestMethod.GET)
	public ResponseEntity<?> readOrganizationSession(@PathVariable Long id, @RequestParam String minDate, @RequestParam String maxDate) throws Exception {
        List<SessionDTO> listSessions = new ArrayList<SessionDTO>();
        
        for (Session session: sessionService.getSessionByOrganizationId(id, OffsetDateTime.parse(minDate), OffsetDateTime.parse(maxDate))) {
        	listSessions.add(new SessionDTO(session));
        }
        return ResponseEntity.ok(listSessions);
	}

}
