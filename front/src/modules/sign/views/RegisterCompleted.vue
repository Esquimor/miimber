<template>
  <TemplateDefault>
    <div class="SignRegisterCompleted">
      <div class="SignRegisterCompleted-wrapper">
        <h1 class="title is-5">{{ $t("sign.registerCompleted.title") }}</h1>
        <span class="is-size-6">
          {{ $t("sign.registerCompleted.message") }}
        </span>
        <div class="SignRegisterCompleted-button">
          <router-link :to="{ name: 'login' }" class="button is-primary">{{
            $t("sign.registerCompleted.login")
          }}</router-link>
        </div>
      </div>
    </div>
  </TemplateDefault>
</template>

<script>
"use strict";

import TemplateDefault from "@core/template/TemplateDefault";

export default {
  name: "SignRegisterCompleted",
  components: {
    TemplateDefault
  }
};
</script>

<style lang="scss">
.SignRegisterCompleted {
  display: flex;
  justify-content: center;
  align-items: center;
  &-wrapper {
    padding: 1rem;
    max-width: 960px;
    width: 100%;
    margin: 2rem auto;
    background-color: $white;
    border: 1px solid $grey-lightest;
    box-shadow: 0 4px 6px 0 $grey;
    border-radius: 5px;
  }
  &-button {
    display: flex;
    justify-content: center;
    margin-top: 1rem;
  }
}
</style>
