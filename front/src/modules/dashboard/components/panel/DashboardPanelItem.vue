<template>
  <router-link class="DashboardPanelItem" :to="{ name: route }">{{ label }}</router-link>
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
    }
  }
};
</script>

<style lang="scss">
.DashboardPanelItem {
  padding: 0.5rem;
  border-left: 5px solid transparent;
  color: $black;
  &:hover {
    background-color: $grey-lighter;
  }
  &.router-link-active {
    border-left-color: $primary;
  }
}
</style>