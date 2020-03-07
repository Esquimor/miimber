<template>
  <TemplateModal
    :title="$t('organization.members.add.title')"
    :loading="loading"
    @cancel="$emit('close')"
    @confirm="confirm"
  ></TemplateModal>
</template>

<script>
"use strict";

import TemplateModal from "@core/template/TemplateModal";

export default {
  name: "OrganizationMembersAdd",
  components: {
    TemplateModal
  },
  data() {
    return {
      loading: false
    };
  },
  methods: {
    confirm() {}
  }
};
</script>

<style lang="scss">
</style>