<template>
  <div v-if="!loading">
    <BTable :data="members" striped paginated :per-page="25">
      <template v-slot="{ row }">
        <BTableColumn
          field="firstName"
          :label="$t('organization.members.table.firstName')"
          sortable
        >{{ row.firstName }}</BTableColumn>
        <BTableColumn
          field="lastName"
          :label="$t('organization.members.table.lastName')"
          sortable
        >{{ row.lastName }}</BTableColumn>
        <BTableColumn
          field="role"
          :label="$t('organization.members.table.role')"
        >{{ $t(`core.role.${row.role}`) }}</BTableColumn>
      </template>
    </BTable>
  </div>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

export default {
  name: "DashboardOrganizationMembers",
  data() {
    return {
      loading: true
    };
  },
  computed: {
    ...mapGetters({
      members: "dashboard/organizationMembers"
    })
  },
  mounted() {
    this.$store
      .dispatch("dashboard/setOrganizationMembers", this.$route.params.id)
      .then(() => {
        this.loading = false;
      });
  }
};
</script>

<style lang="scss">
.DashboardOrganizationMembers {
  &-column {
    &-manage {
      justify-content: center !important;
    }
  }
}
</style>