<template>
  <TemplateForm
    @click="submit"
    :disabled="!isSubmitable"
    :loading="loading"
    :button="$t('register.utils.submit')"
  >
    <form @submit.prevent class="SignResetPassword-form">
      <BField
        :label="$t('core.label.password.label')"
        :type="errorSamePassword ? 'is-danger' : ''"
      >
        <BInput
          id="SignResetPassword-password"
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
          id="SignResetPassword-confirm"
          v-model="confirm"
          type="password"
          @blur="verifySamePassword"
          @focus="errorSamePassword = false"
          required
        ></BInput>
      </BField>
    </form>
  </TemplateForm>
</template>

<script>
"use strict";

import TemplateForm from "@core/template/TemplateForm";

export default {
  name: "SignResetPassword",
  components: {
    TemplateForm
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
