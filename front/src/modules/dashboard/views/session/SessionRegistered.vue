<template>
  <div class="DashboardSessionRegistered">
    <BTable v-if="registereds.length > 0" :data="registereds" striped>
      <template v-slot="{ row }">
        <BTableColumn
          field="title"
          :label="$t('dashboard.session.registered.table.name')"
          >{{ row.user.firstName }} {{ row.user.lastName }}</BTableColumn
        >
      </template>
    </BTable>
    <div v-else class="DashboardSessionRegistered-empty">
      <span>{{ $t("dashboard.session.registered.empty") }}</span>
    </div>
  </div>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

export default {
  name: "DashboardSessionRegistered",
  computed: {
    ...mapGetters({
      registereds: "dashboard/sessionRegistereds"
    })
  }
};
</script>

<style lang="scss">
.DashboardSessionRegistered {
  &-empty {
    display: flex;
    justify-content: center;
    margin: 2rem 1rem;
  }
}
</style>
