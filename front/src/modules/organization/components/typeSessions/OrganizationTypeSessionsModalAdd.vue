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
    confirm() {}
  }
};
</script>

<style lang="scss">
</style>