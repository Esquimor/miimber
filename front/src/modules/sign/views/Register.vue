<template>
  <TemplateSign>
    <template v-slot:rigth>
      <form class="Register-form" @submit.prevent>
        <h1 class="Register-form-title is-size-3">{{ $t("register.utils.title") }}</h1>
        <BNotification
          v-if="error"
          class="Register-form-error"
          type="is-danger"
          aria-close-label="Close notification"
          role="alert"
        >{{ $t("register.utils.error") }}</BNotification>
        <BField :label="$t('register.email.label')">
          <BInput v-model="email" type="email" :placeholder="$t('register.email.placeholder')"></BInput>
        </BField>
        <BField :label="$t('register.password.label')" :type="errorSamePassword ? 'is-danger' : ''">
          <BInput v-model="password" type="password" password-reveal></BInput>
        </BField>
        <BField
          :label="$t('register.password.confirm')"
          :type="errorSamePassword ? 'is-danger' : ''"
          :message="errorSamePassword ? $t('register.password.notSame') : ''"
        >
          <BInput
            v-model="confirm"
            type="password"
            @blur="verifySamePassword"
            @focus="errorSamePassword = false"
          ></BInput>
        </BField>
        <div class="Register-form-submit">
          <button
            type="submit"
            class="button is-primary"
            :class="{ 'is-loading': loading }"
            @click="register"
            :disabled="!isRegistable"
          >{{ $t("register.utils.submit") }}</button>
        </div>
      </form>
      <div class="Register-bottom">
        <span>
          {{ $t("register.login.label") }}
          <router-link :to="{ name: 'login' }">{{ $t("register.login.link") }}</router-link>
        </span>
      </div>
    </template>
  </TemplateSign>
</template>

<script>
"use strict";

import TemplateSign from "@sign/template/TemplateSign";

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
      loading: false,
      errorSamePassword: false,
      error: true
    };
  },
  computed: {
    isRegistable() {
      return (
        this.email !== "" && this.password !== "" && !this.errorSamePassword
      );
    }
  },
  methods: {
    register() {
      if (this.password !== this.confirm) {
        this.errorSamePassword = true;
        return;
      }
      this.loading = true;
      this.$store
        .dispatch("sign/register", {
          email: this.email,
          password: this.password
        })
        .catch(() => {
          this.loading = false;
          this.password = "";
          this.confirm = "";
          this.error = true;
        });
    },
    verifySamePassword() {
      this.errorSamePassword = this.password !== this.confirm;
    }
  }
};
</script>

<style lang="scss">
.Register {
  &-form {
    display: flex;
    flex-direction: column;
    border-radius: 10px;
    &-title {
      text-align: center;
      padding-bottom: 2rem;
    }
    &-error {
      width: 246px;
    }
    &-submit {
      display: flex;
      justify-content: center;
      padding-top: 1rem;
      width: 100%;
    }
  }
  &-bottom {
    position: absolute;
    bottom: 10px;
  }
}
</style>
