<template>
  <TemplateSettings :title="$t('settings.profile.title')">
    <form>
      <BField :label="$t('settings.profile.firstName')">
        <BInput v-model="editMe.firstName" />
      </BField>
      <BField :label="$t('settings.profile.lastName')">
        <BInput v-model="editMe.lastName" />
      </BField>
    </form>
  </TemplateSettings>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

import TemplateSettings from "@settings/template/TemplateSettings";

export default {
  name: "Profile",
  components: {
    TemplateSettings
  },
  data() {
    return {
      editMe: {
        firstName: ""
      }
    };
  },
  computed: {
    ...mapGetters({
      me: "core/me"
    })
  },
  mounted() {
    if (this.me) this.editMe = this.me;
  },
  watch: {
    me(newValue) {
      this.editMe = newValue;
    }
  }
};
</script>

<style lang="scss">
</style>