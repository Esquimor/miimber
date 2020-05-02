<template>
  <TemplateVerification :state="state">
    {{ $t("settings.changeEmail.label") }}
  </TemplateVerification>
</template>

<script>
"use strict";

import { STATE_VALIDATION } from "@/utils/consts";

import TemplateVerification from "@core/template/TemplateVerification";

export default {
  name: "SetingsChangeEmail",
  components: {
    TemplateVerification
  },
  data() {
    return {
      error: false,
      state: STATE_VALIDATION.WAIT
    };
  },
  mounted() {
    const id = this.$route.query.id;
    const token = this.$route.query.token;
    if (!id || !token) {
      this.state = STATE_VALIDATION.ERROR;
      return;
    }
    this.$store
      .dispatch("settings/valideChangeEmail", {
        id,
        token
      })
      .then(() => {
        this.state = STATE_VALIDATION.SUCCESS;
      })
      .catch(() => {
        this.state = STATE_VALIDATION.ERROR;
      });
  }
};
</script>

<style lang="scss"></style>
