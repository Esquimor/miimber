<template>
  <div class="TemplateDefault">
    <header class="TemplateDefault-header">
      <router-link :to="{ name: 'dashboard' }">
        <BIcon icon="home" />
        <span>{{ $t('dashboard.menu.menu') }}</span>
      </router-link>
      <div class="TemplateDefault-header-separator" />
      <router-link :to="{ name: 'dashboard' }">
        <BIcon icon="account" />
        <span>{{ $t('dashboard.menu.account') }}</span>
      </router-link>
    </header>
    <main class="TemplateDefault-main">
      <div class="TemplateDefault-main-panel"></div>
      <div class="TemplateDefault-main-content">
        <slot />
      </div>
    </main>
  </div>
</template>

<script>
"use strict";

export default {
  name: "TemplateDetault"
};
</script>

<style lang="scss">
.TemplateDefault {
  &-header {
    display: flex;
    justify-content: space-around;
    background-color: $background-dashboard;
    color: $white;
    &-separator {
      flex-grow: 1;
    }
    > a > * {
      color: $white;
      &:hover {
        color: $white-ter;
      }
    }
  }
  &-main {
    display: flex;
    width: 100vw;
    height: 100vh;
    &-panel {
      width: 20vw;
      background-color: $black-ter;
    }
    &-content {
      width: 80vw;
    }
  }
}
</style>
