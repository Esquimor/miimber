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
          <BCheckbox
            :value="!!row.attendeeId"
            @click.native="setUser(row)"
          ></BCheckbox>
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
    setUser(user) {
      if (user.attendeeId === 0 || user.attendeeId === null)
        this.$store.dispatch("dashboard/setUserPresentSession", user.id);
      else
        this.$store.dispatch(
          "dashboard/removeUserPresentSession",
          user.attendeeId
        );
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
