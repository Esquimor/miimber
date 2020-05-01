<template>
  <TemplateInfo :size="CONTENT_SIZE.SMALL">
    <h1 class="title is-5">{{ $t("sign.registerCompleted.title") }}</h1>
    <span class="is-size-6">{{ $t("sign.registerCompleted.message") }}</span>
    <div class="SignRegisterCompleted-button">
      <router-link :to="{ name: 'login' }" class="button is-primary">{{
        $t("sign.registerCompleted.login")
      }}</router-link>
    </div>
  </TemplateInfo>
</template>

<script>
"use strict";

import { CONTENT_SIZE } from "@/utils/consts";

import TemplateInfo from "@core/template/TemplateInfo";

export default {
  name: "SignRegisterCompleted",
  components: {
    TemplateInfo
  },
  data() {
    return {
      CONTENT_SIZE: CONTENT_SIZE
    };
  }
};
</script>

<style lang="scss">
.SignRegisterCompleted {
  &-button {
    display: flex;
    justify-content: center;
    margin-top: 1rem;
  }
}
</style>
