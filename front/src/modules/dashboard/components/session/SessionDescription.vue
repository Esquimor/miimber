<template>
  <section class="DashboardSessionDescription">
    <h2
      class="DashboardSessionDescription-title title is-5"
    >{{ $t("dashboard.session.label.descriptionTitle") }}</h2>
    <p class="DashboardSessionDescription-text">{{ description }}</p>
  </section>
</template>

<script>
"use strict";

export default {
  name: "DashboardSessionDescription",
  props: {
    description: {
      type: String,
      default: ""
    }
  }
};
</script>

<style lang="scss">
</style>