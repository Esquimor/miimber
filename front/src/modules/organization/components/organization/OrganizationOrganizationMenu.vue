<template>
  <div class="OrganizationOrganizationMenu">
    <router-link
      :to="{ name: 'organization-members' }"
      class="OrganizationOrganizationMenu-link"
      @click.native="router = ROUTER.MEMBERS"
    >
      <BIcon size="is-small" icon="account-group" class="OrganizationOrganizationMenu-link-icon" />
      <span>{{ $t('organization.members.title') }}</span>
    </router-link>
    <router-link
      :to="{ name: 'organization-sessions' }"
      class="OrganizationOrganizationMenu-link"
      @click.native="router = ROUTER.SESSIONS"
    >
      <BIcon
        size="is-small"
        icon="calendar-multiple"
        class="OrganizationOrganizationMenu-link-icon"
      />
      <span>{{ $t('organization.sessions.title') }}</span>
    </router-link>
    <div v-if="router === ROUTER.SESSIONS" class="OrganizationOrganizationMenu-sublink">
      <router-link
        :to="{ name: 'organization-sessions-types' }"
        class="OrganizationOrganizationMenu-sublink-item"
        @click.native="router = ROUTER.SESSIONS"
      >
        <span>{{ $t('organization.typeSessions.title') }}</span>
      </router-link>
    </div>
    <router-link
      :to="{ name: 'organization-settings' }"
      class="OrganizationOrganizationMenu-link"
      @click.native="router = ROUTER.SETTINGS"
    >
      <BIcon size="is-small" icon="settings" class="OrganizationOrganizationMenu-link-icon" />
      <span>{{ $t('organization.settings.title') }}</span>
    </router-link>
  </div>
</template>

<script>
"use strict";

const ROUTER = {
  MEMBERS: "MEMBERS",
  SESSIONS: "SESSIONS",
  SETTINGS: "SETTINGS",
  NOT_FOUND: "NOT_FOUND"
};

export default {
  name: "OrganizationOrganizationMenu",
  data() {
    return {
      ROUTER: ROUTER,
      router: ROUTER.MEMBERS
    };
  },
  mounted() {
    switch (this.$router.history.current.name) {
      case "organization-members":
        this.router = ROUTER.MEMBERS;
        break;
      case "organization-sessions":
      case "organization-sessions-types":
        this.router = ROUTER.SESSIONS;
        break;

      case "organization-settings":
        this.router = ROUTER.SETTINGS;
        break;

      default:
        this.router = ROUTER.MEMBERS;
        break;
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
    &-icon {
      margin: 0 0.5rem;
    }
  }
  &-sublink {
    display: flex;
    flex-direction: column;
    padding: 0.5rem 0rem 0.5rem 1rem;
    &-item {
      border-left: 2px solid $grey;
      padding: 0.2rem 0rem 0.2rem 0.5rem;
      &.router-link-active {
        border-color: $primary;
      }
    }
  }
}
</style>