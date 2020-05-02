<template>
  <div class="TemplateDefault">
    <header class="TemplateDefault-header">
      <nav class="TemplateDefault-header-nav">
        <router-link
          :to="{ name: 'home' }"
          exact
          class="TemplateDefault-header-nav-home"
        >
          <img
            src="~@/assets/logo.svg"
            class="TemplateDefault-header-nav-home-logo"
          />
          <span>Miimber</span>
        </router-link>
        <router-link v-if="isConnected" :to="{ name: 'dashboard-sessions' }">
          <span>{{ $t("core.menu.sessions") }}</span>
        </router-link>
        <router-link
          v-if="isConnected"
          :to="{ name: 'dashboard-organizations' }"
        >
          <span>{{ $t("core.menu.organizations") }}</span>
        </router-link>
        <div class="TemplateDefault-header-nav-separator" />
        <b-dropdown
          v-if="isConnected"
          aria-role="list"
          position="is-bottom-left"
        >
          <div
            class="TemplateDefault-header-nav-dropdown"
            slot="trigger"
            slot-scope="{ active }"
          >
            <span>{{ $t("core.menu.account") }}</span>
            <b-icon :icon="active ? 'menu-up' : 'menu-down'"></b-icon>
          </div>

          <b-dropdown-item aria-role="listitem" @click.native="goToProfile">
            <span>{{ $t("core.menu.profile") }}</span>
          </b-dropdown-item>
          <b-dropdown-item
            aria-role="listitem"
            @click.native="goToOrganization"
          >
            <span>{{ $t("core.menu.organizations") }}</span>
          </b-dropdown-item>
          <b-dropdown-item aria-role="listitem" separator />
          <b-dropdown-item aria-role="listitem" @click.native="logout">
            <span>{{ $t("core.menu.logout") }}</span>
          </b-dropdown-item>
        </b-dropdown>
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
        <div
          v-if="openMobileMenu"
          @click="openMobileMenu = false"
          class="TemplateDefault-header-nav-mobile-route"
        >
          <router-link :to="{ name: 'home' }" exact>
            <span>Accueil</span>
          </router-link>
          <router-link v-if="isConnected" :to="{ name: 'dashboard-sessions' }">
            <span>{{ $t("core.menu.sessions") }}</span>
          </router-link>
          <router-link
            v-if="isConnected"
            :to="{ name: 'dashboard-organizations' }"
          >
            <span>{{ $t("core.menu.organizations") }}</span>
          </router-link>
          <router-link v-if="isConnected" :to="{ name: 'settings-profile' }">
            <span>{{ $t("core.menu.account") }}</span>
          </router-link>
          <span v-if="isConnected" @click="logout">{{
            $t("core.menu.logout")
          }}</span>
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
    <footer class="TemplateDefault-footer">
      <div class="columns">
        <div class="TemplateDefault-footer-list column">
          <b-dropdown
            class="TemplateDefault-footer-list-lang"
            aria-role="list"
            position="is-top-right"
          >
            <div slot="trigger" class="TemplateDefault-footer-list-element">
              <BIcon
                icon="translate"
                class="TemplateDefault-footer-list-element-icon"
              />
              <span>{{ $t("core.footer.lang") }}</span>
            </div>
            <b-dropdown-item
              v-for="lang in LANG"
              :key="lang.item"
              aria-role="listitem"
              @click.native="changeLang(lang.code)"
              >{{ $t(`core.lang.${lang.label}`) }}</b-dropdown-item
            >
          </b-dropdown>
        </div>
        <div class="TemplateDefault-footer-list column"></div>
        <div class="TemplateDefault-footer-list column"></div>
        <div class="TemplateDefault-footer-list column">
          <router-link
            :to="{ name: 'terms' }"
            class="TemplateDefault-footer-list-element"
          >
            <BIcon
              icon="file-certificate"
              class="TemplateDefault-footer-list-element-icon"
            />
            <span>{{ $t("core.footer.terms") }}</span>
          </router-link>
        </div>
      </div>
      <div class="TemplateDefault-footer-info">
        <span>Miimber - {{ dateYear }} v.{{ version }}</span>
      </div>
    </footer>
  </div>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

import { LANG } from "@/utils/consts";

import dayjs from "dayjs";

export default {
  name: "TemplateDefault",
  data() {
    return {
      openMobileMenu: false,
      LANG: LANG
    };
  },
  computed: {
    ...mapGetters({
      sideBar: "core/sideBar",
      isConnected: "core/isConnected"
    }),
    dateYear() {
      return dayjs().year();
    },
    version() {
      return process.env.VUE_APP_VERSION;
    }
  },
  methods: {
    logout() {
      this.$store.dispatch("core/logout").then(() => {
        this.$buefy.toast.open({
          message: this.$t("core.logout.success"),
          type: "is-success"
        });
        this.$router.push({ name: "home" });
      });
    },
    changeLang(lang) {
      localStorage.setItem("lang", lang);
      if (!this.isConnected) {
        document.location.reload();
        return;
      }
      this.$store
        .dispatch("core/updateLang", {
          lang
        })
        .then(() => {
          document.location.reload();
        })
        .catch(() => {
          document.location.reload();
        });
    },
    goToProfile() {
      this.$router.push({ name: "settings-profile" });
    },
    goToOrganization() {
      this.$router.push({ name: "settings-organization" });
    }
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
          > a,
          > span {
            cursor: pointer;
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
      &-dropdown {
        cursor: pointer;
        display: flex;
        align-items: center;
        padding: 0.5rem;
        color: $black-ter;
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
  &-content {
    min-height: 100vh;
  }
  &-footer {
    background-color: $black-ter;
    color: $white-ter;
    padding: 1rem 4rem;
    a {
      color: $white-ter;
      &:hover {
        color: $white-ter;
      }
    }
    &-list {
      display: flex;
      flex-direction: column;
      &-lang {
        cursor: pointer;
        a {
          color: $black-ter;
        }
      }
      &-element {
        display: flex;
        justify-content: flex-start;
        align-items: center;
        &-icon {
          margin-right: 0.5rem;
        }
      }
    }
    &-info {
      display: flex;
      justify-content: center;
    }
  }
}
</style>
