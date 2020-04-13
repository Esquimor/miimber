<template>
  <div class="TemplateDefault">
    <header class="TemplateDefault-header">
      <nav class="TemplateDefault-header-nav">
        <router-link :to="{ name: 'home' }" exact class="TemplateDefault-header-nav-home">
          <img src="~@/assets/logo.svg" class="TemplateDefault-header-nav-home-logo" />
          <span>Me'ember</span>
        </router-link>
        <router-link v-if="isConnected" :to="{ name: 'dashboard-sessions' }">
          <span>{{ $t("core.menu.sessions") }}</span>
        </router-link>
        <router-link v-if="isConnected" :to="{ name: 'dashboard-organizations' }">
          <span>{{ $t("core.menu.organizations") }}</span>
        </router-link>
        <div class="TemplateDefault-header-nav-separator" />
        <router-link v-if="isConnected" :to="{ name: 'settings-profile' }">
          <span>{{ $t("core.menu.account") }}</span>
        </router-link>
        <router-link
          v-if="!isConnected"
          :to="{ name: 'register' }"
          class="TemplateDefault-header-nav-button button is-primary is-outlined"
        >
          <span>{{ $t("core.menu.register") }}</span>
        </router-link>
        <router-link
          v-if="!isConnected"
          :to="{ name: 'login' }"
          class="TemplateDefault-header-nav-button button is-primary"
        >
          <span>{{ $t("core.menu.login") }}</span>
        </router-link>
      </nav>
      <nav class="TemplateDefault-header-nav-mobile">
        <div
          class="TemplateDefault-header-nav-mobile-menu"
          @click="openMobileMenu = !openMobileMenu"
        >
          <BIcon icon="menu" />
          <span>Menu</span>
        </div>
        <div
          v-if="openMobileMenu"
          @click="openMobileMenu = false"
          class="TemplateDefault-header-nav-mobile-bk"
        />
        <div v-if="openMobileMenu" class="TemplateDefault-header-nav-mobile-route">
          <router-link :to="{ name: 'home' }" exact>
            <span>Accueil</span>
          </router-link>
          <router-link v-if="isConnected" :to="{ name: 'dashboard-sessions' }">
            <span>{{ $t("core.menu.sessions") }}</span>
          </router-link>
          <router-link v-if="isConnected" :to="{ name: 'dashboard-organizations' }">
            <span>{{ $t("core.menu.organizations") }}</span>
          </router-link>
          <router-link v-if="isConnected" :to="{ name: 'settings-profile' }">
            <span>{{ $t("core.menu.account") }}</span>
          </router-link>
          <router-link v-if="!isConnected" :to="{ name: 'register' }">
            <span>{{ $t("core.menu.register") }}</span>
          </router-link>
          <router-link v-if="!isConnected" :to="{ name: 'login' }">
            <span>{{ $t("core.menu.login") }}</span>
          </router-link>
        </div>
      </nav>
    </header>
    <main class="TemplateDefault-content">
      <slot />
    </main>
    <component v-if="sideBar.open" :is="sideBar.component" />
    <footer class="TemplateDefault-footer"></footer>
  </div>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

export default {
  name: "TemplateDefault",
  computed: {
    ...mapGetters({
      sideBar: "core/sideBar",
      isConnected: "core/isConnected"
    })
  },
  data() {
    return {
      openMobileMenu: false
    };
  }
};
</script>

<style lang="scss">
.TemplateDefault {
  &-header {
    position: sticky;
    top: 0px;
    width: 100%;
    background-color: $white;
    box-shadow: 0 1px 4px 0 $grey;
    z-index: 100;
    &-nav {
      display: flex;
      align-items: center;
      color: $black-ter;
      width: 100%;
      @media (max-width: $mobile) {
        display: none;
      }
      &-mobile {
        display: none;
        position: relative;
        @media (max-width: $mobile) {
          display: initial;
        }
        &-menu {
          cursor: pointer;
          display: flex;
          justify-content: flex-start;
          align-items: center;
          padding: 0.5rem;
          > span {
            margin-left: 0.25rem;
          }
        }
        &-bk {
          cursor: pointer;
          position: fixed;
          background-color: $grey;
          opacity: 0.5;
          top: 40px;
          left: 0px;
          width: 100%;
          height: 100%;
        }
        &-route {
          display: flex;
          flex-direction: column;
          align-items: flex-start;
          position: fixed;
          background-color: $white;
          top: 40px;
          left: 0px;
          min-width: 200px;
          z-index: 200;
          > a {
            padding: 0.75rem;
            color: $grey-darker;
            width: 100%;
            &.router-link-active {
              background-color: $primary;
              color: $white;
            }
          }
        }
      }
      &-home {
        &-logo {
          width: 25px;
          height: 25px;
          margin-right: 0.25rem;
        }
      }
      &-separator {
        flex-grow: 1;
      }
      &-button {
        margin: 0 0.5rem 0 0;
      }
      > a {
        display: flex;
        align-items: center;
        padding: 0.5rem;
        color: $black-ter;
        &:hover {
          background-color: $white-ter;
        }
        &.router-link-active:not(.TemplateDefault-header-nav-home) {
          background-color: $primary;
          color: $white;
        }
      }
    }
  }
}
</style>
