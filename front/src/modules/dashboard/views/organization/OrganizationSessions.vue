<template>
  <div class="DashboardOrganizationSessions" v-if="!loading">
    <div class="DashboardOrganizationSessions-search">
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
    <div class="DashboardOrganizationSessions-sessions">
      <SessionList
        v-for="date in sessionByDate"
        :key="date.item"
        :date="date"
        hideOrganization
      />
    </div>
  </div>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

import dayjs from "dayjs";

import SessionList from "@dashboard/components/session/SessionList";

export default {
  name: "DashboardOrganizationSessions",
  components: {
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
      dates: [startDate.clone().toDate(), endDate.clone().toDate()],
      search: ""
    };
  },
  computed: {
    ...mapGetters({
      sessions: "dashboard/organizationSessions"
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
        .dispatch("dashboard/setOrganizationSessions", {
          id: this.$route.params.id,
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
      .dispatch("dashboard/setOrganizationSessions", {
        id: this.$route.params.id,
        minDate: this.dates[0],
        maxDate: this.dates[1]
      })
      .then(() => {
        this.loading = false;
      });
  }
};
</script>

<style lang="scss">
.DashboardOrganizationSessions {
  width: 100%;
  padding: 1rem;
  background-color: $white;
  border: 1px solid $grey-lightest;
  min-height: 80vh;
  border-radius: 5px;
}
</style>
