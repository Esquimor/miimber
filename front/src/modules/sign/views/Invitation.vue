<template>
  <TemplateForm
    title="Invitation"
    @click="submit"
    :disabled="!isSubmitable"
    :loading="loading"
    :button="$t('register.utils.submit')"
  >
    <form @submit.prevent="submit" class="SignInvitation-form">
      <BField :label="$t('core.label.firstName.label')">
        <BInput
          id="SignInvitation-firstName"
          v-model.trim="firstName"
          :placeholder="$t('core.label.firstName.placeholder')"
          required
        />
      </BField>
      <BField :label="$t('core.label.lastName.label')">
        <BInput
          id="SignInvitation-lastName"
          v-model.trim="lastName"
          :placeholder="$t('core.label.lastName.placeholder')"
          required
        />
      </BField>
      <BField
        :label="$t('core.label.password.label')"
        :type="errorSamePassword ? 'is-danger' : ''"
      >
        <BInput
          id="SignInvitation-password"
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
          id="SignInvitation-confirm"
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
  name: "SignInvitation",
  components: {
    TemplateForm
  },
  data() {
    return {
      firstName: "",
      lastName: "",
      password: "",
      confirm: "",
      loading: false,
      errorSamePassword: false
    };
  },
  computed: {
    isSubmitable() {
      return (
        this.firstName !== "" &&
        this.lastName !== "" &&
        this.password !== "" &&
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
        .dispatch("sign/invitUser", {
          id: this.$route.query.id,
          token: this.$route.query.token,
          firstName: this.firstName,
          lastName: this.lastName,
          password: this.password,
          lang: this.$i18n.locale.toUpperCase()
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
