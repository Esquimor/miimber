<template>
  <div class="TemplateDefault">
    <header class="TemplateDefault-header">
      <router-link :to="{ name: 'dashboard-home' }">
        <BIcon icon="home" />
        <span>{{ $t("dashboard.menu.menu") }}</span>
      </router-link>
      <div class="TemplateDefault-header-separator" />
      <router-link :to="{ name: 'settings-profile' }">
        <BIcon icon="account" />
        <span>{{ $t("dashboard.menu.account") }}</span>
      </router-link>
    </header>
    <slot />
    <component v-if="sideBar.open" :is="sideBar.component" />
  </div>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

export default {
  name: "TemplateDefault",
  computed: {
    ...mapGetters({
      sideBar: "core/sideBar"
    })
  }
};
</script>

<style lang="scss">
.TemplateDefault {
  &-header {
    display: flex;
    justify-content: space-around;
    background-color: $black-ter;
    color: $white;
    &-separator {
      flex-grow: 1;
    }
    > a {
      display: flex;
      align-items: center;
      padding: 0.5rem;
      &:hover {
        background-color: $background-dashboard-hover;
      }
      > * {
        color: $white;
        &:hover {
          color: $white-ter;
        }
      }
    }
  }
}
</style>
