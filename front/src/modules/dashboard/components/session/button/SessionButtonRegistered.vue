<template>
  <div class="DashboardSessionButtonRegistered">
    <BButton
      v-if="isRegistered"
      type="is-primary"
      :loading="loading"
      @click.native="$emit('unsubscribe')"
      outlined
      >{{ $t("dashboard.session.label.iUnsubscribe") }}</BButton
    >
    <BButton
      v-else
      type="is-primary"
      :loading="loading"
      @click.native="$emit('registerd')"
      >{{ $t("dashboard.session.label.imRegistered") }}</BButton
    >
  </div>
</template>

<script>
"use strict";

export default {
  name: "DashboardSessionButtonRegistered",
  props: {
    isRegistered: {
      type: Boolean,
      default: true
    },
    loading: {
      type: Boolean,
      default: true
    }
  }
};
</script>

<style lang="scss"></style>
