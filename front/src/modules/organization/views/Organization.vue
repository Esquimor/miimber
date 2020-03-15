<template>
  <TemplateDefault>
    <div class="Organization" v-if="!loading && organization !== null">
      <header class="Organization-header">
        <div class="Organization-header-wrapper">
          <h1 class="is-size-3">{{ organization.name }}</h1>
          <BButton
            icon-left="pencil"
            type="is-primary"
            @click="edit"
            outlined
          >{{ $t('core.utils.edit') }}</BButton>
        </div>
      </header>
      <div class="Organization-content">
        <b-menu class="Organization-menu">
          <b-menu-list>
            <b-menu-item
              :label="$t('organization.members.title')"
              icon="account-group"
              tag="router-link"
              :to="{ name: 'organization-members' }"
            ></b-menu-item>
          </b-menu-list>
          <b-menu-list>
            <b-menu-item
              :label="$t('organization.sessions.title')"
              icon="calendar-multiple"
              tag="router-link"
              :to="{ name: 'organization-sessions' }"
            ></b-menu-item>
          </b-menu-list>
          <b-menu-list>
            <b-menu-item
              :label="$t('organization.settings.title')"
              icon="settings"
              tag="router-link"
              :to="{ name: 'organization-settings' }"
            ></b-menu-item>
          </b-menu-list>
        </b-menu>
        <div class="Organization-main">
          <router-view></router-view>
        </div>
      </div>
    </div>
  </TemplateDefault>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

import TemplateDefault from "@core/template/TemplateDefault";

import OrganizationOrganizationEdit from "@organization/components/organization/OrganizationOrganizationEdit";

export default {
  name: "Organization",
  components: {
    TemplateDefault
  },
  data() {
    return {
      loading: true
    };
  },
  computed: {
    ...mapGetters({
      organization: "organization/organization"
    })
  },
  methods: {
    edit() {
      this.$buefy.modal.open({
        parent: this,
        component: OrganizationOrganizationEdit,
        canCancel: false,
        props: {
          name: this.organization.name
        }
      });
    }
  },
  mounted() {
    const loadingComponent = this.$buefy.loading.open();
    this.$store
      .dispatch("organization/setOrganization", this.$route.params.id)
      .then(() => {
        loadingComponent.close();
        this.loading = false;
      });
  }
};
</script>

<style lang="scss">
.Organization {
  display: flex;
  flex-direction: column;
  &-header {
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: $primary-light;
    border-bottom: 1px solid $grey;
    &-wrapper {
      display: flex;
      justify-content: space-between;
      align-items: center;
      width: 400px;
      margin: 2rem;
    }
  }
  &-content {
    display: flex;
  }
  &-menu {
    max-width: 170px;
    width: 20%;
    &-home {
      > a {
        border-radius: 0px;
        &.router-link-active {
          background-color: $white !important;
          color: #363636 !important;
        }
        &:hover {
          background-color: whitesmoke !important;
          color: #363636 !important;
        }
        &.router-link-exact-active {
          background-color: $primary !important;
          color: $white !important;
        }
      }
    }
    > .menu-list {
      a {
        border-radius: 0px;
        &.router-link-active {
          background-color: $primary;
          color: $white;
        }
      }
    }
  }
  &-main {
    flex-grow: 1;
    padding: 1rem;
  }
}
</style>
