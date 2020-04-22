<template>
  <TemplateInfo>
    <form @submit.prevent class="SignResetPassword-form">
      <BField
        :label="$t('register.password.label')"
        :type="errorSamePassword ? 'is-danger' : ''"
      >
        <BInput
          id="SignResetPassword-password"
          v-model="password"
          type="password"
          password-reveal
          required
        ></BInput>
      </BField>
      <BField
        :label="$t('register.password.confirm')"
        :type="errorSamePassword ? 'is-danger' : ''"
        :message="errorSamePassword ? $t('register.password.notSame') : ''"
      >
        <BInput
          id="SignResetPassword-confirm"
          v-model="confirm"
          type="password"
          @blur="verifySamePassword"
          @focus="errorSamePassword = false"
          required
        ></BInput>
      </BField>
      <div class="SignResetPassword-form-submit">
        <button
          id="SignResetPassword-submit"
          type="submit"
          class="button is-primary"
          :class="{ 'is-loading': loading }"
          @click="submit"
          :disabled="!isSubmitable"
        >
          {{ $t("register.utils.submit") }}
        </button>
      </div>
    </form>
  </TemplateInfo>
</template>

<script>
"use strict";

import TemplateInfo from "@core/template/TemplateInfo";

export default {
  name: "SignResetPassword",
  components: {
    TemplateInfo
  },
  data() {
    return {
      password: "",
      confirm: "",
      loading: false,
      errorSamePassword: false
    };
  },
  computed: {
    isSubmitable() {
      return (
        this.password !== "" &&
        this.confirm !== "" &&
        this.password === this.confirm
      );
    }
  },
  methods: {
    submit() {
      if (!this.isSubmitable) return;
      if (this.loading) return;
      this.loading = true;
      this.$store
        .dispatch("sign/resetPassword", {
          password: this.password,
          idUser: this.$route.query.id,
          token: this.$route.query.token
        })
        .then(() => {
          this.loading = false;
          this.$router.push({ name: "login" });
        })
        .catch(() => {
          this.loading = false;
        });
    },
    verifySamePassword() {
      this.errorSamePassword = this.password !== this.confirm;
    }
  }
};
</script>

<style lang="scss"></style>
