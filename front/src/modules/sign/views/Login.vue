<template>
  <TemplateSign>
    <template v-slot:rigth>
      <form class="SignLogin-form" @submit.prevent>
        <h1 class="SignLogin-form-title is-size-3">
          {{ $t("login.utils.title") }}
        </h1>
        <BNotification
          v-if="error"
          class="SignLogin-form-error"
          type="is-danger"
          aria-close-label="Close notification"
          role="alert"
          >{{ $t("login.utils.error") }}</BNotification
        >
        <BField :label="$t('core.label.email.label')">
          <BInput
            id="SignLogin-email"
            type="email"
            v-model.trim="email"
            :placeholder="$t('core.label.email.placeholder')"
            required
          ></BInput>
        </BField>
        <BField :label="$t('core.label.password.label')">
          <BInput
            id="SignLogin-password"
            v-model="password"
            :placeholder="$t('core.label.password.placeholder')"
            type="password"
            password-reveal
            required
          ></BInput>
        </BField>
        <div class="SignLogin-form-submit">
          <button
            id="SignLogin-submit"
            type="submit"
            class="button is-primary"
            :class="{ 'is-loading': loading }"
            @click="submit"
            :disabled="!isLoggable"
          >
            {{ $t("login.utils.submit") }}
          </button>
        </div>
        <router-link
          :to="{ name: 'password-forgotten' }"
          class="SignLogin-form-passwordForgotten"
          >{{ $t("sign.passwordForgotten.link") }}</router-link
        >
      </form>
      <div class="SignLogin-bottom">
        <span>
          {{ $t("login.register.label") }}
          <router-link :to="{ name: 'register' }">
            {{ $t("login.register.link") }}
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
  name: "Login",
  components: {
    TemplateSign
  },
  data() {
    return {
      loading: false,
      email: "",
      password: "",
      error: false
    };
  },
  computed: {
    isLoggable() {
      return this.email !== "" && this.password !== "";
    }
  },
  methods: {
    submit() {
      if (!this.isLoggable) return;
      if (this.loading) return;
      this.loading = true;
      this.$store
        .dispatch("sign/login", {
          email: this.email,
          password: this.password
        })
        .then(() => {
          this.$router.push({ name: "dashboard-sessions" });
          this.$buefy.toast.open({
            message: this.$t("login.success"),
            type: "is-success"
          });
          this.loading = false;
        })
        .catch(() => {
          this.password = "";
          this.error = true;
          this.loading = false;
        });
    }
  }
};
</script>

<style lang="scss">
.SignLogin {
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
    &-remember {
      padding-top: 0.5rem;
    }
    &-passwordForgotten {
      margin: 1rem 0;
    }
  }
  &-bottom {
    position: absolute;
    bottom: 10px;
  }
}
</style>
