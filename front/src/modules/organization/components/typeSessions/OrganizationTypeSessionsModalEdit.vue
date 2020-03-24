<template>
  <TemplateModal
    :title="$t('organization.typeSessions.edit.title')"
    :loading="loading"
    hasPadding
    @cancel="$emit('close')"
    @confirm="confirm"
  >
    <div class="columns">
      <div class="column is-half">
        <BField :label="$t('organization.typeSessions.label.name')">
          <BInput v-model="typeSession.name" required />
        </BField>
      </div>
    </div>
  </TemplateModal>
</template>

<script>
"use strict";

import TemplateModal from "@core/template/TemplateModal";

export default {
  name: "OrganizationTypeSessionsModalEdit",
  components: {
    TemplateModal
  },
  props: {
    element: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      loading: false,
      typeSession: {
        id: 0,
        name: ""
      }
    };
  },
  methods: {
    confirm() {
      if (!this.typeSession.name) return;
      this.loading = true;
      this.$store
        .dispatch("organization/editTypeSession", this.typeSession)
        .then(() => {
          this.$buefy.toast.open({
            message: this.$t("organization.typeSessions.edit.success"),
            type: "is-primary"
          });
          this.$emit("close");
        });
    }
  },
  watch: {
    element: {
      immediate: true,
      handler(newVal) {
        this.typeSession = newVal;
      }
    }
  }
};
</script>

<style lang="scss"></style>
