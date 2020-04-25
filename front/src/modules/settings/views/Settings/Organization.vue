<template>
  <TemplateSettings :title="$t('settings.organization.title')">
    <div v-if="!loading" class="SettingsOrganization-list">
      <h2 class="subtitle is-5">{{ $t("settings.organization.listTitle") }}</h2>
      <div id="SettingsOrganization-list" v-if="organizations.length > 0">
        <OrganizationItem
          v-for="organization in organizations"
          :key="organization.key"
          :organization="organization"
        />
      </div>
      <div v-else class="SettingsOrganization-list-empty">
        <span>{{ $t("settings.organization.listEmpty") }}</span>
      </div>
    </div>
    <div class="SettingsOrganization-create">
      <h2 class="subtitle is-5">{{ $t("settings.organization.create") }}</h2>
      <div class="SettingsOrganization-create-button">
        <router-link
          class="button is-primary"
          :to="{ name: 'settings-organization-create' }"
          >{{ $t("settings.organization.button") }}</router-link
        >
      </div>
    </div>
  </TemplateSettings>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

import TemplateSettings from "@settings/template/TemplateSettings";

import OrganizationItem from "@settings/components/organization/OrganizationItem";

export default {
  name: "Organization",
  components: {
    TemplateSettings,
    OrganizationItem
  },
  data() {
    return {
      loading: true
    };
  },
  methods: {
    create() {}
  },
  computed: {
    ...mapGetters({
      organizations: "settings/organizations"
    })
  },
  mounted() {
    const loadingComponent = this.$buefy.loading.open();
    this.$store
      .dispatch("settings/getOrganizationOwnered")
      .then(() => {
        loadingComponent.close();
        this.loading = false;
      })
      .catch(() => {
        loadingComponent.close();
        this.loading = false;
      });
  }
};
</script>

<style lang="scss">
.SettingsOrganization {
  &-list {
    overflow: auto;
    max-width: 600px;
    &-item {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 0.75rem 0.5rem;
      &:nth-child(even) {
        background-color: $grey-lightest;
      }
      &-name {
        font-weight: 700;
      }
    }
    &-empty {
      display: flex;
      justify-content: center;
      align-items: center;
    }
  }
  &-create {
    margin-top: 1rem;
    &-button {
      display: flex;
      justify-content: flex-start;
      align-items: center;
    }
  }
}
</style>
