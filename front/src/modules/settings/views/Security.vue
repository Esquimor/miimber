<template>
  <TemplateSettings :title="$t('settings.security.title')">
    <form @submit.prevent>
      <BField :label="$t('settings.security.oldPassword')">
        <BInput type="password" v-model="oldPassword" password-reveal required></BInput>
      </BField>
      <BField
        :label="$t('settings.security.newPassword')"
        :type="errorSamePassword ? 'is-danger' : ''"
      >
        <BInput
          type="password"
          v-model="newPassword"
          @input="errorSamePassword = false"
          password-reveal
          required
        ></BInput>
      </BField>
      <BField
        :label="$t('settings.security.confirmPassword')"
        :type="errorSamePassword ? 'is-danger' : ''"
        :message="errorSamePassword ? $t('settings.security.notSame') : ''"
      >
        <BInput
          type="password"
          v-model="confirmPassword"
          @blur="verifySamePassword"
          @input="errorSamePassword = false"
          password-reveal
          required
        ></BInput>
      </BField>
      <div class="SettingsSecurity-form-button">
        <button
          class="button is-primary"
          :class="{'is-loading': loading}"
          @click="changePassword"
        >{{ $t('settings.security.change') }}</button>
      </div>
    </form>
  </TemplateSettings>
</template>


<script>
"use strict";

import { mapGetters } from "vuex";

import TemplateSettings from "@settings/template/TemplateSettings";

export default {
  name: "Security",
  components: {
    TemplateSettings
  },
  data() {
    return {
      oldPassword: "",
      newPassword: "",
      confirmPassword: "",
      loading: false,
      errorSamePassword: false
    };
  },
  computed: {
    ...mapGetters({
      me: "core/me"
    })
  },
  methods: {
    changePassword() {
      if (this.errorSamePassword) return;
      this.loading = true;
      this.$store
        .dispatch("settings/updatePassword", {
          newPassword: this.newPassword,
          oldPassword: this.oldPassword,
          id: this.me.id
        })
        .then(() => {
          this.loading = false;
          this.$buefy.toast.open({
            message: this.$t("settings.security.success"),
            type: "is-success"
          });
        })
        .catch(() => {
          this.loading = false;
        });
    },
    verifySamePassword() {
      return (this.errorSamePassword =
        this.newPassword !== this.confirmPassword);
    }
  }
};
</script>

<style lang="scss">
</style>