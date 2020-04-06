<template>
  <TemplateDashboard :loading="loading">
    <div class="DashboardOrganizations">
      <h1
        class="DashboardOrganizations-title title is-4"
      >{{$t('dashboard.organization.label.list') }}</h1>
      <div class="DashboardOrganizations-organizations">
        <OrganizationItem
          v-for="organization in organizations"
          :key="organization.id"
          :organization="organization"
        />
      </div>
    </div>
  </TemplateDashboard>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

import TemplateDashboard from "@dashboard/template/TemplateDashboard";

import OrganizationItem from "@dashboard/components/organization/OrganizationItem";

export default {
  name: "DashboardOrganizations",
  components: {
    TemplateDashboard,
    OrganizationItem
  },
  data() {
    return {
      loading: true
    };
  },
  computed: {
    ...mapGetters({
      organizations: "dashboard/organizations",
      canChangeOrganization: "core/canChangeOrganization"
    })
  },
  mounted() {
    this.$store.dispatch("dashboard/setOrganizations").then(() => {
      this.loading = false;
    });
  }
};
</script>

<style lang="scss">
.DashboardOrganizations {
  &-title {
  }
  &-organizations {
    display: flex;
    align-content: flex-start;
    flex-wrap: wrap;
  }
}
</style>
