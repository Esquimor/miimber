<template>
  <TemplateSettings :title="$t('settings.account.title')"
    >Profile</TemplateSettings
  >
</template>

<script>
"use strict";

import TemplateSettings from "@settings/template/TemplateSettings";

export default {
  name: "Account",
  components: {
    TemplateSettings
  }
};
</script>

<style lang="scss"></style>
