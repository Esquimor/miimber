<template>
  <div class="DashboardOrganizationForumCategory">
    <div class="DashboardOrganizationForumCategory-category text is-size-5">
      {{ category.title }}
    </div>
    <div class="DashboardOrganizationForumCategory-subjects">
      <OrganizationForumSubjectItem
        v-for="subject in orderSubjects"
        :key="subject.id"
        :subject="subject"
        class="DashboardOrganizationForumCategory-subject"
      />
    </div>
  </div>
</template>

<script>
"use strict";

import OrganizationForumSubjectItem from "@dashboard/components/organization/forum/OrganizationForumSubjectItem";

export default {
  name: "DashboardOrganizationForumCategory",
  components: {
    OrganizationForumSubjectItem
  },
  props: {
    category: {
      type: Object,
      required: true
    }
  },
  computed: {
    orderSubjects() {
      // eslint-disable-next-line vue/no-side-effects-in-computed-properties
      return this.category.subjects.sort((a, b) => a.position - b.position);
    }
  }
};
</script>

<style lang="scss">
.DashboardOrganizationForumCategory {
  display: flex;
  flex-direction: column;
  border: 1px solid $grey-lighter;
  border-radius: 5px;
  margin-bottom: 1rem;
  &-category {
    padding: 0.5rem;
    background-color: $white-ter;
    font-weight: 600;
  }
  &-subject {
    cursor: pointer;
    display: flex;
    flex-direction: column;
    &:nth-child(even) {
      background-color: $white-bis;
    }
    &:hover {
      color: $primary;
    }
  }
}
</style>
