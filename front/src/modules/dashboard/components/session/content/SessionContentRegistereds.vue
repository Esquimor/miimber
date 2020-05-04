<template>
  <section class="DashboardSessionContentRegistereds">
    <h2 class="DashboardSessionDescription-title title is-5">
      {{ label }}
      <span>({{ nbRegistered }}</span>
      <span v-if="showLimit">/{{ limit }}</span
      >)
    </h2>
    <div v-if="nbRegistered > 0" class="DashboardSessionDescription-users">
      <SessionUserItem
        v-for="registered in orderRegistered"
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
import dayjs from "dayjs";

export default {
  name: "DashboardSessionContentRegistereds",
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
    },
    showLimit: {
      type: Boolean,
      default: false
    },
    limit: {
      type: Number,
      default: 0
    }
  },
  computed: {
    nbRegistered() {
      return this.registereds.length;
    },
    orderRegistered() {
      // eslint-disable-next-line vue/no-side-effects-in-computed-properties
      return this.registereds.sort((a, b) =>
        dayjs(a.dateRegistered).isAfter(dayjs(b.dateRegistered))
      );
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
