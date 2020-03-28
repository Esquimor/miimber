<template>
  <TemplateDashboard title="Organization" :loading="loading">
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
</style>