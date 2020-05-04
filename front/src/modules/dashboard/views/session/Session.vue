<template>
  <TemplateDefault class="DashboardSession">
    <div v-if="loading === false">
      <header class="DashboardSession-header">
        <div class="DashboardSession-header-wrapper">
          <div class="DashboardSession-header-left">
            <span class="DashboardSession-header-date">
              {{ dateLabel }} {{ session.start | formatHour }}-{{
                session.end | formatHour
              }}
            </span>
            <h1 class="DashboardSession-header-title title is-3">
              {{ session.title }}
            </h1>
          </div>
          <SessionButtonRegistered
            class="DashboardSession-header-right"
            @unsubscribe="unsubscribe"
            @registerd="registerd"
            :isRegistered="userRegistered"
            :loading="loadingRegisterd"
          />
        </div>
      </header>
      <div v-if="isInsctructorOrganization" class="DashboardSession-emerge">
        <router-link
          :to="{ name: 'dashboard-session-emerge', params: { id: session.id } }"
          class="button is-primary is-medium"
          >{{ $t("dashboard.session.label.emerge") }}</router-link
        >
      </div>
      <main class="DashboardSession-main">
        <div class="DashboardSession-main-wrapper">
          <div class="DashboardSession-main-content">
            <SessionContentDescription
              v-if="session.description !== ''"
              :description="session.description"
              class="DashboardSession-main-content-description"
            />
            <SessionContentRegistereds
              :registereds="registeredsTaken"
              :label="$t('dashboard.session.label.registered')"
              :showLimit="session.limit > 0"
              :limit="session.limit"
              class="DashboardSession-main-content-attendees"
            />
            <SessionContentRegistereds
              v-if="registeredsWaiting.length > 0"
              :registereds="registeredsWaiting"
              :label="$t('dashboard.session.label.registeredWaiting')"
              class="DashboardSession-main-content-attendees"
            />
            <SessionComment />
          </div>
          <div class="DashboardSession-main-side">
            <SessionSideOrganizationItem :organization="session.organization" />
          </div>
        </div>
      </main>
      <div v-if="showRegister" class="DashboardSession-bottom">
        <div class="DashboardSession-bottom-wrapper">
          <div class="DashboardSession-bottom-info">
            <span class="DashboardSession-bottom-info-date text is-size-6">
              {{ dateLabel }} {{ session.start | formatHour }}-{{
                session.end | formatHour
              }}
            </span>
            <span class="DashboardSession-bottom-info-title text is-size-5">{{
              session.title
            }}</span>
          </div>
          <SessionButtonRegistered
            class="DashboardSession-bottom-button"
            @unsubscribe="unsubscribe"
            @registerd="registerd"
            :isRegistered="userRegistered"
            :loading="loadingRegisterd"
          />
        </div>
      </div>
    </div>
  </TemplateDefault>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

import { STATUS_REGISTERED } from "@/utils/consts";

import TemplateDefault from "@core/template/TemplateDefault";

import SessionSideOrganizationItem from "@dashboard/components/session/side/SessionSideOrganizationItem";
import SessionContentDescription from "@dashboard/components/session/content/SessionContentDescription";
import SessionContentRegistereds from "@dashboard/components/session/content/SessionContentRegistereds";

import SessionComment from "@dashboard/components/session/comment/SessionComment";

import SessionButtonRegistered from "@dashboard/components/session/button/SessionButtonRegistered";

import dayjs from "dayjs";

export default {
  name: "DashboardSession",
  components: {
    TemplateDefault,
    SessionSideOrganizationItem,
    SessionContentDescription,
    SessionContentRegistereds,
    SessionComment,
    SessionButtonRegistered
  },
  data() {
    return {
      loadingRegisterd: false,
      loadingComponent: null,
      loading: true
    };
  },
  computed: {
    ...mapGetters({
      session: "dashboard/session",
      isInsctructorOrganization: "dashboard/isInsctructorOrganization",
      userRegistered: "dashboard/userRegistered",
      getUserForSession: "dashboard/getUserForSession"
    }),
    dateLabel() {
      if (!this.session) return "";
      const date = dayjs(this.session.start);
      return `${this.$t(`core.daysNumber.${date.get("day")}`)} ${date.get(
        "date"
      )} ${this.$t(`core.monthsNumber.${date.get("month")}`)} ${date.get(
        "year"
      )}`;
    },
    registeredsTaken() {
      return this.session.registereds.filter(
        r => r.status === STATUS_REGISTERED.TAKEN
      );
    },
    registeredsWaiting() {
      return this.session.registereds.filter(
        r => r.status === STATUS_REGISTERED.WAITING
      );
    },
    showRegister() {
      const now = dayjs();
      const start = dayjs(this.session.start);
      return now.isBefore(start);
    }
  },
  methods: {
    registerd() {
      if (this.loadingRegisterd) return;
      this.loadingRegisterd = true;
      this.$store.dispatch("dashboard/registered", this.session.id).then(() => {
        this.$buefy.toast.open({
          message: this.$t("dashboard.session.registered.success"),
          type: "is-success"
        });
        this.loadingRegisterd = false;
      });
    },
    unsubscribe() {
      if (this.loadingRegisterd) return;
      this.$buefy.dialog.confirm({
        title: this.$t("dashboard.session.unsubscribe.title"),
        message: this.$t("dashboard.session.unsubscribe.message"),
        confirmText: this.$t("dashboard.session.label.iUnsubscribe"),
        cancelText: this.$t("core.utils.cancel"),
        type: "is-warning",
        onConfirm: () => {
          this.loadingRegisterd = true;
          this.$store
            .dispatch(
              "dashboard/unsubscribe",
              this.getUserForSession.registered.id
            )
            .then(() => {
              this.$buefy.toast.open({
                message: this.$t("dashboard.session.unsubscribe.success"),
                type: "is-success"
              });
              this.loadingRegisterd = false;
            });
        }
      });
    },
    startLoading() {
      this.loading = true;
      this.loadingComponent = this.$buefy.loading.open({});
    },
    endLoading() {
      if (this.loadingComponent) {
        this.loadingComponent.close();
        this.loadingComponent = null;
        this.loading = false;
      }
    }
  },
  mounted() {
    this.loading = true;
    this.$nextTick(() => {
      this.startLoading();
      this.$store
        .dispatch("dashboard/setSession", this.$route.params.id)
        .then(() => {
          this.endLoading();
        })
        .catch(() => {
          this.endLoading();
          this.loading = false;
        });
    });
  }
};
</script>

<style lang="scss">
.DashboardSession {
  &-header {
    padding: 1rem 0.5rem 1rem;
    box-shadow: 0 1px 4px 0 $grey;
    &-wrapper {
      display: flex;
      justify-content: space-between;
      align-items: center;
      width: 100%;
      max-width: 960px;
      margin: 0 auto;
      @media (max-width: $mobile) {
        flex-direction: column;
      }
    }
    &-right {
      @media (max-width: $mobile) {
        margin-top: 1rem;
      }
    }
    &-left {
      @media (max-width: $mobile) {
        display: flex;
        flex-direction: column;
        align-items: center;
      }
    }
  }
  &-emerge {
    display: flex;
    justify-content: center;
    padding: 0.5rem;
  }
  &-main {
    min-height: 100vh;
    background-color: $white-bis;
    padding: 0 1rem 5rem;
    &-wrapper {
      display: flex;
      width: 100%;
      max-width: 960px;
      padding: 2rem 0;
      margin: 0 auto;
      @media (max-width: $tablet) {
        flex-direction: column;
        padding: 0.5rem 0;
      }
    }
    &-content {
      flex-grow: 1;
      &-description {
        margin-bottom: 1rem;
      }
      &-attendees {
        margin-bottom: 1rem;
      }
    }
    &-side {
      width: 375px;
      @media (max-width: $tablet) {
        display: flex;
        justify-content: center;
        width: 100%;
        margin-bottom: 1rem;
        order: -1;
      }
    }
  }
  &-bottom {
    background-color: $white;
    box-shadow: 0 -2px 7px 0 $grey;
    bottom: 0;
    padding: 0.5rem;
    &-wrapper {
      display: flex;
      justify-content: space-between;
      align-items: center;
      width: 100%;
      max-width: 960px;
      margin: 0 auto;
      @media (max-width: $mobile) {
        justify-content: center;
      }
    }
    &-info {
      display: flex;
      flex-direction: column;
      @media (max-width: $mobile) {
        display: none;
      }
    }
  }
}
</style>
