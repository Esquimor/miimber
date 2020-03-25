<template>
  <TemplateSidePanelRight
    :title="$t('organization.sessions.add.title')"
    :loading="loading"
    :disabled="!canConfirm"
    hasPadding
    @confirm="confirm"
  >
    <div class="columns">
      <div class="column">
        <BField :label="$t('organization.sessions.label.startHour')">
          <BTimepicker
            v-model="session.startHour"
            icon="alarm"
            trap-focus
          ></BTimepicker>
        </BField>
      </div>
      <div class="column">
        <BField :label="$t('organization.sessions.label.endHour')">
          <BTimepicker
            v-model="session.endHour"
            icon="alarm"
            trap-focus
          ></BTimepicker>
        </BField>
      </div>
    </div>
    <div class="columns">
      <div class="column">
        <BField :label="$t('organization.sessions.label.sessionDate')">
          <BDatepicker
            v-model="session.sessionDate"
            placeholder="Click to select..."
            icon="calendar-today"
            trap-focus
            :minDate="minDate"
          ></BDatepicker>
        </BField>
      </div>
      <div class="column">
        <BField :label="$t('organization.sessions.label.typeSession')">
          <BSelect
            placeholder="Select a name"
            v-model="session.typeSession"
            expanded
          >
            <option
              v-for="typeSession in typeSessions"
              :value="typeSession.id"
              :key="typeSession.id"
              >{{ typeSession.name }}</option
            >
          </BSelect>
        </BField>
      </div>
    </div>
  </TemplateSidePanelRight>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

import dayjs from "dayjs";
import customParseFormat from "dayjs/plugin/customParseFormat";
dayjs.extend(customParseFormat);

import TemplateSidePanelRight from "@core/template/TemplateSidePanelRight";

export default {
  name: "OrganizationSessionsAdd",
  components: {
    TemplateSidePanelRight
  },
  data() {
    const today = new Date();
    const minDate = new Date(
      today.getFullYear(),
      today.getMonth(),
      today.getDate() - 1,
      24,
      0,
      0
    );
    return {
      loading: false,
      minDate: minDate,
      session: {
        startHour: null,
        endHour: null,
        typeSession: null,
        sessionDate: null
      }
    };
  },
  computed: {
    ...mapGetters({
      typeSessions: "organization/typeSessions"
    }),
    canConfirm() {
      return (
        !!this.session.startHour &&
        !!this.session.endHour &&
        !!this.session.typeSession &&
        !!this.session.sessionDate
      );
    }
  },
  methods: {
    confirm() {
      if (this.loading) return;
      if (!this.canConfirm) return;
      this.loading = true;
      const start = new Date(this.session.sessionDate.getTime());
      start.setHours(this.session.startHour.getHours());
      start.setMinutes(this.session.startHour.getMinutes());
      const end = new Date(this.session.sessionDate.getTime());
      end.setHours(this.session.endHour.getHours());
      end.setMinutes(this.session.endHour.getMinutes());
      this.$store
        .dispatch("organization/addSession", {
          start: dayjs(start).format("YYYY-MM-DDTHH:mm:ssZ"),
          end: dayjs(end).format("YYYY-MM-DDTHH:mm:ssZ"),
          typeSessionId: this.session.typeSession
        })
        .then(() => {
          this.$buefy.toast.open({
            message: this.$t("organization.sessions.add.success"),
            type: "is-primary"
          });
          this.$store.dispatch("core/closeSideBar");
        })
        .catch(() => {
          this.loading = false;
        });
    }
  },
  mounted() {
    this.session.typeSession = this.typeSessions[0].id;
  }
};
</script>

<style lang="scss"></style>
