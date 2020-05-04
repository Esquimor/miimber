<template>
  <div class="OrganizationForumSubject">{{ subject }}</div>
</template>

<script>
"use strict";

export default {
  name: "OrganizationForumSubject",
  props: {
    subject: {
      type: Object,
      required: true
    }
  }
};
</script>

<style lang="scss"></style>
