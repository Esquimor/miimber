<template>
  <TemplateDefault>
    <main class="Settings">
      <nav class="Settings-nav">
        <div class="Settings-nav-head">{{ $t("settings.title") }}</div>
        <SettingsNavItem
          :route="NAV.PROFILE"
          :label="$t('settings.profile.title')"
          @click.native="nav = NAV.PROFILE"
        />
        <!--<SettingsNavItem route="settings-account" :label="$t('settings.account.title')" />-->
        <SettingsNavItem
          :route="NAV.EMAIL"
          :label="$t('settings.email.title')"
          @click.native="nav = NAV.EMAIL"
        />
        <SettingsNavItem
          :route="NAV.SECURITY"
          :label="$t('settings.security.title')"
          @click.native="nav = NAV.SECURITY"
        />
        <SettingsNavItem
          :route="NAV.ORGANIZATION"
          :label="$t('settings.organization.title')"
          @click.native="nav = NAV.ORGANIZATION"
        />
      </nav>
      <b-select
        class="Settings-nav-tablet"
        size="is-medium"
        expanded
        v-model="nav"
      >
        <option :value="NAV.PROFILE">{{ $t("settings.profile.title") }}</option>
        <option :value="NAV.EMAIL">{{ $t("settings.email.title") }}</option>
        <option :value="NAV.SECURITY">
          {{ $t("settings.security.title") }}
        </option>
        <option :value="NAV.ORGANIZATION">
          {{ $t("settings.organization.title") }}
        </option>
      </b-select>
      <router-view class="Settings-content"></router-view>
    </main>
  </TemplateDefault>
</template>

<script>
"use strict";

import TemplateDefault from "@core/template/TemplateDefault";

import SettingsNavItem from "@settings/components/nav/SettingsNavItem";

const NAV = {
  PROFILE: "settings-profile",
  SECURITY: "settings-security",
  ORGANIZATION: "settings-organization",
  EMAIL: "settings-email"
};

export default {
  name: "Settings",
  components: {
    TemplateDefault,
    SettingsNavItem
  },
  data() {
    return {
      nav: "",
      NAV: NAV
    };
  },
  mounted() {
    this.nav = this.$route.name;
  },
  watch: {
    nav(newVal) {
      this.$router.push({ name: newVal });
    }
  }
};
</script>

<style lang="scss">
.Settings {
  display: flex;
  margin: 1rem auto;
  max-width: 800px;
  @media (max-width: $tablet) {
    flex-direction: column;
    padding: 0.3rem;
  }
  &-nav {
    display: flex;
    flex-direction: column;
    width: 200px;
    @media (max-width: $tablet) {
      display: none;
    }
    &-head {
      color: $black-ter;
      background-color: #f3f5f8;
      padding: 0.3rem;
      border: 1px solid $grey;
      border-top-left-radius: 3px;
      border-top-right-radius: 3px;
      font-size: 15px;
    }
    &-tablet {
      display: none;
      @media (max-width: $tablet) {
        display: initial;
      }
    }
  }
  &-content {
    flex-grow: 1;
  }
}
</style>
