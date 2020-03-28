<template>
  <router-link class="SettingsNavItem" :to="{ name: route }">{{
    label
  }}</router-link>
</template>

<script>
"use strict";

export default {
  name: "SettingsNavItem",
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
.SettingsNavItem {
  border-left: 1px solid $grey;
  border-right: 1px solid $grey;
  border-bottom: 1px solid $grey;
  padding: 0.3rem;
  color: $primary;
  &.router-link-active {
    border-left: 4px solid $primary;
    color: $black-ter;
    font-weight: 600;
  }
  &:not(.router-link-active) {
    padding-left: calc(0.3rem + 3px);
  }
  &:hover {
    background-color: $white-bis;
    color: $primary;
  }
}
</style>
