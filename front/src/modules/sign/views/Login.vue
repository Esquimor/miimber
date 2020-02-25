<template>
  <TemplateSign>
    <template v-slot:rigth>
      <form class="Login-form" @submit.prevent>
        <h1 class="Login-form-title is-size-3">{{ $t("login.utils.title") }}</h1>
        <BNotification
          v-if="error"
          class="Login-form-error"
          type="is-danger"
          aria-close-label="Close notification"
          role="alert"
        >{{ $t("login.utils.error") }}</BNotification>
        <BField :label="$t('login.email.label')">
          <BInput v-model="email" :placeholder="$t('login.email.placeholder')"></BInput>
        </BField>
        <BField :label="$t('login.password.label')">
          <BInput
            v-model="password"
            :placeholder="$t('login.password.placeholder')"
            type="password"
            password-reveal
          ></BInput>
        </BField>
        <BCheckbox class="Login-form-remember" v-model="remember">{{ $t("login.utils.remember") }}</BCheckbox>
        <div class="Login-form-submit">
          <button
            type="submit"
            class="button is-primary"
            :class="{ 'is-loading': loading }"
            @click="submit"
          >{{ $t("login.utils.submit") }}</button>
        </div>
      </form>
      <div class="Login-bottom">
        <span>
          {{ $t("login.register.label") }}
          <router-link :to="{ name: 'register' }">{{ $t("login.register.link") }}</router-link>
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
      remember: false,
      error: false
    };
  },
  methods: {
    submit() {
      this.loading = true;
      this.$store
        .dispatch("login", {
          email: this.email,
          password: this.password
        })
        .then(() => {
          this.$router.push({ name: "dashboard-home" });
        })
        .catch(() => {
          this.loading = false;
          this.password = "";
          this.error = true;
        });
    }
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
  }
  &-bottom {
    position: absolute;
    bottom: 10px;
  }
}
</style>
