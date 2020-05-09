<template>
  <div class="DashboardSessionList">
    <header class="DashboardSessionList-header">{{ dateLabel }}</header>
    <div class="DashboardSessionList-sessions">
      <SessionItem
        v-for="session in date.sessions"
        :key="session.id"
        :session="session"
        :hideOrganization="hideOrganization"
      />
    </div>
  </div>
</template>

<script>
"use strict";

import SessionItem from "@dashboard/components/session/list/SessionListItem";

export default {
  name: "DashboardSessionList",
  components: {
    SessionItem
  },
  props: {
    date: {
      type: Object,
      required: true
    },
    hideOrganization: {
      type: Boolean,
      default: false
    }
  },
  computed: {
    dateLabel() {
      return `${this.$t(`core.daysNumber.${this.date.day}`)} ${
        this.date.date
      } ${this.$t(`core.monthsNumber.${this.date.month}`)}`;
    }
  }
};
</script>

<style lang="scss">
.DashboardSessionList {
  background-color: $white;
  &-header {
    padding: 1rem 0.5rem 0.5rem;
  }
}
</style>
