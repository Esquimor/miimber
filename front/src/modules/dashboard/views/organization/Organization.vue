<template>
  <TemplateDashboard title="Organization" :loading="loading" hasNav>
    <template v-slot:nav>
      <router-link
        :to="{name: 'dashboard-organization-sessions', params:{id: $route.params.id}}"
        class="TemplateDashboard-nav-link"
      >Sessions</router-link>
      <router-link
        :to="{name: 'dashboard-organization-members', params:{id: $route.params.id}}"
        class="TemplateDashboard-nav-link"
      >Membres</router-link>
    </template>
    <router-view></router-view>
  </TemplateDashboard>
</template>

<script>
"use strict";

import TemplateDashboard from "@dashboard/template/TemplateDashboard";

export default {
  name: "DashboardOrganization",
  components: {
    TemplateDashboard
  },
  data() {
    return {
      loading: true
    };
  },
  mounted() {
    this.$store
      .dispatch("dashboard/setOrganization", this.$route.params.id)
      .then(() => {
        this.loading = false;
      });
  }
};
</script>

<style lang="scss">
.DashboardOrganization {
  &-link {
  }
}
</style>