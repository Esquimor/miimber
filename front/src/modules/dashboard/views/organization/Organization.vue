<template>
  <TemplateDefault>
    <div v-if="loading === false">
      <header class="DashboardOrganization-header">
        <div class="DashboardOrganization-header-wrapper">
          <h1 class="DashboardOrganization-header-title title is-3">
            {{ organization.name }}
          </h1>
          <b-tag
            v-if="isOrganizationArchive"
            type="is-danger"
            class="DashboardOrganization-header-tag"
            >{{ $t("dashboard.organization.archive.tag") }}</b-tag
          >
          <b-tag
            v-if="isOrganizationSuspended"
            type="is-warning"
            class="DashboardOrganization-header-tag"
            >{{ $t("dashboard.organization.suspended.tag") }}</b-tag
          >
        </div>
      </header>
      <nav class="DashboardOrganization-nav">
        <router-link
          v-if="isOrganizationActive"
          class="DashboardOrganization-nav-item"
          :to="{
            name: 'dashboard-organization-sessions',
            params: { id: organization.id }
          }"
          >{{ $t("dashboard.organization.label.sessions") }}</router-link
        >
        <router-link
          v-if="isMemberOrganization && isOrganizationActive"
          class="DashboardOrganization-nav-item"
          :to="{
            name: 'dashboard-organization-forum',
            params: { id: organization.id }
          }"
          >{{ $t("dashboard.organization.label.forum") }}</router-link
        >
        <router-link
          v-if="!isOrganizationArchive"
          class="DashboardOrganization-nav-item"
          :to="{
            name: 'dashboard-organization-members',
            params: { id: organization.id }
          }"
          >{{ $t("dashboard.organization.label.members") }}</router-link
        >
      </nav>
      <main class="DashboardOrganization-main">
        <div class="DashboardOrganization-main-wrapper">
          <router-view></router-view>
        </div>
      </main>
    </div>
  </TemplateDefault>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

import TemplateDefault from "@core/template/TemplateDefault";

export default {
  name: "DashboardOrganization",
  components: {
    TemplateDefault
  },
  data() {
    return {
      loadingComponent: null,
      loading: true
    };
  },
  computed: {
    ...mapGetters({
      organization: "dashboard/organization",
      canChangeOrganization: "dashboard/canChangeOrganization",
      isMemberOrganization: "dashboard/isMemberOrganization",
      isOrganizationActive: "dashboard/isOrganizationActive",
      isOrganizationArchive: "dashboard/isOrganizationArchive",
      isOrganizationSuspended: "dashboard/isOrganizationSuspended"
    }),
    title() {
      return this.loading ? "" : this.organization.name;
    }
  },
  methods: {
    startLoading() {
      this.loading = true;
      this.loadingComponent = this.$buefy.loading.open({});
    },
    endLoading() {
      if (this.loadingComponent) {
        this.loadingComponent.close();
        this.loadingComponent = null;
        this.loading = false;
      }
    }
  },
  mounted() {
    this.loading = true;
    this.$nextTick(() => {
      this.startLoading();
      this.$store
        .dispatch("dashboard/setOrganization", this.$route.params.id)
        .then(() => {
          this.endLoading();
        })
        .catch(() => {
          this.endLoading();
          this.loading = false;
        });
    });
  }
};
</script>

<style lang="scss">
.DashboardOrganization {
  &-header {
    padding: 1rem 0.5rem 1rem;
    box-shadow: 0 1px 0 $grey-lightest;
    &-wrapper {
      display: flex;
      align-items: flex-end;
      width: 100%;
      max-width: 960px;
      margin: 0 auto;
    }
    &-title {
      margin-bottom: 0px !important;
    }
    &-tag {
      margin-left: 0.5rem;
    }
  }
  &-nav {
    padding: 0.5rem;
    width: 100%;
    max-width: 960px;
    margin: 0 auto;
    &-item {
      padding: 0.5rem;
      background-color: $white;
      border-bottom: 2px solid $white;
      color: $black;
      &.router-link-active {
        border-bottom: 2px solid $primary;
      }
    }
  }
  &-main {
    min-height: 100vh;
    background-color: $white-bis;
    &-wrapper {
      display: flex;
      width: 100%;
      max-width: 960px;
      padding: 2rem 0 2rem;
      margin: 0 auto;
    }
  }
}
</style>
