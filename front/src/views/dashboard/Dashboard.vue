<template>
  <div class="Dashboard">
    <header class="Dashboard-header">
      <router-link :to="{ name: 'dashboard-home' }">
        <BIcon icon="home" />
        <span>{{ $t('dashboard.menu.menu') }}</span>
      </router-link>
      <div class="Dashboard-header-separator" />
      <router-link :to="{ name: 'dashboard-home' }">
        <BIcon icon="account" />
        <span>{{ $t('dashboard.menu.account') }}</span>
      </router-link>
    </header>
    <main class="Dashboard-main">
      <div class="Dashboard-main-panel">
        <DashboardPanelItem route="dashboard-session" :label="$t('dashboard.panel.session')" />
      </div>
      <div class="Dashboard-main-content">
        <router-view></router-view>
      </div>
    </main>
  </div>
</template>

<script>
"use strict";

import DashboardPanelItem from "@/components/dashboard/panel/DashboardPanelItem";

export default {
  name: "Dashboard",
  components: {
    DashboardPanelItem
  }
};
</script>

<style lang="scss">
.Dashboard {
  &-header {
    display: flex;
    justify-content: space-around;
    background-color: $background-dashboard;
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
  &-main {
    display: flex;
    width: 100vw;
    height: 100vh;
    &-panel {
      display: flex;
      flex-direction: column;
      width: 20vw;
      background-color: $grey-lightest;
    }
    &-content {
      width: 80vw;
      padding: 1rem;
    }
  }
}
</style>