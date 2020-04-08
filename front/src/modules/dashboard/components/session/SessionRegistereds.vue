<template>
  <section class="DashboardSessionRegistereds">
    <h2 class="DashboardSessionDescription-title title is-5">
      {{ label }} ({{ nbRegistered }})
    </h2>
    <div v-if="nbRegistered > 0" class="DashboardSessionDescription-users">
      <SessionUserItem
        v-for="registered in registereds"
        :key="registered.id"
        :user="registered.user"
      />
    </div>
    <div v-else class="DashboardSessionDescription-empty">
      <span>{{ $t("dashboard.session.label.registeredEmpty") }}</span>
    </div>
  </section>
</template>

<script>
"use strict";

import SessionUserItem from "@dashboard/components/session/SessionUserItem";

export default {
  name: "DashboardSessionRegistereds",
  components: {
    SessionUserItem
  },
  props: {
    registereds: {
      type: Array,
      required: true
    },
    label: {
      type: String,
      default: ""
    }
  },
  computed: {
    nbRegistered() {
      return this.registereds.length;
    }
  }
};
</script>

<style lang="scss">
.DashboardSessionDescription {
  &-empty {
    display: flex;
    justify-content: center;
  }
  &-users {
    display: flex;
    flex-wrap: wrap;
    justify-content: flex-start;
    @media (max-width: $tablet) {
      justify-content: center;
    }
  }
}
</style>
