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

@Service
public class MailJetService {

	@Value("${mailjet.public}")
	private String apiKeyPublic;

	@Value("${mailjet.private}")
	private String apiKeyPrivate;
	
	@Value("${mailjet.register}")
	private Integer idRegister;
	
	@Value("${mailjet.reset_password}")
	private Integer idResetPassword;
	
	@Value("${mailjet.invitation}")
	private Integer idInvitation;
	
	@Value("${mailjet.change_email}")
	private Integer idChangeEmail;
    
    public MailjetResponse sendEmail(String fromEmail, String fromName, String toEmail, String toName, String subject, JSONObject variables, Integer template) throws MailjetException, MailjetSocketTimeoutException {
    	MailjetClient client;
    	client = new MailjetClient(apiKeyPublic, apiKeyPrivate, new ClientOptions("v3.1"));
    	MailjetRequest request = new MailjetRequest(Emailv31.resource)
    			.property(Emailv31.MESSAGES, new JSONArray()
    	                .put(new JSONObject()
    	                    .put(Emailv31.Message.FROM, new JSONObject()
    	                        .put("Email", fromEmail)
    	                        .put("Name", fromName))
    	                    .put(Emailv31.Message.TO, new JSONArray()
    	                        .put(new JSONObject()
    	                            .put("Email", toEmail)
    	                            .put("Name", toName)))
    	                    .put(Emailv31.Message.VARIABLES, variables)
    	                    .put(Emailv31.Message.SUBJECT, subject)
    	                    .put(Emailv31.Message.TEMPLATELANGUAGE, true)
    	                    .put(Emailv31.Message.TEMPLATEID, template)));
        return client.post(request);
    }
    
    public MailjetResponse sendEmailRegister(String email, String name, String link) throws MailjetException, MailjetSocketTimeoutException {
    	return this.sendEmail(
			"no-reply@test280407.ga", 
			"Miimber", 
			email, 
			name, 
			"Create a account", 
			new JSONObject()
				.put("link", link), 
			idRegister
		);
    }
    
    public MailjetResponse sendEmailResetPassword(String email, String name, String link) throws MailjetException, MailjetSocketTimeoutException {
    	return this.sendEmail(
			"no-reply@test280407.ga", 
			"Miimber", 
			email, 
			name, 
			"Reset password", 
			new JSONObject()
				.put("link", link), 
			idResetPassword
		);
    }
    
    public MailjetResponse sendEmailInvitation(String email, String name, String link, String sender, String organization) throws MailjetException, MailjetSocketTimeoutException {
    	return this.sendEmail(
			"no-reply@test280407.ga", 
			"Miimber", 
			email, 
			name, 
			"Invitation", 
			new JSONObject()
				.put("sender", sender)
				.put("organization", organization)
				.put("link", link),
			idInvitation
		);
    }
    
    public MailjetResponse sendEmailChangeEmail(String email, String name, String link) throws JSONException, MailjetException, MailjetSocketTimeoutException {
    	return this.sendEmail(
    			"no-reply@test280407.ga",
    			"Miimber",
    			email,
    			name,
    			"Email",
    			new JSONObject().put("link", link),
    			idChangeEmail
    		);
    }
}
