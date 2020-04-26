package com.miimber.back.core.helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.resource.Emailv31;
import com.miimber.back.core.enums.LangEnum;

@Service
public class MailJetService {

	@Value("${mailjet.public}")
	private String apiKeyPublic;

	@Value("${mailjet.private}")
	private String apiKeyPrivate;
    
    public MailjetResponse sendEmail(String fromEmail, String fromName, String toEmail, String toName, JSONObject variables, Integer template) throws MailjetException, MailjetSocketTimeoutException {
    	MailjetClient client;
        MailjetResponse response;
    	client = new MailjetClient(apiKeyPublic, apiKeyPrivate, new ClientOptions("v3.1"));
    	MailjetRequest request = new MailjetRequest(Emailv31.resource)
    			.property(Emailv31.MESSAGES, new JSONArray()
    	                .put(new JSONObject()
    	                    .put(Emailv31.Message.FROM, new JSONObject()
    	                        .put("Email", fromEmail))
    	                    .put(Emailv31.Message.TO, new JSONArray()
    	                        .put(new JSONObject()
    	                            .put("Email", toEmail)
    	                            .put("Name", toName)))
    	                    .put(Emailv31.Message.VARIABLES, variables)
    	                    .put(Emailv31.Message.TEMPLATELANGUAGE, true)
    	                    .put(Emailv31.Message.TEMPLATEID, template)));
        response = client.post(request);
        return response;
    }
    
    public MailjetResponse sendEmailRegister(String email, String name, LangEnum lang, String token) throws MailjetException, MailjetSocketTimeoutException {
    	Integer idTemplate;
    	switch(lang) {
    		case FR:
    			idTemplate = 1379267;
    			break;
    		default:
    			idTemplate = 1357551;
    			break;
    	}
    	
    	return this.sendEmail(
			"no-reply@test280407.ga", 
			"Miimber", 
			email, 
			name, 
			new JSONObject()
				.put("token", token), 
			idTemplate
		);
    }
    
    public MailjetResponse sendEmailResetPassword(String email, String name, LangEnum lang, String link) throws MailjetException, MailjetSocketTimeoutException {
    	Integer idTemplate;
    	switch(lang) {
    		case FR:
    			idTemplate = 1359680;
    			break;
    		default:
    			idTemplate = 1379269;
    			break;
    	}
    	
    	return this.sendEmail(
			"no-reply@test280407.ga", 
			"Miimber", 
			email, 
			name, 
			new JSONObject()
				.put("link", link), 
			idTemplate
		);
    }
    
    public MailjetResponse sendEmailInvitation(String email, String name, LangEnum lang, String link, String sender, String organization) throws MailjetException, MailjetSocketTimeoutException {
    	Integer idTemplate;
    	switch(lang) {
    		case FR:
    			idTemplate = 1359682;
    			break;
    		default:
    			idTemplate = 1379272;
    			break;
    	}
    	
    	return this.sendEmail(
			"no-reply@test280407.ga", 
			"Miimber", 
			email, 
			name, 
			new JSONObject()
				.put("sender", sender)
				.put("organization", organization)
				.put("link", link),
			idTemplate
		);
    }
    
    public MailjetResponse sendEmailChangeEmail(String email, String name, LangEnum lang, String link) throws JSONException, MailjetException, MailjetSocketTimeoutException {
    	Integer idTemplate;
    	switch(lang) {
    		case FR:
    			idTemplate = 1379146;
    			break;
    		default:
    			idTemplate = 1379266;
    			break;
    	}
    	
    	return this.sendEmail(
    			"no-reply@test280407.ga",
    			"Miimber",
    			email,
    			name,
    			new JSONObject().put("link", link),
    			idTemplate
    		);
    }
}
