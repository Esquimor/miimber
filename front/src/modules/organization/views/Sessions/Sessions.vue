<template>
  <OrganizationTemplateList
    :title="$t('organization.sessions.title')"
    @add="add"
    :loading="loading"
  >
    <div class="OrganizationSession-search">
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
          >{{ row.typeSession.name }}</BTableColumn
        >
        <BTableColumn class="OrganizationMembers-column-manage" :width="200">
          <OrganizationSessionsDropdown
            :id="row.id"
            @edit="edit(row)"
            @delete="deleteItem(row.id)"
          />
        </BTableColumn>
      </template>
    </BTable>
  </OrganizationTemplateList>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

import dayjs from "dayjs";

import OrganizationTemplateList from "@organization/templates/OrganizationTemplateList";

import OrganizationSessionsAdd from "@organization/components/sessions/OrganizationSessionsAdd";
import OrganizationSessionsEdit from "@organization/components/sessions/OrganizationSessionsEdit";

import OrganizationSessionsDropdown from "@organization/components/sessions/OrganizationSessionsDropdown";

export default {
  name: "OrganizationSessions",
  components: {
    OrganizationTemplateList,
    OrganizationSessionsDropdown
  },
  data() {
    return {
      loading: true,
      dates: [],
      search: ""
    };
  },
  computed: {
    ...mapGetters({
      sessions: "organization/sessions"
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
    add() {
      this.$store.dispatch("core/openSideBar", {
        component: OrganizationSessionsAdd
      });
    },
    edit(session) {
      this.$store.dispatch("core/openSideBar", {
        component: OrganizationSessionsEdit,
        props: { session }
      });
    },
    deleteItem(id) {
      this.$buefy.dialog.confirm({
        title: this.$t("organization.sessions.delete.title"),
        message: this.$t("organization.sessions.delete.message"),
        confirmText: this.$t("core.utils.delete"),
        cancelText: this.$t("core.utils.cancel"),
        type: "is-danger",
        hasIcon: true,
        onConfirm: () => {
          this.$store.dispatch("organization/deleteSession", id).then(() => {
            this.$buefy.toast.open({
              message: this.$t("organization.sessions.delete.success"),
              type: "is-primary"
            });
          });
        }
      });
    },
    setSessions() {
      this.loading = true;
      this.$store
        .dispatch("organization/setSessions", {
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
      .dispatch("organization/setSessions", {
        minDate: this.dates[0],
        maxDate: this.dates[1]
      })
      .then(() => {
        this.$store
          .dispatch("organization/setTypeSessions")
          .then(() => {
            this.loading = false;
          })
          .catch(() => {
            this.loading = false;
          });
      })
      .catch(() => {
        this.loading = false;
      });
  }
};
</script>

<style></style>
