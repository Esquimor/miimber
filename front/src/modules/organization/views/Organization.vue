<template>
  <TemplateDefault>
    <div class="Organization" v-if="!loading && organization !== null">
      <header class="Organization-header">
        <div class="Organization-header-wrapper">
          <h1 class="is-size-3">{{ organization.name }}</h1>
          <BButton
            v-if="organization.isFree || organization.isPayed"
            id="OrganizationOrganization-edit"
            icon-left="pencil"
            type="is-primary"
            @click="edit"
            outlined
            >{{ $t("core.utils.edit") }}</BButton
          >
        </div>
      </header>
      <div class="Organization-content">
        <OrganizationOrganizationMenu />
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

import OrganizationOrganizationMenu from "@organization/components/organization/OrganizationOrganizationMenu";
import OrganizationOrganizationEdit from "@organization/components/organization/OrganizationOrganizationEdit";

export default {
  name: "Organization",
  components: {
    TemplateDefault,
    OrganizationOrganizationMenu
  },
  data() {
    return {
      loading: true,
      isActiveSession: true
    };
  },
  computed: {
    ...mapGetters({
      organization: "organization/organization"
    })
  },
  methods: {
    edit() {
      this.$store.dispatch("core/openSideBar", {
        component: OrganizationOrganizationEdit,
        props: { name: this.organization.name }
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
        if (this.organization.isPayed === false) {
          this.$router.push({
            name: "organization-manage-settings",
            params: { id: this.organization.id }
          });
        }
      })
      .catch(() => {
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
    background-color: $white-bis;
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
    @media (max-width: $tablet) {
      flex-direction: column;
    }
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
