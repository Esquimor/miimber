<template>
  <TemplateSidePanelRight
    :title="$t('organization.typeSessions.add.title')"
    :loading="loading"
    hasPadding
    @confirm="confirm"
    :disabled="!canConfirm"
  >
    <div class="columns">
      <div class="column is-half">
        <BField :label="$t('organization.typeSessions.label.name')">
          <BInput v-model.trim="typeSession.name" required />
        </BField>
      </div>
    </div>
  </TemplateSidePanelRight>
</template>

<script>
"use strict";

import TemplateSidePanelRight from "@core/template/TemplateSidePanelRight";

export default {
  name: "OrganizationTypeSessionsAdd",
  components: {
    TemplateSidePanelRight
  },
  data() {
    return {
      loading: false,
      typeSession: {
        name: ""
      }
    };
  },
  computed: {
    canConfirm() {
      return this.typeSession.name;
    }
  },
  methods: {
    confirm() {
      if (!this.canConfirm) return;
      this.loading = true;
      this.$store
        .dispatch("organization/addTypeSession", this.typeSession)
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
  }
};
</script>

<style lang="scss"></style>
