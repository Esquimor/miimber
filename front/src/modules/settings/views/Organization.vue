<template>
  <TemplateSettings :title="$t('settings.organization.title')" ref="container">
    <div class="SettingsOrganization-list">
      <h2 class="subtitle is-5">{{ $t('settings.organization.listTitle') }}</h2>
      <template v-if="organizations.length >0">
        <div
          v-for="organization in organizations"
          :key="organization.key"
          class="SettingsOrganizaorganizationtion-list-item"
        >{{ organization }}</div>
      </template>
      <div class="SettingsOrganization-list-empty">
        <span>{{ $t('settings.organization.listEmpty')}}</span>
      </div>
    </div>
    <div class="SettingsOrganization-add"></div>
  </TemplateSettings>
</template>


<script>
"use strict";

import { mapGetters } from "vuex";

import TemplateSettings from "@settings/template/TemplateSettings";

export default {
  name: "Organization",
  components: {
    TemplateSettings
  },
  computed: {
    ...mapGetters({
      organizations: "settings/organizations"
    })
  },
  mounted() {
    const loadingComponent = this.$buefy.loading.open({
      container: this.$refs.container.$el
    });
    this.$store.dispatch("settings/getOrganizationOwnered").then(() => {
      loadingComponent.close();
    });
  }
};
</script>

<style lang="scss">
.SettingsOrganization {
  &-list {
    overflow: auto;
    max-width: 600px;
    &-empty {
      display: flex;
      justify-content: center;
      align-items: center;
    }
  }
}
</style>