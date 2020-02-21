<template>
  <TemplateSign>
    <template v-slot:rigth>
      <form class="Login-form" @submit.prevent>
        <h1 class="Login-form-title is-size-3">
          {{ $t("register.utils.title") }}
        </h1>
        <BField :label="$t('register.email.label')">
          <BInput
            v-model="email"
            :placeholder="$t('register.email.placeholder')"
          ></BInput>
        </BField>
        <BField :label="$t('register.password.label')">
          <BInput v-model="password" type="password" password-reveal></BInput>
        </BField>
        <BField :label="$t('register.password.confirm')">
          <BInput v-model="confirm" type="password"></BInput>
        </BField>
        <div class="Login-form-submit">
          <button
            type="submit"
            class="button is-primary"
            :class="{ 'is-loading': loading }"
            @click="register"
          >
            {{ $t("register.utils.submit") }}
          </button>
        </div>
      </form>
      <div class="Login-bottom">
        <span
          >{{ $t("register.login.label") }}
          <router-link :to="{ name: 'login' }"
            >{{ $t("register.login.link") }}
          </router-link>
        </span>
      </div>
    </template></TemplateSign
  >
</template>

<script>
"use strict";

import TemplateSign from "@/template/TemplateSign";

export default {
  name: "Register",
  components: {
    TemplateSign
  },
  data() {
    return {
      email: "",
      password: "",
      confirm: "",
      loading: false
    };
  },
  methods: {
    register() {}
  }
};
</script>

<style lang="scss"></style>
