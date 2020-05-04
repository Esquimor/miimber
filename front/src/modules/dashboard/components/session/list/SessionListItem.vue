<template>
  <router-link
    :to="{ name: 'dashboard-session', params: { id: session.id } }"
    class="DashboardSessionListItem"
  >
    <div class="DashboardSessionListItem-time">
      <BTag :type="statusSession.type">
        {{ $t(`core.sessionType.${statusSession.label}`) }}
      </BTag>
      <div class="DashboardSessionListItem-time-start">
        {{ session.start | formatHour }}
      </div>
      <div class="DashboardSessionListItem-time-end">
        {{ session.end | formatHour }}
      </div>
    </div>
    <div class="DashboardSessionListItem-info">
      <div
        v-if="!hideOrganization"
        class="DashboardSessionListItem-info-organization text is-size-6"
      >
        {{ session.organizationName }}
      </div>
      <div class="text is-size-5">{{ session.title }}</div>
      <div class="DashboardSessionListItem-info-person">
        <span class="DashboardSessionListItem-info-person-registered">
          {{ session.nbRegistereds }}
          {{ $t("dashboard.session.label.registered") }}
        </span>
        <template
          v-if="
            isRegisteredLimited && statusSession === STATUS_SESSION.TO_COME_UP
          "
        >
          <span
            v-if="session.nbRegistereds < session.limit"
            class="DashboardSessionListItem-info-person-places"
          >
            {{
              $t("dashboard.session.label.placesLeft", {
                nb: session.limit - session.nbRegistereds
              })
            }}
          </span>
          <span v-else class="DashboardSessionListItem-info-person-waiting">
            -
            {{
              $t("dashboard.session.label.waiting", {
                nb: session.nbRegistereds - session.limit
              })
            }}
          </span>
        </template>
      </div>
    </div>
  </router-link>
</template>

<script>
"use strict";

import dayjs from "dayjs";

import { STATUS_SESSION } from "@/utils/consts";

export default {
  name: "DashboardSessionListItem",
  props: {
    session: {
      type: Object,
      required: true
    },
    hideOrganization: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      STATUS_SESSION: STATUS_SESSION
    };
  },
  computed: {
    isRegisteredLimited() {
      return this.session.limit !== 0;
    },
    statusSession() {
      const now = dayjs();
      const start = dayjs(this.session.start);
      const end = dayjs(this.session.end);
      if (now.isBefore(start)) {
        return STATUS_SESSION.TO_COME_UP;
      }
      if (now.isAfter(start) && now.isBefore(end)) {
        return STATUS_SESSION.IN_PROGRESS;
      }
      return STATUS_SESSION.COMPLETED;
    }
  }
};
</script>

<style lang="scss">
.DashboardSessionListItem {
  color: $text;
  display: flex;
  border-bottom: 1px solid $grey-lightest;
  border-left: 1px solid $grey-lightest;
  border-right: 1px solid $grey-lightest;
  background-color: $white;
  &-time {
    display: flex;
    flex-direction: column;
    align-items: center;
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
    }
  }
  &:nth-child(1) {
    border-top: 1px solid $grey-lightest;
  }
}
</style>
