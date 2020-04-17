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
  mounted() {
    const token = this.$route.params.token;
    console.log(token);
  }
};
</script>

<style lang="scss">
.SignRegisterValidated {
}
</style>