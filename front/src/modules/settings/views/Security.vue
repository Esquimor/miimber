<template>
  <TemplateSettings :title="$t('settings.security.title')">
    <form @submit.prevent>
      <BField :label="$t('settings.security.oldPassword')">
        <BInput type="password" v-model="oldPassword" password-reveal required></BInput>
      </BField>
      <BField :label="$t('settings.security.newPassword')">
        <BInput type="password" v-model="newPassword" password-reveal required></BInput>
      </BField>
      <BField :label="$t('settings.security.confirmPassword')">
        <BInput type="password" v-model="confirmPassword" password-reveal required></BInput>
      </BField>
      <div class="Security-form-button">
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
      loading: false
    };
  },
  methods: {
    changePassword() {}
  }
};
</script>

<style lang="scss">
</style>