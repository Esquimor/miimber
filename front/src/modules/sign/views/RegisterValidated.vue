<template>
  <TemplateVerification :state="state"></TemplateVerification>
</template>

<script>
"use strict";

import { STATE_VALIDATION } from "@/utils/consts";

import TemplateVerification from "@core/template/TemplateVerification";

export default {
  name: "SignRegisterValidated",
  components: {
    TemplateVerification
  },
  data() {
    return {
      state: STATE_VALIDATION.WAIT
    };
  },
  mounted() {
    const token = this.$route.query.token;
    const id = this.$route.query.id;
    if (!id || !token) {
      this.state = STATE_VALIDATION.ERROR;
      return;
    }
    this.$store
      .dispatch("sign/registerValidated", {
        token,
        id
      })
      .then(() => {
        this.state = STATE_VALIDATION.SUCESS;
        this.$router.push({ name: "login" });
      })
      .catch(() => {
        this.state = STATE_VALIDATION.ERROR;
      });
  }
};
</script>

<style lang="scss">
.SignRegisterValidated {
}
</style>
