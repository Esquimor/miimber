<template>
  <TemplateDefault class="DashboardSession">
    <div v-if="loading === false">
      <header class="DashboardSession-header">
        <div class="DashboardSession-header-wrapper">
          <span class="DashboardSession-header-date">
            {{ dateLabel }} {{ session.start | formatHour }}-{{
            session.end | formatHour
            }}
          </span>
          <h1 class="DashboardSession-header-title title is-3">{{ session.title }}</h1>
        </div>
      </header>
      <div v-if="isInsctructorOrganization" class="DashboardSession-emerge">
        <router-link
          :to="{ name: 'dashboard-session-emerge', params: { id: session.id } }"
          class="button is-primary is-medium"
        >{{ $t("dashboard.session.label.emerge") }}</router-link>
      </div>
      <main class="DashboardSession-main">
        <div class="DashboardSession-main-wrapper">
          <div class="DashboardSession-main-left">
            <SessionDescription
              v-if="session.description !== ''"
              :description="session.description"
              class="DashboardSession-main-left-description"
            />
            <SessionRegistereds
              :registereds="registeredsTaken"
              :label="$t('dashboard.session.label.registered')"
            />
            <SessionRegistereds
              v-if="registeredsWaiting.length > 0"
              :registereds="registeredsWaiting"
              :label="$t('dashboard.session.label.registeredWaiting')"
            />
          </div>
          <div class="DashboardSession-main-right">
            <SessionOrganizationItem :organization="session.organization" />
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
            <span class="DashboardSession-bottom-info-title text is-size-5">
              {{
              session.title
              }}
            </span>
          </div>
          <div class="DashboardSession-bottom-button">
            <BButton
              v-if="userRegistered"
              type="is-primary"
              :loading="loadingRegisterd"
              @click.native="unsubscribe"
              outlined
            >{{ $t("dashboard.session.label.iUnsubscribe") }}</BButton>
            <BButton
              v-else
              type="is-primary"
              :loading="loadingRegisterd"
              @click.native="registerd"
            >{{ $t("dashboard.session.label.imRegistered") }}</BButton>
          </div>
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

import SessionOrganizationItem from "@dashboard/components/session/SessionOrganizationItem";
import SessionDescription from "@dashboard/components/session/SessionDescription";
import SessionRegistereds from "@dashboard/components/session/SessionRegistereds";

import dayjs from "dayjs";

export default {
  name: "DashboardSession",
  components: {
    TemplateDefault,
    SessionOrganizationItem,
    SessionDescription,
    SessionRegistereds
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
        this.loadingRegisterd = false;
      });
    },
    unsubscribe() {
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
      width: 100%;
      max-width: 960px;
      margin: 0 auto;
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
    padding: 0 1rem;
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
    &-left {
      flex-grow: 1;
      &-description {
        margin-bottom: 1rem;
      }
    }
    &-right {
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
    position: sticky;
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
