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
      categories: "dashboard/organizationsCategoriesForum"
    })
  },
  mounted() {
    this.$store.dispatch("dashboard/setForum").then(() => {
      this.loading = false;
    });
  }
};
</script>

<style lang="scss"></style>
