<template>
  <TemplateOrganization :loading="loading">
    <div class="DashboardOrganizationForum">
      <OrganizationForumCategory
        v-for="category in categories"
        :key="category.id"
        :category="category"
      />
    </div>
  </TemplateOrganization>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

import TemplateOrganization from "@dashboard/template/TemplateOrganization";

import OrganizationForumCategory from "@dashboard/components/organization/forum/OrganizationForumCategory";

export default {
  name: "DashboardOrganizationForum",
  components: {
    TemplateOrganization,
    OrganizationForumCategory
  },
  data() {
    return {
      loading: true
    };
  },
  computed: {
    ...mapGetters({
      categories: "dashboard/organizationsCategoriesForum",
      isMemberOrganization: "dashboard/isMemberOrganization",
      isOrganizationArchived: "dashboard/isOrganizationArchived",
      isOrganizationSuspended: "dashboard/isOrganizationSuspended"
    })
  },
  mounted() {
    if (!this.isMemberOrganization) {
      this.$router.push({ name: "error_404" });
      this.loading = false;
      return;
    }
    if (this.isOrganizationArchived) {
      this.$router.push({
        name: "dashboard-organization-archived",
        params: { id: this.$route.params.id }
      });
      this.loading = false;
      return;
    }
    if (this.isOrganizationSuspended) {
      this.$router.push({
        name: "dashboard-organization-suspended",
        params: { id: this.$route.params.id }
      });
      this.loading = false;
      return;
    }
    this.$store.dispatch("dashboard/setForum").then(() => {
      this.loading = false;
    });
  }
};
</script>

<style lang="scss"></style>
