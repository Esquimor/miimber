<template>
  <div class="DashboardOrganizationForumTalkItem">{{ talk.title }}</div>
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

<style>
.DashboardOrganizationForumTalkItem {
}
</style>