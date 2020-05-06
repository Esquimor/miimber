<template>
  <OrganizationTemplateList
    :title="$t('organization.forum.title')"
    :loading="loading"
    @add="add"
    addLabel="organization.forum.label.add"
  >
    <div class="OrganizationForum">
      <draggable class="OrganizationForum-categories" v-model="myList">
        <OrganizationForumCategory
          v-for="category in myList"
          :key="category.id"
          :category="category"
          class="OrganizationForum-categories-category"
        />
      </draggable>
    </div>
  </OrganizationTemplateList>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

import draggable from "vuedraggable";

import OrganizationTemplateList from "@organization/templates/OrganizationTemplateList";

import OrganizationForumCategory from "@organization/components/forum/OrganizationForumCategory";

import OrganizationForumCagegoryAdd from "@organization/components/forum/OrganizationForumCategoryAdd";

export default {
  name: "OrganizationForum",
  components: {
    draggable,
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
    }),
    myList: {
      get() {
        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        return this.categories.sort((a, b) => a.position - b.position);
      },
      set(value) {
        this.loading = true;
        this.$store
          .dispatch("organization/setPositionForumCategory", value)
          .then(() => {
            this.loading = false;
          })
          .catch(() => {
            this.loading = false;
          });
      }
    }
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

<style lang="scss">
.OrganizationForum {
  &-categories {
    &-category {
      margin-bottom: 1rem;
    }
  }
}
</style>
