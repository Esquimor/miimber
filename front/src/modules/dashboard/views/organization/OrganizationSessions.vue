<template>
  <div v-if="!loading">
    <div class="OrganizationSession-search">
      <div class="columns">
        <div class="column">
          <BField :label="$t('organization.sessions.label.search')">
            <BInput v-model="search" type="search"></BInput>
          </BField>
        </div>
        <div class="column">
          <BField :label="$t('organization.sessions.label.between')">
            <BDatepicker v-model="dates" range @input="setSessions" :nearbyMonthDays="false"></BDatepicker>
          </BField>
        </div>
      </div>
    </div>
    <BTable :data="filteredSession" striped paginated :per-page="25">
      <template v-slot="{ row }">
        <BTableColumn field="title" :label="$t('organization.sessions.table.title')">{{ row.title }}</BTableColumn>
        <BTableColumn
          field="title"
          :label="$t('organization.sessions.table.date')"
        >{{ row.start | formatDate }}</BTableColumn>
        <BTableColumn
          field="title"
          :label="$t('organization.sessions.table.start')"
        >{{ row.start | formatHour }}</BTableColumn>
        <BTableColumn
          field="title"
          :label="$t('organization.sessions.table.end')"
        >{{ row.end | formatHour }}</BTableColumn>
        <BTableColumn
          field="title"
          :label="$t('organization.sessions.table.typeSession')"
          sortable
        >{{ row.typeSession.name }}</BTableColumn>
        <BTableColumn class="OrganizationMembers-column-manage" :width="200"></BTableColumn>
      </template>
    </BTable>
  </div>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

import dayjs from "dayjs";

export default {
  name: "DashboardOrganizationSessions",
  data() {
    return {
      loading: true,
      dates: [],
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
    this.dates = [
      dayjs().toDate(),
      dayjs()
        .add(1, "month")
        .toDate()
    ];
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
</style>