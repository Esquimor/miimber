<template>
  <div class="OrganizationSettings">
    <div v-if="!organization.isFree" class="OrganizationSettings-stripe">
      <h2 class="title is-5">{{ $t("organization.settings.stripe.title") }}</h2>
      <b-notification
        v-if="!organization.isFree && !organization.isPayed"
        type="is-warning"
        aria-close-label="Close notification"
        role="alert"
        class="OrganizationSettings-stripe-notification"
        >{{ $t("organization.settings.stripe.invalid") }}</b-notification
      >
      <h3 class="subtitle is-6 OrganizationSettings-stripe-description">
        {{ $t("organization.settings.stripe.description") }}
      </h3>
      <div>
        <BButton
          type="is-warning"
          icon-left="credit-card"
          @click="stripeOrganization"
          >{{ $t("organization.settings.stripe.button") }}</BButton
        >
      </div>
    </div>
    <div class="OrganizationSettings-delete">
      <h2 class="title is-5">{{ $t("organization.settings.delete.title") }}</h2>
      <h3 class="subtitle is-6 OrganizationSettings-delete-description">
        {{ $t("organization.settings.delete.description") }}
      </h3>
      <div>
        <BButton
          type="is-danger"
          icon-left="delete"
          @click="deleteOrganization"
          >{{ $t("organization.settings.delete.button") }}</BButton
        >
      </div>
    </div>
  </div>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

import OrganizationSettingsEditStripe from "@organization/components/settings/OrganizationSettingsEditStripe";

export default {
  name: "OrganizationSettings",
  computed: {
    ...mapGetters({
      organization: "organization/organization"
    })
  },
  methods: {
    stripeOrganization() {
      this.$store.dispatch("core/openSideBar", {
        component: OrganizationSettingsEditStripe
      });
    },
    deleteOrganization() {
      this.$buefy.dialog.confirm({
        title: this.$t("organization.settings.archive.title"),
        message: this.$t("organization.settings.archive.description"),
        confirmText: this.$t("core.utils.confirm"),
        cancelText: this.$t("core.utils.cancel"),
        type: "is-danger",
        hasIcon: true,
        onConfirm: () => {
          this.$store.dispatch("organization/archiveOrganization").then(() => {
            this.$router.push({ name: "settings-organization" });
            this.$buefy.toast.open({
              message: this.$t("organization.settings.archive.success"),
              type: "is-success"
            });
          });
        }
      });
    }
  }
};
</script>

<style lang="scss">
.OrganizationSettings {
  &-stripe {
    margin-bottom: 2rem;
    &-description {
      margin-bottom: 0.5rem !important;
    }
    &-notification {
      width: 100%;
      max-width: 450px;
    }
  }
  &-delete {
    display: flex;
    flex-direction: column;
    &-description {
      margin-bottom: 0.5rem !important;
    }
  }
}
</style>
