<template>
  <TemplateDashboard
    :title="$t('dashboard.organization.label.list')"
    :loading="loading"
  >
    <BTable :data="organizations" striped paginated :per-page="25">
      <template v-slot="{ row }">
        <BTableColumn
          field="name"
          :label="$t('dashboard.organization.table.name')"
          sortable
          >{{ row.name }}</BTableColumn
        >
        <BTableColumn class="DashboardOrganizations-column-manage" :width="200">
          <router-link
            :to="{
              name: 'dashboard-organization-sessions',
              params: { id: row.id }
            }"
            class="button is-primary"
          >
            <BIcon icon="eye" />
            <span class="is-size-6">{{ $t("core.utils.see") }}</span>
          </router-link>
        </BTableColumn>
      </template>
    </BTable>
  </TemplateDashboard>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

import TemplateDashboard from "@dashboard/template/TemplateDashboard";

export default {
  name: "DashboardOrganizations",
  components: {
    TemplateDashboard
  },
  data() {
    return {
      loading: true
    };
  },
  computed: {
    ...mapGetters({
      organizations: "dashboard/organizations",
      canChangeOrganization: "core/canChangeOrganization"
    })
  },
  mounted() {
    this.$store.dispatch("dashboard/setOrganizations").then(() => {
      this.loading = false;
    });
  }
};
</script>

<style lang="scss">
.DashboardOrganizations {
  &-column {
    &-manage {
      display: flex;
      justify-content: center;
    }
  }
}
</style>
