<template>
  <section class="DashboardSessionContentDescription">
    <h2 class="DashboardSessionContentDescription-title title is-5">
      {{ $t("dashboard.session.label.descriptionTitle") }}
    </h2>
    <p class="DashboardSessionContentDescription-text">{{ description }}</p>
  </section>
</template>

<script>
"use strict";

export default {
  name: "DashboardSessionContentDescription",
  props: {
    description: {
      type: String,
      default: ""
    }
  }
};
</script>

<style lang="scss"></style>
