<template>
  <router-link
    class="DashboardOrganizationItem"
    :to="{
      name: 'dashboard-organization-sessions',
      params: { id: organization.id }
    }"
  >
    <img :src="image" class="DashboardOrganizationItem-image" />
    <div class="DashboardOrganizationItem-info">
      <span
        class="DashboardOrganizationItem-info-name is-size-6 has-text-weight-semibold"
        >{{ organization.name }}</span
      >
      <span>{{ $t(`core.role.${organization.user.role}`) }}</span>
    </div>
  </router-link>
</template>

<script>
"use strict";

export default {
  name: "DashboardOrganizationItem",
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
.DashboardOrganizationItem {
  display: flex;
  width: 280px;
  padding: 0.5rem 0;
  margin: 0.5rem 0.4rem;
  border-bottom: 1px solid $grey-lightest;
  &:hover {
    background-color: $white-bis;
  }
  &-image {
    max-width: 50px;
  }
  &-info {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    align-items: flex-start;
    padding-left: 0.5rem;
    color: $black-ter;
    &-name {
      text-overflow: ellipsis;
    }
  }
}
</style>
