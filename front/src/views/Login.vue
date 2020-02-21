<template>
  <TemplateSign>
    <template v-slot:rigth>
      <form class="Login-form" @submit.prevent>
        <h1 class="Login-form-title is-size-3">
          {{ $t("login.utils.title") }}
        </h1>
        <BField :label="$t('login.email.label')">
          <BInput
            v-model="email"
            :placeholder="$t('login.email.placeholder')"
          ></BInput>
        </BField>
        <BField :label="$t('login.password.label')">
          <BInput
            v-model="password"
            :placeholder="$t('login.password.placeholder')"
            type="password"
            password-reveal
          ></BInput>
        </BField>
        <BCheckbox class="Login-form-remember" v-model="remember">
          {{ $t("login.utils.remember") }}
        </BCheckbox>
        <div class="Login-form-submit">
          <button
            type="submit"
            class="button is-primary"
            :class="{ 'is-loading': loading }"
            @click="submit"
          >
            {{ $t("login.utils.submit") }}
          </button>
        </div>
      </form>
      <div class="Login-bottom">
        <span
          >{{ $t("login.register.label") }}
          <a href="#">{{ $t("login.register.link") }}</a></span
        >
      </div>
    </template>
    <template v-slot:left>
      <div class="Login-img" />
    </template>
  </TemplateSign>
</template>

<script>
"use strict";

import TemplateSign from "@/template/TemplateSign";

export default {
  name: "Login",
  components: {
    TemplateSign
  },
  data() {
    return {
      loading: false,
      email: "",
      password: "",
      remember: false
    };
  }
};
</script>

<style lang="scss">
.Login {
  &-form {
    display: flex;
    flex-direction: column;
    border-radius: 10px;
    &-title {
      text-align: center;
      padding-bottom: 2rem;
    }
    &-submit {
      display: flex;
      justify-content: center;
      padding-top: 1rem;
      width: 100%;
    }
    &-remember {
      padding-top: 0.5rem;
    }
  }
  &-bottom {
    position: absolute;
    bottom: 10px;
  }
  &-img {
    width: 100%;
    height: 100%;
    background: linear-gradient(#1565c0, #42a5f5);
  }
}
</style>
