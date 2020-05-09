<template>
  <router-link
    class="DashboardSideSessionOrganizationItem"
    :to="{
      name: 'dashboard-organization-sessions',
      params: { id: organization.id }
    }"
  >
    <img :src="image" class="DashboardSideSessionOrganizationItem-image" />
    <span
      class="DashboardSideSessionOrganizationItem-info is-size-6 has-text-weight-semibold"
      >{{ organization.name }}</span
    >
  </router-link>
</template>

<script>
"use strict";

export default {
  name: "DashboardSideSessionOrganizationItem",
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
.DashboardSideSessionOrganizationItem {
  display: flex;
  width: 280px;
  padding: 0.5rem;
  margin: 0.5rem 0.4rem;
  background-color: $white;
  border-radius: 5px;
  box-shadow: 0 3px 4px 0 $grey;
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
