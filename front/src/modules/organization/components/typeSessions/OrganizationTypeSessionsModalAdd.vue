<template>
  <TemplateModal
    :title="$t('organization.typeSessions.add.title')"
    :loading="loading"
    hasPadding
    @cancel="$emit('close')"
    @confirm="confirm"
  >
    <div class="columns">
      <div class="column is-half">
        <BField :label="$t('organization.typeSessions.label.name')">
          <BInput v-model="typeSession.name" />
        </BField>
      </div>
    </div>
  </TemplateModal>
</template>

<script>
"use strict";

import TemplateModal from "@core/template/TemplateModal";

export default {
  name: "OrganizationTypeSessionsModalAdd",
  components: {
    TemplateModal
  },
  data() {
    return {
      loading: false,
      typeSession: {
        name: ""
      }
    };
  },
  methods: {
    confirm() {
      this.loading = true;
      this.$store
        .dispatch("organization/addTypeSession", this.typeSession)
        .then(() => {
          this.$buefy.toast.open({
            message: this.$t("organization.typeSessions.add.success"),
            type: "is-primary"
          });
          this.$emit("close");
        });
    }
  }
};
</script>

<style lang="scss">
</style>