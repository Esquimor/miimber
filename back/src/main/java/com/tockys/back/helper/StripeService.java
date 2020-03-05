package com.tockys.back.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Subscription;
import com.stripe.param.ChargeCreateParams;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.SubscriptionCreateParams;
import com.stripe.param.SubscriptionCreateParams.Item;

@Service
public class StripeService {

	@Value("${stripe.apiKey}")
	private String apiKey;
	
	public Customer createCustomer(String token, String email) throws StripeException {
        Stripe.apiKey = apiKey;
		CustomerCreateParams customerParams =
		  CustomerCreateParams.builder()
		    .setSource(token)
		    .setEmail(email)
		    .build();
	
		return Customer.create(customerParams);
	}
	
	public Subscription createSubscription(Customer customer, String plan) throws StripeException {
        Stripe.apiKey = apiKey;
		Item item = Item.builder().setPlan(plan).build();
		
		SubscriptionCreateParams subscriptionParams = 
				SubscriptionCreateParams.builder()
				.setCustomer(customer.getId())
				.addItem(item)
				.build()
				;
		
		return Subscription.create(subscriptionParams);
	}
	
	public Subscription getSubscription(String subscriptionId) throws StripeException {
        Stripe.apiKey = apiKey;
		return Subscription.retrieve(subscriptionId);
	}
	
	public Charge chargeCustomerByCard(Customer customer, String currency, Long amount) throws StripeException {
        Stripe.apiKey = apiKey;
		ChargeCreateParams chargeParams =
		  ChargeCreateParams.builder()
		    .setAmount(amount)
		    .setCurrency(currency)
		    .setCustomer(customer.getId())
		    .build();
		
		return Charge.create(chargeParams);
	}
}
