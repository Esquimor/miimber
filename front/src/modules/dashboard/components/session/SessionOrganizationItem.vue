<template>
  <router-link
    class="DashboardSessionOrganizationItem"
    :to="{
        name: 'dashboard-organization-sessions',
        params: { id: organization.id }
      }"
  >
    <img :src="image" class="DashboardSessionOrganizationItem-image" />
    <span
      class="DashboardSessionOrganizationItem-info is-size-6 has-text-weight-semibold"
    >{{ organization.name }}</span>
  </router-link>
</template>

<script>
"use strict";

export default {
  name: "DashboardSessionOrganizationItem",
  props: {
    organization: {
      type: Object,
      required: true
    }
  },
  computed: {
    image() {
      return this.organization.image
        ? this.organization.image
        : require("@/assets/imagePlaceholder.png");
    }
  }
};
</script>

<style lang="scss">
.DashboardSessionOrganizationItem {
  display: flex;
  width: 280px;
  padding: 0.5rem;
  margin: 0.5rem 0.4rem;
  background-color: $white;
  border-radius: 5px;
  &-image {
    max-width: 50px;
    border-radius: 5px;
  }
  &-info {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    align-items: flex-start;
    padding-left: 0.6rem;
    color: $black-ter;
    text-overflow: ellipsis;
  }
}
</style>