<template>
  <OrganizationTemplateList
    :title="$t('organization.typeSessions.title')"
    @add="add"
    :loading="loading"
  >
    <BTable :data="typeSessions" striped paginated :per-page="25">
      <template v-slot="{ row }">
        <BTableColumn
          field="name"
          :label="$t('organization.typeSessions.table.name')"
          sortable
        >{{ row.name }}</BTableColumn>
        <BTableColumn class="OrganizationMembers-column-manage" :width="200">
          <OrganizationTypeSessionsDropdown @edit="edit(row)" @delete="deleteItem(row)" />
        </BTableColumn>
      </template>
    </BTable>
  </OrganizationTemplateList>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

import OrganizationTemplateList from "@organization/templates/OrganizationTemplateList";

import OrganizationTypeSessionsModalAdd from "@organization/components/typeSessions/OrganizationTypeSessionsModalAdd";
import OrganizationTypeSessionsModalEdit from "@organization/components/typeSessions/OrganizationTypeSessionsModalEdit";

import OrganizationTypeSessionsDropdown from "@organization/components/typeSessions/OrganizationTypeSessionsDropdown";

export default {
  name: "OrganizationSessionsTypeSessions",
  components: {
    OrganizationTemplateList,
    OrganizationTypeSessionsDropdown
  },
  data() {
    return {
      loading: true
    };
  },
  computed: {
    ...mapGetters({
      typeSessions: "organization/typeSessions"
    })
  },
  methods: {
    add() {
      this.$buefy.modal.open({
        parent: this,
        component: OrganizationTypeSessionsModalAdd,
        canCancel: false
      });
    },
    edit(element) {
      this.$buefy.modal.open({
        parent: this,
        component: OrganizationTypeSessionsModalEdit,
        canCancel: false,
        props: { element }
      });
    },
    deleteItem() {}
  },
  mounted() {
    this.$store.dispatch("organization/setTypeSession").then(() => {
      this.loading = false;
    });
  }
};
</script>

<style lang="scss">
</style>