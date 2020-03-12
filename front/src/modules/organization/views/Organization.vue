<template>
  <TemplateDefault>
    <div class="Organization" v-if="!loading && organization !== null">
      <header class="Organization-header">
        <h1 class="title is-3">{{ organization.name }}</h1>
        <BButton icon-left="pencil" type="is-primary" @click="edit">{{ $t('core.utils.edit') }}</BButton>
      </header>
      <div class="Organization-tabs tabs">
        <ul>
          <router-link tag="li" :to="{ name: 'organization-members' }">
            <a href="#">{{ $t('organization.members.title') }}</a>
          </router-link>
        </ul>
      </div>
      <main class="Organization-main">
        <router-view></router-view>
      </main>
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
  max-width: 800px;
  margin: 0 auto;
  &-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0.5rem;
  }
  &-tabs {
    > ul {
      > li {
        &.router-link-active {
          > a {
            border-bottom: 1px solid $primary;
            color: $primary;
          }
        }
      }
    }
  }
  &-main {
    padding: 0 1rem 1rem;
  }
}
</style>
