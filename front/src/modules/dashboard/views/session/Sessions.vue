<template>
  <TemplateDashboard :loading="loading">
    <div class="DashboardSession-search">
      <div class="columns">
        <div class="column">
          <BField :label="$t('organization.sessions.label.search')">
            <BInput v-model="search" type="search"></BInput>
          </BField>
        </div>
        <div class="column">
          <BField :label="$t('organization.sessions.label.between')">
            <BDatepicker
              v-model="dates"
              range
              @input="setSessions"
              :nearbyMonthDays="false"
            ></BDatepicker>
          </BField>
        </div>
      </div>
    </div>
    <div v-if="sessionByDate.length > 0" class="DashboardSession-sessions">
      <SessionList
        v-for="date in sessionByDate"
        :key="date.item"
        :date="date"
      />
    </div>
    <div v-else class="DashboardSession-empty">
      <span>{{ $t("dashboard.session.label.empty") }}</span>
    </div>
  </TemplateDashboard>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

import dayjs from "dayjs";

import TemplateDashboard from "@dashboard/template/TemplateDashboard";

import SessionList from "@dashboard/components/session/list/SessionList";

export default {
  name: "DashboardSession",
  components: {
    TemplateDashboard,
    SessionList
  },
  data() {
    let startDate = dayjs()
      .set("hour", 0)
      .set("minute", 0)
      .set("second", 0)
      .set("millisecond", 0);
    let endDate = dayjs()
      .set("hour", 23)
      .set("minute", 59)
      .set("second", 59)
      .set("millisecond", 0)
      .subtract(1, "day")
      .add(1, "week");
    return {
      loading: true,
      search: "",
      dates: [startDate.clone().toDate(), endDate.clone().toDate()],
      currentWeek: [startDate.clone().toDate(), endDate.clone().toDate()],
      nextWeek: [
        startDate
          .clone()
          .add(1, "week")
          .toDate(),
        endDate
          .clone()
          .add(1, "week")
          .toDate()
      ]
    };
  },
  computed: {
    ...mapGetters({
      sessions: "dashboard/sessions"
    }),
    reorderByDate() {
      // eslint-disable-next-line vue/no-side-effects-in-computed-properties
      const reorderByDate = this.sessions.sort((a, b) =>
        dayjs(a.start).isAfter(dayjs(b.start))
      );
      return reorderByDate;
    },
    filteredSession() {
      if (this.search === "") return this.reorderByDate;

      const lowerCaseSearch = this.search.toLowerCase();
      return this.reorderByDate.filter(p => {
        return p.title.toLowerCase().search(lowerCaseSearch) !== -1;
      });
    },
    sessionByDate() {
      return this.filteredSession.reduce((list, session) => {
        const start = dayjs(session.start);
        const sameDay = list.find(
          e =>
            e.date === start.get("date") &&
            e.month === start.get("month") &&
            e.year === start.get("year")
        );
        if (sameDay) {
          sameDay.sessions = [...sameDay.sessions, session];
        } else {
          list = [
            ...list,
            {
              date: start.get("date"),
              day: start.get("day"),
              month: start.get("month"),
              year: start.get("year"),
              sessions: [session]
            }
          ];
        }
        return list;
      }, []);
    }
  },
  methods: {
    setSessions() {
      this.loading = true;
      this.$store
        .dispatch("dashboard/setSessions", {
          minDate: this.dates[0],
          maxDate: this.dates[1]
        })
        .then(() => {
          this.loading = false;
        });
    }
  },
  mounted() {
    this.$store
      .dispatch("dashboard/setSessions", {
        minDate: this.dates[0],
        maxDate: this.dates[1]
      })
      .then(() => {
        this.loading = false;
      })
      .catch(() => {
        this.loading = false;
      });
  }
};
</script>

<style lang="scss">
.DashboardSession {
  &-sessions {
    margin-top: 2rem;
  }
  &-empty {
    display: flex;
    justify-content: center;
    width: 100%;
    padding: 3rem 2rem;
  }
}
</style>
