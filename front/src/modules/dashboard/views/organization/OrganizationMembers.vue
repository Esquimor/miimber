<template>
  <TemplateOrganization :loading="loading">
    <div class="DashboardOrganizationMembers">
      <SessionUserItem
        v-for="member in members"
        :key="member.id"
        :user="member"
      />
    </div>
  </TemplateOrganization>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

import TemplateOrganization from "@dashboard/template/TemplateOrganization";

import SessionUserItem from "@dashboard/components/session/SessionUserItem";

export default {
  name: "DashboardOrganizationMembers",
  components: {
    TemplateOrganization,
    SessionUserItem
  },
  data() {
    return {
      loading: true
    };
  },
  computed: {
    ...mapGetters({
      members: "dashboard/organizationMembers",
      isOrganizationArchived: "dashboard/isOrganizationArchived",
      isOrganizationSuspended: "dashboard/isOrganizationSuspended"
    })
  },
  mounted() {
    if (this.isOrganizationArchived) {
      this.$router.push({
        name: "dashboard-organization-archived",
        params: { id: this.$route.params.id }
      });
      this.loading = false;
      return;
    }
    this.$store
      .dispatch("dashboard/setOrganizationMembers", this.$route.params.id)
      .then(() => {
        this.loading = false;
      })
      .catch(() => {
        this.loading = false;
      });
  }
};
</script>

<style lang="scss">
.DashboardOrganizationMembers {
  display: flex;
  flex-wrap: wrap;
}
</style>
