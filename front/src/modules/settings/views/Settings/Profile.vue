<template>
  <TemplateSettings :title="$t('settings.profile.title')">
    <form @submit.prevent>
      <BField :label="$t('core.label.firstName.label')">
        <BInput
          id="SettingsProfile-firstName"
          v-model.trim="editMe.firstName"
          :placeholder="$t('core.label.firstName.placeholder')"
          required
        />
      </BField>
      <BField :label="$t('core.label.lastName.label')">
        <BInput
          id="SettingsProfile-lastName"
          v-model.trim="editMe.lastName"
          :placeholder="$t('core.label.lastName.placeholder')"
          required
        />
      </BField>
      <div class="SettingsProfile-form-button">
        <button
          id="SettingsProfile-update"
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

import TemplateSettings from "@settings/template/TemplateSettings";

export default {
  name: "Profile",
  components: {
    TemplateSettings
  },
  data() {
    return {
      editMe: {
        firstName: "",
        lastName: ""
      },
      loading: false
    };
  },
  computed: {
    ...mapGetters({
      me: "core/me"
    }),
    canUpdate() {
      return !!this.editMe.firstName && !!this.editMe.lastName;
    }
  },
  methods: {
    update() {
      if (!this.canUpdate) return;
      if (this.loading) return;
      this.loading = true;
      this.$store
        .dispatch("settings/updateProfile", {
          firstName: this.editMe.firstName,
          lastName: this.editMe.lastName,
          id: this.me.id
        })
        .then(() => {
          this.$buefy.toast.open({
            message: this.$t("settings.profile.success"),
            type: "is-success"
          });
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
        this.editMe.firstName = this.me.firstName;
        this.editMe.lastName = this.me.lastName;
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
