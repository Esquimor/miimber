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
        <BField :label="$t('organization.sessions.label.title')">
          <BInput v-model.trim="session.title" required></BInput>
        </BField>
      </div>
    </div>
    <div class="columns">
      <div class="column">
        <BField :label="$t('organization.sessions.label.startHour')">
          <BClockpicker
            v-model="session.startHour"
            icon="clock"
            hourFormat="24"
            trap-focus
            required
            :hoursLabel="$t('core.time.hours')"
            :minutesLabel="$t('core.timeShort.minutes')"
          ></BClockpicker>
        </BField>
      </div>
      <div class="column">
        <BField :label="$t('organization.sessions.label.endHour')">
          <BClockpicker
            v-model="session.endHour"
            icon="clock"
            hourFormat="24"
            trap-focus
            required
            :hoursLabel="$t('core.time.hours')"
            :minutesLabel="$t('core.timeShort.minutes')"
          ></BClockpicker>
        </BField>
      </div>
    </div>
    <div class="columns">
      <div
        class="column"
        :class="{
          'is-half':
            recurrence.periodicity === SESSION_RECURRENCE.ONCE ||
            recurrence.periodicity === SESSION_RECURRENCE.EVERYDAY
        }"
      >
        <BField :label="$t('organization.sessions.label.recurrence')">
          <BSelect v-model="recurrence.periodicity" expanded required>
            <option
              v-for="recurrence in SESSION_RECURRENCE"
              :value="recurrence"
              :key="recurrence.item"
              >{{ $t(`core.recurrence.${recurrence}`) }}</option
            >
          </BSelect>
        </BField>
      </div>
      <div
        class="column"
        v-if="
          recurrence.periodicity === SESSION_RECURRENCE.BY_WEEK ||
            recurrence.periodicity === SESSION_RECURRENCE.CUSTOM
        "
      >
        <BField :label="$t('organization.sessions.label.repeat')">
          <BSelect v-model="recurrence.repeat" expanded required>
            <option
              v-for="repeat in SESSION_REPEAT"
              :value="repeat.value"
              :key="repeat.item"
              >{{ $t(`core.repeatWeek.${repeat.label}`) }}</option
            >
          </BSelect>
        </BField>
      </div>
    </div>
    <div
      class="columns"
      v-if="recurrence.periodicity === SESSION_RECURRENCE.BY_WEEK"
    >
      <div class="column">
        <BField label="Jours">
          <OrganizationSessionsDays
            @click="changeDays"
            :days="recurrence.days"
          />
        </BField>
      </div>
    </div>
    <div
      class="columns"
      v-if="recurrence.periodicity === SESSION_RECURRENCE.ONCE"
    >
      <div class="column is-half">
        <BField :label="$t('organization.sessions.label.sessionDate')">
          <BDatepicker
            v-model="session.startDate"
            icon="calendar-today"
            trap-focus
            :minDate="minDate"
            :firstDayOfWeek="1"
            :monthNames="monthNames"
            :dayNames="dayNames"
            :nearbyMonthDays="false"
          ></BDatepicker>
        </BField>
      </div>
    </div>
    <div
      class="columns"
      v-if="recurrence.periodicity !== SESSION_RECURRENCE.ONCE"
    >
      <div class="column">
        <BField :label="$t('organization.sessions.label.startDate')">
          <BDatepicker
            v-model="session.startDate"
            icon="calendar-today"
            trap-focus
            :minDate="minDate"
            :firstDayOfWeek="1"
            :monthNames="monthNames"
            :dayNames="dayNames"
            :nearbyMonthDays="false"
          ></BDatepicker>
        </BField>
      </div>
      <div class="column">
        <BField :label="$t('organization.sessions.label.endDate')">
          <BDatepicker
            v-model="session.endDate"
            icon="calendar-today"
            trap-focus
            position="is-bottom-left"
            :minDate="minDate"
            :firstDayOfWeek="1"
            :monthNames="monthNames"
            :dayNames="dayNames"
            :nearbyMonthDays="false"
          ></BDatepicker>
        </BField>
      </div>
    </div>
    <div class="columns">
      <div class="column" :class="{ 'is-half': !hasLimit }">
        <BField
          :label="$t('organization.sessions.label.hasLimit')"
          style="height: 68px;"
        >
          <BSwitch v-model="hasLimit">
            {{ hasLimit ? $t("core.utils.yes") : $t("core.utils.no") }}
          </BSwitch>
        </BField>
      </div>
      <div class="column" v-if="hasLimit">
        <BField :label="$t('organization.sessions.label.limit')">
          <BNumberinput v-model="session.limit" min="0"></BNumberinput>
        </BField>
      </div>
    </div>
    <div class="columns">
      <div class="column is-half">
        <BField :label="$t('organization.sessions.label.typeSession')">
          <BSelect v-model="session.typeSession" expanded>
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
    <div class="columns">
      <div class="column">
        <BField :label="$t('organization.sessions.label.description')">
          <BInput
            v-model.trim="session.description"
            maxlength="500"
            type="textarea"
          ></BInput>
        </BField>
      </div>
    </div>
  </TemplateSidePanelRight>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

import calendar from "@core/mixins/calendar";

import { SESSION_RECURRENCE, SESSION_REPEAT } from "@/utils/consts";

import dayjs from "dayjs";
import customParseFormat from "dayjs/plugin/customParseFormat";
dayjs.extend(customParseFormat);

import TemplateSidePanelRight from "@core/template/TemplateSidePanelRight";

import OrganizationSessionsDays from "@organization/components/sessions/OrganizationSessionsDays";

export default {
  name: "OrganizationSessionsAdd",
  mixins: [calendar],
  components: {
    TemplateSidePanelRight,
    OrganizationSessionsDays
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
      hasLimit: false,
      session: {
        title: "",
        startHour: null,
        endHour: null,
        typeSession: null,
        startDate: null,
        endDate: null,
        description: "",
        limit: 0
      },
      recurrence: {
        periodicity: SESSION_RECURRENCE.ONCE,
        days: [],
        repeat: SESSION_REPEAT.ONE.value
      },
      SESSION_RECURRENCE: SESSION_RECURRENCE,
      SESSION_REPEAT: SESSION_REPEAT
    };
  },
  computed: {
    ...mapGetters({
      typeSessions: "organization/typeSessions"
    }),
    canConfirm() {
      return (
        !!this.session.title &&
        !!this.session.startHour &&
        !!this.session.endHour &&
        !!this.session.typeSession &&
        !!this.session.startDate
      );
    }
  },
  methods: {
    confirm() {
      if (this.loading) return;
      if (!this.canConfirm) return;
      this.loading = true;
      const start = new Date(this.session.startDate.getTime());
      start.setHours(this.session.startHour.getHours());
      start.setMinutes(this.session.startHour.getMinutes());
      const end = new Date(this.session.startDate.getTime());
      end.setHours(this.session.endHour.getHours());
      end.setMinutes(this.session.endHour.getMinutes());
      this.$store
        .dispatch("organization/addSession", {
          title: this.session.title,
          description: this.session.description,
          start: dayjs(start).format("YYYY-MM-DDTHH:mm:ssZ"),
          end: dayjs(end).format("YYYY-MM-DDTHH:mm:ssZ"),
          startDate: dayjs(this.session.startDate).format(
            "YYYY-MM-DDTHH:mm:ssZ"
          ),
          endDate: this.session.endDate
            ? dayjs(this.session.endDate).format("YYYY-MM-DDTHH:mm:ssZ")
            : null,
          typeSessionId: this.session.typeSession,
          periodicity: this.recurrence.periodicity,
          days: this.recurrence.days,
          repeat: this.recurrence.repeat,
          limit: this.session.limit
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
    },
    changeDays(day) {
      let days = this.recurrence.days;
      if (days.some(d => d === day)) {
        this.recurrence.days = days.filter(d => d !== day);
      } else {
        days = days.push(day);
      }
    }
  },
  mounted() {
    this.session.typeSession = this.typeSessions[0].id;
  },
  watch: {
    "session.startDate"(newVal) {
      if (!this.session.endDate) return;
      if (!newVal) return;
      if (dayjs(newVal).isAfter(dayjs(this.session.endDate))) {
        const temp = this.session.endDate;
        this.session.endDate = newVal;
        this.session.startDate = temp;
      }
    },
    "session.endDate"(newVal) {
      if (!this.session.startDate) return;
      if (!newVal) return;
      if (dayjs(newVal).isBefore(dayjs(this.session.startDate))) {
        const temp = this.session.startDate;
        this.session.startDate = newVal;
        this.session.endDate = temp;
      }
    }
  }
};
</script>

<style lang="scss"></style>
