<template>
  <router-link
    :to="{
      name: 'dashboard-organization-forum-talk',
      params: {
        idSubject: $route.params.idSubject,
        idTalk: talk.id
      }
    }"
    class="DashboardOrganizationForumTalkItem"
    >{{ talk.title }}</router-link
  >
</template>

<script>
"use strict";

export default {
  name: "DashboardOrganizationForumTalkItem",
  props: {
    talk: {
      type: Object,
      required: true
    }
  }
};
</script>

<style lang="scss">
.DashboardOrganizationForumTalkItem {
  padding: 0.5rem;
  border-bottom: 1px solid $white-ter;
  color: $black-ter;
  &:hover {
    box-shadow: inset 0 1px 2px rgba(10, 10, 10, 0.1);
    color: $primary;
  }
}
</style>
