<template>
  <OrganizationTemplateList
    :title="$t('organization.forum.title')"
    :loading="false"
    @add="add"
  >
    <div class="OrganizationForum">
      <div class="OrganizationForum-categories">
        <OrganizationForumCategory
          v-for="category in categories"
          :key="category.id"
          :category="category"
        />
      </div>
    </div>
  </OrganizationTemplateList>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

import OrganizationTemplateList from "@organization/templates/OrganizationTemplateList";

import OrganizationForumCategory from "@organization/components/forum/OrganizationForumCategory";

import OrganizationForumCagegoryAdd from "@organization/components/forum/OrganizationForumCategoryAdd";

export default {
  name: "OrganizationForum",
  components: {
    OrganizationTemplateList,
    OrganizationForumCategory
  },
  data() {
    return {
      loading: true
    };
  },
  computed: {
    ...mapGetters({
      categories: "organization/categoriesForum"
    })
  },
  methods: {
    add() {
      this.$store.dispatch("core/openSideBar", {
        component: OrganizationForumCagegoryAdd
      });
    }
  },
  mounted() {
    this.$store.dispatch("organization/setForum").then(() => {
      this.loading = false;
    });
  }
};
</script>

<style lang="scss"></style>
