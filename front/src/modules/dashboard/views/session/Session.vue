<template>
  <TemplateDashboard :title="title" :loading="loading" hasNav>
    <template v-slot:nav>
      <router-link
        :to="{
          name: 'dashboard-session-information',
          params: { id: $route.params.id }
        }"
        class="TemplateDashboard-nav-link"
        >{{ $t("dashboard.session.label.information") }}</router-link
      >
      <router-link
        :to="{
          name: 'dashboard-session-attendee',
          params: { id: $route.params.id }
        }"
        class="TemplateDashboard-nav-link"
        >{{ $t("dashboard.session.label.attendee") }}</router-link
      >
      <router-link
        v-if="isInsctructorOrganization"
        :to="{
          name: 'dashboard-session-emerge',
          params: { id: $route.params.id }
        }"
        class="TemplateDashboard-nav-link"
        >{{ $t("dashboard.session.label.emerge") }}</router-link
      >
    </template>
    <router-view></router-view>
  </TemplateDashboard>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

import TemplateDashboard from "@dashboard/template/TemplateDashboard";

export default {
  name: "DashboardSession",
  components: {
    TemplateDashboard
  },
  data() {
    return {
      loading: true
    };
  },
  computed: {
    ...mapGetters({
      session: "dashboard/session",
      isInsctructorOrganization: "dashboard/isInsctructorOrganization"
    }),
    title() {
      return this.loading ? "" : this.session.title;
    }
  },
  mounted() {
    this.$store
      .dispatch("dashboard/setSession", this.$route.params.id)
      .then(() => {
        this.loading = false;
      });
  }
};
</script>

<style lang="scss"></style>
