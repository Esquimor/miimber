<template>
  <TemplateInfo>
    <h1 class="title is-5">{{ $t("sign.registerValidated.title") }}</h1>
    <span class="is-size-6">{{ $t("sign.registerValidated.message") }}</span>
  </TemplateInfo>
</template>

<script>
"use strict";

import TemplateInfo from "@core/template/TemplateInfo";

export default {
  name: "SignRegisterValidated",
  components: {
    TemplateInfo
  },
  data() {
    return {
      error: false
    };
  },
  mounted() {
    const token = this.$route.query.token;
    const id = this.$route.query.id;
    this.$store
      .dispatch("sign/registerValidated", {
        token,
        id
      })
      .then(() => {
        this.$router.push({ name: "login" });
      })
      .catch(() => {
        this.error = true;
      });
  }
};
</script>

<style lang="scss">
.SignRegisterValidated {
}
</style>
