<template>
  <div class="TemplateDefault">
    <header class="TemplateDefault-header">
      <nav class="TemplateDefault-header-nav">
        <router-link :to="{ name: 'dashboard-home' }" exact>
          <span>{{ $t("core.menu.home") }}</span>
        </router-link>
        <router-link :to="{ name: 'dashboard-sessions' }">
          <span>{{ $t("core.menu.sessions") }}</span>
        </router-link>
        <router-link :to="{ name: 'dashboard-organizations' }">
          <span>{{ $t("core.menu.organizations") }}</span>
        </router-link>
        <div class="TemplateDefault-header-nav-separator" />
        <router-link :to="{ name: 'settings-profile' }">
          <BIcon icon="account" />
          <span>{{ $t("core.menu.account") }}</span>
        </router-link>
      </nav>
    </header>
    <div class="TemplateDefault-content">
      <slot />
    </div>
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
    width: 100%;
    box-shadow: 0 1px 4px 0 $grey;
    &-nav {
      display: flex;
      align-items: center;
      color: $black-ter;
      width: 100%;
      &-separator {
        flex-grow: 1;
      }
      > a {
        display: flex;
        align-items: center;
        padding: 0.5rem;
        color: $black-ter;
        &:hover {
          background-color: $white-ter;
        }
        &.router-link-active {
          background-color: $primary;
          color: $white;
        }
      }
    }
  }
  &-content {
    margin-top: 3px;
  }
}
</style>
