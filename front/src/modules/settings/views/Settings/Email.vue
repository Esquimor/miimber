<template>
  <TemplateSettings :title="$t('settings.email.title')">
    <form @submit.prevent>
      <BField :label="$t('core.label.email.label')">
        <BInput
          id="SettingsEmail-email"
          v-model.trim="email"
          :placeholder="$t('core.label.email.placeholder')"
          type="email"
          required
        />
      </BField>
      <div class="SettingsEmail-form-button">
        <button
          id="SettingsEmail-update"
          class="button is-primary"
          :class="{ 'is-loading': loading }"
          @click="update"
          :disabled="!canUpdate"
        >
          {{ $t("core.utils.update") }}
        </button>
      </div>
    </form>
  </TemplateSettings>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

import { validEmail } from "@/utils/function";

import TemplateSettings from "@settings/template/TemplateSettings";

export default {
  name: "SettingsEmail",
  components: {
    TemplateSettings
  },
  data() {
    return {
      email: "",
      loading: false
    };
  },
  computed: {
    ...mapGetters({
      me: "core/me"
    }),
    canUpdate() {
      return !!this.email;
    }
  },
  methods: {
    update() {
      if (!validEmail(this.email)) return;
      if (!this.canUpdate) return;
      if (this.loading) return;
      this.loading = true;
      this.$store
        .dispatch("settings/updateEmail", {
          email: this.email,
          id: this.me.id,
          lang: this.$i18n.locale.toUpperCase()
        })
        .then(() => {
          this.loading = false;
        })
        .catch(() => {
          this.loading = false;
        });
    }
  },
  mounted() {
    if (this.me !== null) {
      this.$nextTick(() => {
        this.email = this.me.email;
      });
    }
  },
  watch: {
    me(newValue) {
      this.editMe = newValue;
    }
  }
};
</script>

<style lang="scss"></style>
