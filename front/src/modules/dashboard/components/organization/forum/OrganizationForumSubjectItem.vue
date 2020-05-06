<template>
  <router-link
    :to="{name: 'dashboard-organization-forum-subject', params: {idSubject: subject.id}}"
    class="DashboardOrganizationForumSubjectItem"
  >{{ subject.title }}</router-link>
</template>

<script>
"use strict";

export default {
  name: "DashboardOrganizationForumSubjectItem",
  props: {
    subject: {
      type: Object,
      required: true
    }
  }
};
</script>

<style lang="scss">
.DashboardOrganizationForumSubjectItem {
  padding: 0.5rem;
  color: $black-ter;
}
</style>