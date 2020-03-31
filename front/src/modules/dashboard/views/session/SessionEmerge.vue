<template>
  <div>
    <BTable :data="users" striped>
      <template v-slot="{ row }">
        <BTableColumn
          field="title"
          :label="$t('dashboard.session.emerge.table.name')"
          >{{ row.firstName }} {{ row.lastName }}</BTableColumn
        >
        <BTableColumn
          class="DashboardSessionEmerge-column-checkbox"
          :width="100"
          :label="$t('dashboard.session.emerge.table.present')"
        >
          <BCheckbox @click.native="setUser(row.id)"></BCheckbox>
        </BTableColumn>
      </template>
    </BTable>
  </div>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

export default {
  name: "DashboardSessionEmerge",
  computed: {
    ...mapGetters({
      users: "dashboard/sessionUsers"
    })
  },
  methods: {
    setUser(id) {
      this.$store.dispatch("dashboard/setUserPresentSession", id);
    }
  }
};
</script>

<style lang="scss">
.DashboardSessionEmerge {
  &-column {
    &-checkbox {
      display: flex;
      justify-content: center;
    }
  }
}
</style>
