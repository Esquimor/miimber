<template>
  <TemplateDashboard :title="title" :loading="loading" hasNav>
    <template v-slot:header>
      <BButton
        v-if="userRegistered"
        icon-left="account-arrow-right"
        type="is-primary"
        :loading="loadingRegisterd"
        @click.native="unsubscribe"
      >{{ $t("dashboard.session.label.iUnsubscribe") }}</BButton>
      <BButton
        v-else
        icon-left="account-edit"
        type="is-primary"
        :loading="loadingRegisterd"
        @click.native="registerd"
      >{{ $t("dashboard.session.label.imRegistered") }}</BButton>
    </template>
    <template v-slot:nav>
      <router-link
        :to="{
          name: 'dashboard-session-information',
          params: { id: $route.params.id }
        }"
        class="TemplateDashboard-nav-link"
      >{{ $t("dashboard.session.label.information") }}</router-link>
      <!--<router-link
        :to="{
          name: 'dashboard-session-attendee',
          params: { id: $route.params.id }
        }"
        class="TemplateDashboard-nav-link"
        >{{ $t("dashboard.session.label.attendee") }}</router-link
      >-->
      <router-link
        :to="{
          name: 'dashboard-session-registered',
          params: { id: $route.params.id }
        }"
        class="TemplateDashboard-nav-link"
      >{{ $t("dashboard.session.label.registered") }}</router-link>
      <router-link
        v-if="isInsctructorOrganization"
        :to="{
          name: 'dashboard-session-emerge',
          params: { id: $route.params.id }
        }"
        class="TemplateDashboard-nav-link"
      >{{ $t("dashboard.session.label.emerge") }}</router-link>
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
      loading: true,
      loadingRegisterd: false
    };
  },
  computed: {
    ...mapGetters({
      session: "dashboard/session",
      isInsctructorOrganization: "dashboard/isInsctructorOrganization",
      userRegistered: "dashboard/userRegistered",
      getUserForSession: "dashboard/getUserForSession"
    }),
    title() {
      return this.loading ? "" : this.session.title;
    }
  },
  methods: {
    registerd() {
      this.loadingRegisterd = true;
      this.$store.dispatch("dashboard/registered", this.session.id).then(() => {
        this.loadingRegisterd = false;
      });
    },
    unsubscribe() {
      this.loadingRegisterd = true;
      this.$store
        .dispatch("dashboard/unsubscribe", this.getUserForSession.registered.id)
        .then(() => {
          this.loadingRegisterd = false;
        });
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
