<template>
  <TemplateSidePanelRight
    :title="$t('organization.settings.stripe.title')"
    :loading="loading"
    hasPadding
    @confirm="confirm"
  >
    <div class="columns">
      <div class="column OrganizationCreate-column">
        <b-field
          :label="$t('settings.organizationCreate.cardNumber')"
          :message="cardNumberElement.message"
          :type="cardNumberElement.type"
        >
          <div
            id="card-number-element"
            class="OrganizationCreate-stripe-input"
          ></div>
        </b-field>
      </div>
    </div>
    <div class="columns">
      <div class="column OrganizationCreate-column">
        <b-field
          :label="$t('settings.organizationCreate.expiry')"
          :message="cardExpiryElement.message"
          :type="cardExpiryElement.type"
        >
          <div
            id="card-expiry-element"
            class="OrganizationCreate-stripe-input"
          ></div>
        </b-field>
      </div>
      <div class="column OrganizationCreate-column">
        <b-field
          :label="$t('settings.organizationCreate.cvc')"
          :message="cardCvcElement.message"
          :type="cardCvcElement.type"
        >
          <div
            id="card-cvc-element"
            class="OrganizationCreate-stripe-input"
          ></div>
        </b-field>
      </div>
    </div>
  </TemplateSidePanelRight>
</template>

<script>
"use strict";

import { loadStripe } from "@stripe/stripe-js";

import TemplateSidePanelRight from "@core/template/TemplateSidePanelRight";

export default {
  name: "OrganizationSettingsEditStripe",
  components: {
    TemplateSidePanelRight
  },
  data() {
    return {
      loading: false,
      cardNumberElement: {
        element: null,
        message: "",
        type: ""
      },
      cardExpiryElement: {
        element: null,
        message: "",
        type: ""
      },
      cardCvcElement: {
        element: null,
        message: "",
        type: ""
      },
      stripe: null
    };
  },
  methods: {
    confirm() {
      this.loading = true;
      let that = this;
      this.stripe
        .createToken(this.cardNumberElement.element)
        .then(function(result) {
          if (result.error) {
            // Inform the user if there was an error.
            let errorElement = document.getElementById("card-errors");
            errorElement.textContent = result.error.message;
            that.loading = false;
          } else {
            // Send the token to your server.
            that.$store
              .dispatch("organization/updateCardOrganization", {
                token: result.token.id
              })
              .then(() => {
                this.$store.dispatch("core/closeSideBar");
                that.$buefy.toast.open({
                  message: that.$t("organization.settings.stripe.success"),
                  type: "is-primary"
                });
              })
              .catch(() => {
                that.loading = false;
              });
          }
        })
        .catch(() => {
          that.loading = false;
        });
    },
    setCardNumberElement(event) {
      if (event.error) {
        this.cardNumberElement.type = "is-danger";
        this.cardNumberElement.message = event.error.message;
      } else {
        this.cardNumberElement.type = "";
        this.cardNumberElement.message = "";
      }
    },
    setCardExpiryElement(event) {
      if (event.error) {
        this.cardExpiryElement.type = "is-danger";
        this.cardExpiryElement.message = event.error.message;
      } else {
        this.cardExpiryElement.type = "";
        this.cardExpiryElement.message = "";
      }
    },
    setCardCvcElement(event) {
      if (event.error) {
        this.cardCvcElement.type = "is-danger";
        this.cardCvcElement.message = event.error.message;
      } else {
        this.cardCvcElement.type = "";
        this.cardCvcElement.message = "";
      }
    }
  },
  mounted: function() {
    loadStripe(`pk_test_NOsGYAIvHj3VuuEhbzvetWgW00BUPzCdgM`).then(stripe => {
      this.stripe = stripe;
      let elements = stripe.elements();

      this.cardNumberElement.element = elements.create("cardNumber");
      this.cardNumberElement.element.mount("#card-number-element");
      this.cardNumberElement.element.addEventListener(
        "change",
        this.setCardNumberElement
      );

      this.cardExpiryElement.element = elements.create("cardExpiry");
      this.cardExpiryElement.element.mount("#card-expiry-element");
      this.cardExpiryElement.element.addEventListener(
        "change",
        this.setCardExpiryElement
      );

      this.cardCvcElement.element = elements.create("cardCvc");
      this.cardCvcElement.element.mount("#card-cvc-element");
      this.cardCvcElement.element.addEventListener(
        "change",
        this.setCardCvcElement
      );
    });
  },
  beforeDestroy() {
    this.cardNumberElement.element.removeEventListener(
      "change",
      this.setCardNumberElement
    );
    this.cardExpiryElement.element.removeEventListener(
      "change",
      this.setCardExpiryElement
    );
    this.cardCvcElement.element.removeEventListener(
      "change",
      this.setCardCvcElement
    );
  }
};
</script>

<style lang="scss"></style>
