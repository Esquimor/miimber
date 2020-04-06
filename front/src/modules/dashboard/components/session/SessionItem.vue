<template>
  <router-link
    :to="{name: 'dashboard-session',
        params: { id: session.id } }"
    class="DashboardSessionItem"
  >
    <div class="DashboardSessionItem-time">
      <div class="DashboardSessionItem-time-start">{{ session.start | formatHour }}</div>
      <div class="DashboardSessionItem-time-end">{{ session.end | formatHour }}</div>
    </div>
    <div class="DashboardSessionItem-info">
      <div
        class="DashboardSessionItem-info-organization text is-size-6"
      >{{ session.organizationName }}</div>
      <div class="text is-size-5">{{ session.title }}</div>
      <div class="DashboardSessionItem-info-person">
        <span
          class="DashboardSessionItem-info-person-registered"
        >{{session.nbRegistereds }} {{ $t("dashboard.session.label.registered") }}</span>
        <template v-if="isRegisteredLimited">
          <span
            v-if="session.nbRegistereds < session.limit"
            class="DashboardSessionItem-info-person-places"
          >{{ $t('dashboard.session.label.placesLeft', {nb: session.limit - session.nbRegistereds}) }}</span>
          <span
            v-else
            class="DashboardSessionItem-info-person-waiting"
          >{{ $t('dashboard.session.label.waiting', {nb: session.nbRegistereds - session.limit }) }}</span>
        </template>
      </div>
    </div>
  </router-link>
</template>

<script>
"use strict";

export default {
  name: "DashboardSessionItem",
  props: {
    session: {
      type: Object,
      required: true
    }
  },
  computed: {
    isRegisteredLimited() {
      return this.session.limit !== 0;
    }
  }
};
</script>

<style lang="scss">
.DashboardSessionItem {
  color: $text;
  display: flex;
  border-bottom: 1px solid $grey-lightest;
  border-left: 1px solid $grey-lightest;
  border-right: 1px solid $grey-lightest;
  background-color: $white;
  &-time {
    padding: 0.5rem 1rem;
  }
  &-info {
    padding: 0.5rem;
    &-organization {
      color: $grey;
    }
    &-person {
      &-places {
        margin-left: 0.5rem;
        color: $primary;
      }
      &-waiting {
        margin-left: 0.5rem;
      }
    }
  }
  &:nth-child(1) {
    border-top: 1px solid $grey-lightest;
  }
}
</style>