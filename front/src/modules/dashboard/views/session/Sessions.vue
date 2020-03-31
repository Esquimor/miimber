<template>
  <TemplateDashboard title="Vos sessions" :loading="loading">
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
    <BTable :data="filteredSession" striped paginated :per-page="25">
      <template v-slot="{ row }">
        <BTableColumn
          field="title"
          :label="$t('organization.sessions.table.title')"
          >{{ row.title }}</BTableColumn
        >
        <BTableColumn
          field="title"
          :label="$t('organization.sessions.table.date')"
          >{{ row.start | formatDate }}</BTableColumn
        >
        <BTableColumn
          field="title"
          :label="$t('organization.sessions.table.start')"
          >{{ row.start | formatHour }}</BTableColumn
        >
        <BTableColumn
          field="title"
          :label="$t('organization.sessions.table.end')"
          >{{ row.end | formatHour }}</BTableColumn
        >
        <BTableColumn
          field="title"
          :label="$t('organization.sessions.table.typeSession')"
          sortable
          >{{ row.typeSessionName }}</BTableColumn
        >
        <BTableColumn class="OrganizationMembers-column-manage" :width="200">
          <router-link
            :to="{
              name: 'dashboard-session-information',
              params: { id: row.id }
            }"
            class="button is-primary"
          >
            <BIcon icon="eye" />
            <span class="is-size-6">{{ $t("core.utils.see") }}</span>
          </router-link>
        </BTableColumn>
      </template>
    </BTable>
  </TemplateDashboard>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

import dayjs from "dayjs";

import TemplateDashboard from "@dashboard/template/TemplateDashboard";

export default {
  name: "DashboardSession",
  components: {
    TemplateDashboard
  },
  data() {
    const startDate = dayjs()
      .set("day", 1)
      .set("hour", 0)
      .set("minute", 0)
      .set("second", 0)
      .set("millisecond", 0);
    const endDate = dayjs()
      .set("day", 0)
      .set("hour", 23)
      .set("minute", 59)
      .set("second", 59)
      .set("millisecond", 0)
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
      });
  }
};
</script>

<style lang="scss"></style>
