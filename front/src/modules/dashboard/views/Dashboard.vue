<template>
  <TemplateDefault>
    <main class="Dashboard-main">
      <div class="Dashboard-main-panel">
        <DashboardPanelItem
          route="dashboard-sessions"
          icon="account-group"
          :label="$t('dashboard.panel.sessions')"
        />
        <DashboardPanelItem
          route="dashboard-organizations"
          icon="calendar-multiple"
          :label="$t('dashboard.panel.organizations')"
        />
      </div>
      <div class="Dashboard-main-content">
        <router-view></router-view>
      </div>
    </main>
  </TemplateDefault>
</template>

<script>
"use strict";

import TemplateDefault from "@core/template/TemplateDefault";

import DashboardPanelItem from "@dashboard/components/panel/DashboardPanelItem";

export default {
  name: "Dashboard",
  components: {
    DashboardPanelItem,
    TemplateDefault
  }
};
</script>

<style lang="scss">
.Dashboard {
  &-main {
    display: flex;
    width: 100vw;
    min-height: 100vh;
    &-panel {
      display: flex;
      flex-direction: column;
      width: 200px;
      background-color: $background-dashboard;
    }
    &-content {
      flex-grow: 1;
      padding: 1rem;
    }
  }
}
</style>