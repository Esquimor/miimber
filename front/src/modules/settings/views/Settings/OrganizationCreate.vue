<template>
  <TemplateDefault class="OrganizationCreate">
    <form @submit.prevent="add">
      <h1 class="title is-4 OrganizationCreate-title">
        {{ $t("settings.organization.create") }}
      </h1>
      <div class="columns">
        <div class="column is-half is-offset-one-quarter">
          <b-field
            :label="$t('settings.organizationCreate.name')"
            :message="message"
          >
            <b-input
              v-model.trim="name"
              @focus="focusName"
              @blur="searchIfExit"
              required
            ></b-input>
          </b-field>
        </div>
      </div>
      <div class="columns">
        <div
          class="column is-half is-offset-one-quarter OrganizationCreate-column OrganizationCreate-cardNumber"
        >
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
        <div
          class="column is-3 is-offset-one-quarter OrganizationCreate-column"
        >
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
        <div class="column is-3 OrganizationCreate-column">
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
      <div class="OrganizationCreate-button">
        <button
          class="button is-primary"
          :class="{ 'is-loading': loading }"
          type="submit"
          @click="add"
        >
          {{ $t("settings.organizationCreate.button") }}
        </button>
      </div>
    </form>
  </TemplateDefault>
</template>

<script>
"use strict";

import api from "@/utils/api";

import { loadStripe } from "@stripe/stripe-js";

import { STRIPE_PLAN } from "@/utils/consts";

import TemplateDefault from "@core/template/TemplateDefault";

export default {
  name: "OrganizationCreate",
  components: {
    TemplateDefault
  },
  data() {
    return {
      loading: false,
      activeStep: 0,
      name: "",
      valid: false,
      message: "",
      subscription: STRIPE_PLAN,
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
    searchIfExit() {
      if (!this.name) return;
      api
        .get(`organization/name/${this.name}`)
        .then(() => {
          this.valid = true;
        })
        .catch(() => {
          this.valid = false;
          this.name = "";
          this.message = this.$t("settings.organizationCreate.alreadyTaken");
        });
    },
    focusName() {
      this.valid = false;
      this.message = "";
    },
    add() {
      if (!this.name) return;
      if (this.loading) return;
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
              .dispatch("settings/createOrganization", {
                token: result.token.id,
                name: that.name,
                subscription: that.subscription
              })
              .then(() => {
                that.loading = false;
                that.$router.push({ name: "settings-organization" });
              })
              .catch(() => {
                that.loading = false;
              });
          }
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

<style lang="scss">
.OrganizationCreate {
  .column {
    padding: 0 3rem;
  }
  &-title {
    width: 100%;
    text-align: center;
    padding: 1rem;
  }
  &-cardNumber {
    margin-top: 0.5rem;
  }
  &-stripe {
    &-input {
      max-width: 100%;

      width: 100%;
      background-color: white;
      border-radius: 4px;
      color: #363636;
      align-items: center;

      border: 1px solid #dbdbdb;

      border-radius: 4px;
      font-size: 1rem;
      height: 2.25em;
      line-height: 1.5;
      padding-left: calc(0.625em - 1px);
      padding-right: calc(0.625em - 1px);
      padding-top: 8px;

      &:hover {
        border-color: $primary;
        box-shadow: 0 0 0 0.125em rgba(50, 115, 220, 0.25);
        outline: none;
      }
    }
  }
  &-column.column {
    padding-bottom: 0.75rem;
  }
  &-button {
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 0rem 1rem 1rem;
  }
}
</style>
