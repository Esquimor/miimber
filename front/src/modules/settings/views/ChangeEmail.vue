<template>
  <TemplateInfo>Email Changé</TemplateInfo>
</template>

<script>
"use strict";

import TemplateInfo from "@core/template/TemplateInfo";

export default {
  name: "SetingsChangeEmail",
  components: {
    TemplateInfo
  },
  mounted() {
    const id = this.$route.query.id;
    const token = this.$router.query.token;
    this.$store
      .dispatch("settings/valideChangeEmail", {
        id,
        token
      })
      .then(() => {})
      .catch(() => {});
  }
};
</script>

<style lang="scss"></style>
