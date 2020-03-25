<template>
  <OrganizationTemplateList
    :title="$t('organization.sessions.title')"
    @add="add"
    :loading="loading"
  >
    <BTable :data="sessions" striped paginated :per-page="25">
      <template v-slot="{ row }">
        <BTableColumn
          field="name"
          :label="$t('organization.sessions.table.name')"
          sortable
          >{{ row.name }}</BTableColumn
        >
        <BTableColumn class="OrganizationMembers-column-manage" :width="200">
          <OrganizationSessionsDropdown
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
      loading: true
    };
  },
  computed: {
    ...mapGetters({
      sessions: "organization/sessions"
    })
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
    }
  },
  mounted() {
    this.$store.dispatch("organization/setSessions").then(() => {
      this.$store.dispatch("organization/setTypeSessions").then(() => {
        this.loading = false;
      });
    });
  }
};
</script>

<style></style>
