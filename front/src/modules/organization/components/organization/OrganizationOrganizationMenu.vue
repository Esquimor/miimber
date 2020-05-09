<template>
  <div class="OrganizationOrganizationMenu">
    <router-link
      v-if="isPayed"
      :to="{ name: NAV.MEMBERS }"
      class="OrganizationOrganizationMenu-link"
      @click.native="setRouterAndNav(ROUTER.MEMBERS, NAV.MEMBERS)"
    >
      <BIcon
        size="is-small"
        icon="account-group"
        class="OrganizationOrganizationMenu-link-icon"
      />
      <span>{{ $t("organization.members.title") }}</span>
    </router-link>
    <router-link
      v-if="isPayed"
      :to="{ name: NAV.SESSIONS }"
      class="OrganizationOrganizationMenu-link"
      @click.native="setRouterAndNav(ROUTER.SESSIONS, NAV.SESSIONS)"
    >
      <BIcon
        size="is-small"
        icon="calendar-multiple"
        class="OrganizationOrganizationMenu-link-icon"
      />
      <span>{{ $t("organization.sessions.title") }}</span>
    </router-link>
    <div
      v-if="router === ROUTER.SESSIONS && isPayed"
      class="OrganizationOrganizationMenu-sublink"
    >
      <router-link
        :to="{ name: NAV.SESSIONS_TYPES }"
        class="OrganizationOrganizationMenu-sublink-item"
        @click.native="setRouterAndNav(ROUTER.SESSIONS, NAV.SESSIONS_TYPES)"
      >
        <span>{{ $t("organization.typeSessions.title") }}</span>
      </router-link>
    </div>
    <router-link
      v-if="isPayed"
      :to="{ name: NAV.FORUM }"
      class="OrganizationOrganizationMenu-link"
      @click.native="setRouterAndNav(ROUTER.FORUM, NAV.FORUM)"
    >
      <BIcon
        size="is-small"
        icon="forum"
        class="OrganizationOrganizationMenu-link-icon"
      />
      <span>{{ $t("organization.forum.title") }}</span>
    </router-link>
    <router-link
      :to="{ name: NAV.SETTINGS }"
      class="OrganizationOrganizationMenu-link"
      @click.native="setRouterAndNav(ROUTER.SETTINGS, NAV.SETTINGS)"
    >
      <BIcon
        size="is-small"
        icon="settings"
        class="OrganizationOrganizationMenu-link-icon"
      />
      <span>{{ $t("organization.settings.title") }}</span>
    </router-link>
    <BSelect
      class="OrganizationOrganizationMenu-tablet"
      placeholder="Medium"
      size="is-medium"
      expanded
      v-model="nav"
    >
      <option v-if="isPayed" :value="NAV.MEMBERS">{{
        $t("organization.members.title")
      }}</option>
      <option v-if="isPayed" :value="NAV.SESSIONS">{{
        $t("organization.sessions.title")
      }}</option>
      <option v-if="isPayed" :value="NAV.SESSIONS_TYPES">
        {{ $t("organization.sessions.title") }} -
        {{ $t("organization.typeSessions.title") }}
      </option>
      <option v-if="isPayed" :value="NAV.FORUM">{{
        $t("organization.forum.title")
      }}</option>
      <option :value="NAV.SETTINGS">{{
        $t("organization.settings.title")
      }}</option>
    </BSelect>
  </div>
</template>

<script>
"use strict";

const ROUTER = {
  MEMBERS: "MEMBERS",
  SESSIONS: "SESSIONS",
  SETTINGS: "SETTINGS",
  FORUM: "FORUM",
  NOT_FOUND: "NOT_FOUND"
};

const NAV = {
  MEMBERS: "organization-manage-members",
  SESSIONS: "organization-manage-sessions",
  SESSIONS_TYPES: "organization-manage-sessions-types",
  SETTINGS: "organization-manage-settings",
  FORUM: "organization-manage-forum"
};

import { mapGetters } from "vuex";

export default {
  name: "OrganizationOrganizationMenu",
  data() {
    return {
      ROUTER: ROUTER,
      router: ROUTER.MEMBERS,
      nav: "",
      NAV: NAV
    };
  },
  computed: {
    ...mapGetters({
      organization: "organization/organization"
    }),
    isPayed() {
      return this.organization.isPayed || this.organization.isFree;
    }
  },
  methods: {
    setRouterAndNav(router, nav) {
      this.router = router;
      this.nav = nav;
    }
  },
  mounted() {
    this.nav = this.$route.name;
    switch (this.$route.name) {
      case "organization-manage-members":
        this.router = ROUTER.MEMBERS;
        break;
      case NAV.SESSIONS:
      case "organization-manage-sessions-types":
        this.router = ROUTER.SESSIONS;
        break;

      case NAV.SETTINGS:
        this.router = ROUTER.SETTINGS;
        break;

      default:
        this.router = ROUTER.MEMBERS;
        break;
    }
  },
  watch: {
    nav(newVal) {
      this.$router.push({ name: newVal });
    }
  }
};
</script>

<style lang="scss">
.OrganizationOrganizationMenu {
  display: flex;
  flex-direction: column;
  max-width: 170px;
  width: 20%;
  @media (max-width: $tablet) {
    width: 100%;
    max-width: 100%;
  }
  &-link {
    display: flex;
    justify-content: flex-start;
    align-items: center;
    padding: 0.5rem;
    background-color: $white-bis;
    color: $black-ter;
    &.router-link-active {
      background-color: $primary;
      color: $white;
    }
    @media (max-width: $tablet) {
      display: none;
    }
    &-icon {
      margin: 0 0.5rem;
    }
  }
  &-sublink {
    display: flex;
    flex-direction: column;
    padding: 0.5rem 0rem 0.5rem 1rem;
    @media (max-width: $tablet) {
      display: none;
    }
    &-item {
      border-left: 2px solid $grey;
      padding: 0.2rem 0rem 0.2rem 0.5rem;
      &.router-link-active {
        border-color: $primary;
      }
    }
  }
  &-tablet {
    display: none;
    padding: 0 1rem;
    @media (max-width: $tablet) {
      display: initial;
    }
  }
}
</style>
