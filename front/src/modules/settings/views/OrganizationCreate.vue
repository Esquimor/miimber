<template>
  <TemplateDefault class="OrganizationCreate">
    <h1 class="title is-4 OrganizationCreate-title">{{ $t('settings.organization.create') }}</h1>
    <div class="columns">
      <div class="column is-half is-offset-one-quarter">
        <b-field :label="$t('settings.organizationCreate.name')" :message="message">
          <b-input v-model="name" @focus="focusName" @blur="searchIfExit" required></b-input>
        </b-field>
      </div>
    </div>
    <div ref="card"></div>
  </TemplateDefault>
</template>

<script>
"use strict";

import api from "@/utils/api";

import TemplateDefault from "@core/template/TemplateDefault";

// eslint-disable-next-line no-undef
let stripe = Stripe(`pk_test_NOsGYAIvHj3VuuEhbzvetWgW00BUPzCdgM`),
  elements = stripe.elements(),
  card = undefined;

export default {
  name: "OrganizationCreate",
  components: {
    TemplateDefault
  },
  data() {
    return {
      activeStep: 0,
      name: "",
      valid: false,
      message: ""
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
    }
  },
  mounted: function() {
    card = elements.create("card");
    card.mount(this.$refs.card);
  }
};
</script>

<style lang="scss">
.OrganizationCreate {
  &-title {
    width: 100%;
    text-align: center;
    padding: 1rem;
  }
  &-steps {
    display: flex;
    flex-direction: column;
    margin: 1rem auto;
    max-width: 800px;
    > .step-content {
      margin-top: 1rem;
      > .step-item {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        > div {
          display: flex;
          justify-content: flex-start;
          align-items: center;
          max-width: 600px;
          padding: 1rem;
        }
      }
    }
  }
}
</style>