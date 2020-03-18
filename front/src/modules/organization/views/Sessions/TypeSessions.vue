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
      </template>
    </BTable>
  </OrganizationTemplateList>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

import OrganizationTemplateList from "@organization/templates/OrganizationTemplateList";

import OrganizationTypeSessionsModalAdd from "@organization/components/typeSessions/OrganizationTypeSessionsModalAdd";

export default {
  name: "OrganizationSessionsTypeSessions",
  components: {
    OrganizationTemplateList
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
    }
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