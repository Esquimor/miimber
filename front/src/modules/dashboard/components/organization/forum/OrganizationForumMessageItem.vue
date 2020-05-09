<template>
  <div class="DashboardOrganizationForumMessageItem">
    <div class="DashboardOrganizationForumMessageItem-user">
      <div class="DashboardOrganizationForumMessageItem-user-name">
        {{ message.user.firstName }} {{ message.user.lastName }}
      </div>
      <div class="DashboardOrganizationForumMessageItem-user-date">
        {{ dateMessage }}
      </div>
    </div>
    <div
      class="DashboardOrganizationForumMessageItem-message"
      v-html="message.message"
    />
  </div>
</template>

<script>
"use strict";

import dayjs from "dayjs";

export default {
  name: "DashboardOrganizationForumMessageItem",
  props: {
    message: {
      type: Object,
      required: true
    }
  },
  computed: {
    dateMessage() {
      return dayjs(this.message.dateMessage).format("DD/MM/YYYY HH:MM");
    }
  }
};
</script>

<style lang="scss">
.DashboardOrganizationForumMessageItem {
  display: flex;
  flex-direction: column;
  border: 1px solid $grey-lightest;
  border-radius: 5px;
  margin: 1rem 0;
  &-user {
    display: flex;
    justify-content: space-between;
    padding: 0.5rem;
    border-bottom: 1px solid $grey-lightest;
    background-color: $white-bis;
  }
  &-message {
    padding: 0.5rem;
  }
}
</style>
