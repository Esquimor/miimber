<template>
  <TemplateDefault>
    <main class="Settings">
      <nav class="Settings-nav">
        <div class="Settings-nav-head">{{ $t("settings.title") }}</div>
        <SettingsNavItem
          route="settings-profile"
          :label="$t('settings.profile.title')"
        />
        <!--<SettingsNavItem route="settings-account" :label="$t('settings.account.title')" />-->
        <SettingsNavItem
          route="settings-security"
          :label="$t('settings.security.title')"
        />
        <SettingsNavItem
          route="settings-organization"
          :label="$t('settings.organization.title')"
        />
      </nav>
      <router-view class="Settings-content"></router-view>
    </main>
  </TemplateDefault>
</template>

<script>
"use strict";

import TemplateDefault from "@core/template/TemplateDefault";

import SettingsNavItem from "@settings/components/nav/SettingsNavItem";

export default {
  name: "Settings",
  components: {
    TemplateDefault,
    SettingsNavItem
  }
};
</script>

<style lang="scss">
.Settings {
  display: flex;
  margin: 1rem auto;
  max-width: 800px;
  &-nav {
    display: flex;
    flex-direction: column;
    width: 200px;

    &-head {
      color: $black-ter;
      background-color: #f3f5f8;
      padding: 0.3rem;
      border: 1px solid $grey;
      border-top-left-radius: 3px;
      border-top-right-radius: 3px;
      font-size: 15px;
    }
  }
  &-content {
    flex-grow: 1;
  }
}
</style>
