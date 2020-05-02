<template>
  <TemplateSign>
    <template v-slot:rigth>
      <form class="Register-form" @submit.prevent>
        <h1 class="Register-form-title is-size-3">
          {{ $t("register.utils.title") }}
        </h1>
        <BNotification
          v-if="error"
          class="Register-form-error"
          type="is-danger"
          aria-close-label="Close notification"
          role="alert"
          >{{ $t("register.utils.error") }}</BNotification
        >
        <BField :label="$t('core.label.firstName.label')">
          <BInput
            id="SignRegister-firstName"
            v-model.trim="firstName"
            type="firstName"
            :placeholder="$t('core.label.firstName.placeholder')"
            required
          ></BInput>
        </BField>
        <BField :label="$t('core.label.lastName.label')">
          <BInput
            id="SignRegister-lastName"
            v-model.trim="lastName"
            type="lastName"
            :placeholder="$t('core.label.lastName.placeholder')"
            required
          ></BInput>
        </BField>
        <BField :label="$t('core.label.email.label')">
          <BInput
            id="SignRegister-email"
            v-model.trim="email"
            type="email"
            :placeholder="$t('core.label.email.placeholder')"
            required
          ></BInput>
        </BField>
        <BField
          :label="$t('core.label.password.label')"
          :type="errorSamePassword ? 'is-danger' : ''"
        >
          <BInput
            id="SignRegister-password"
            v-model="password"
            type="password"
            :placeholder="$t('core.label.password.placeholder')"
            password-reveal
            required
          ></BInput>
        </BField>
        <BField
          :label="$t('core.label.password.confirm')"
          :type="errorSamePassword ? 'is-danger' : ''"
          :message="errorSamePassword ? $t('core.label.password.notSame') : ''"
        >
          <BInput
            id="SignRegister-confirm"
            v-model="confirm"
            type="password"
            @blur="verifySamePassword"
            @focus="errorSamePassword = false"
            required
          ></BInput>
        </BField>
        <div class="Register-form-submit">
          <button
            id="SignRegister-submit"
            type="submit"
            class="button is-primary"
            :class="{ 'is-loading': loading }"
            @click="register"
            :disabled="!isRegistable"
          >
            {{ $t("register.utils.submit") }}
          </button>
        </div>
      </form>
      <div class="Register-bottom">
        <span>
          {{ $t("register.login.label") }}
          <router-link :to="{ name: 'login' }">
            {{ $t("register.login.link") }}
          </router-link>
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
      firstName: "",
      lastName: "",
      password: "",
      confirm: "",
      loading: false,
      errorSamePassword: false,
      error: false
    };
  },
  computed: {
    isRegistable() {
      return (
        this.email !== "" &&
        this.firstName !== "" &&
        this.lastName !== "" &&
        this.password !== "" &&
        !this.errorSamePassword
      );
    }
  },
  methods: {
    register() {
      if (this.password !== this.confirm) {
        this.errorSamePassword = true;
        return;
      }
      if (!this.isRegistable) return;
      if (this.loading) return;
      this.loading = true;
      this.$store
        .dispatch("sign/register", {
          email: this.email,
          firstName: this.firstName,
          lastName: this.lastName,
          password: this.password,
          lang: this.$i18n.locale.toUpperCase()
        })
        .then(() => {
          this.$router.push({ name: "register-completed" });
          this.loading = false;
        })
        .catch(() => {
          this.password = "";
          this.confirm = "";
          this.error = true;
          this.loading = false;
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
