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
import com.stripe.param.SubscriptionUpdateParams;

@Service
public class StripeService {

	@Value("${stripe.apiKey}")
	private String apiKey;

	@Value("${stripe.trialQt}")
	private Long trialQt;
	
	public Customer createCustomer(String token, String email) throws StripeException {
        Stripe.apiKey = apiKey;
		CustomerCreateParams customerParams =
		  CustomerCreateParams.builder()
		    .setSource(token)
		    .setEmail(email)
		    .build();
	
		return Customer.create(customerParams);
	}
	
	public Subscription createSubscription(Customer customer, String plan, Long quantity) throws StripeException {
        Stripe.apiKey = apiKey;
		Item item = Item.builder()
				.setPlan(plan)
				.setQuantity(quantity)
				.build();
		
		boolean trial = quantity < trialQt;
		
		SubscriptionCreateParams subscriptionParams = 
				SubscriptionCreateParams.builder()
				.setCustomer(customer.getId())
				.addItem(item)
			    .setProrationBehavior(SubscriptionCreateParams.ProrationBehavior.ALWAYS_INVOICE)
				.build()
				;
		
		return Subscription.create(subscriptionParams);
	}
	
	public Subscription getSubscription(String subscriptionId) throws StripeException {
        Stripe.apiKey = apiKey;
		return Subscription.retrieve(subscriptionId);
	}
	
	public Subscription updateSubscription(String subscriptionId, Long quantity) throws StripeException {
        Stripe.apiKey = apiKey;
        Subscription subscription = getSubscription(subscriptionId);
		
		boolean trial = quantity < trialQt;

		SubscriptionUpdateParams params =
		  SubscriptionUpdateParams.builder()
		    .addItem(
		      SubscriptionUpdateParams.Item.builder()
		        .setId(subscription.getItems().getData().get(0).getId())
				.setQuantity(quantity)
		        .build())
			.setTrialFromPlan(trial)
		    .build();
		
		return subscription.update(params);
	}
	
	public Subscription addOneMemberSubscription(String subscriptionId) throws StripeException {
        Stripe.apiKey = apiKey;
        Subscription subscription = getSubscription(subscriptionId);
		
        Long quantity = subscription.getItems().getData().get(0).getQuantity() + 1;
		boolean trial = quantity < trialQt;

		SubscriptionUpdateParams params =
		  SubscriptionUpdateParams.builder()
		    .addItem(
		      SubscriptionUpdateParams.Item.builder()
		        .setId(subscription.getItems().getData().get(0).getId())
				.setQuantity(quantity)
		        .build())
			.setTrialFromPlan(trial)
		    .build();
		
		return subscription.update(params);
	}
	
	public Subscription removeOneMemberSubscription(String subscriptionId) throws StripeException {
        Stripe.apiKey = apiKey;
        Subscription subscription = getSubscription(subscriptionId);
		
        Long quantity = subscription.getItems().getData().get(0).getQuantity() - 1;
		boolean trial = quantity < trialQt;

		SubscriptionUpdateParams params =
		  SubscriptionUpdateParams.builder()
		    .addItem(
		      SubscriptionUpdateParams.Item.builder()
		        .setId(subscription.getItems().getData().get(0).getId())
				.setQuantity(quantity)
		        .build())
			.setTrialFromPlan(trial)
		    .build();
		
		return subscription.update(params);
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
