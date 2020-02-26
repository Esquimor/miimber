<template>
  <router-link class="DashboardPanelItem" :to="{ name: route }">
    <BIcon :icon="icon" />
    <span>{{ label }}</span>
  </router-link>
</template>

<script>
"use strict";

export default {
  name: "DashboardPanelItem",
  props: {
    route: {
      type: String,
      required: true
    },
    label: {
      type: String,
      required: true
    },
    icon: {
      type: String,
      required: true
    }
  }
};
</script>

<style lang="scss">
.DashboardPanelItem {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 0.5rem;
  color: $white;
  &:hover {
    background-color: $background-dashboard-hover;
    color: $white;
  }
  &.router-link-active {
    background-color: $white;
    color: $background-dashboard;
  }
}
</style>