<template>
  <TemplateSettings :title="$t('settings.profile.title')">
    <form @submit.prevent>
      <BField :label="$t('settings.profile.email')">
        <BInput
          id="SettingsProfile-email"
          v-model="editMe.email"
          type="email"
          required
        />
      </BField>
      <BField :label="$t('settings.profile.firstName')">
        <BInput
          id="SettingsProfile-firstName"
          v-model="editMe.firstName"
          required
        />
      </BField>
      <BField :label="$t('settings.profile.lastName')">
        <BInput
          id="SettingsProfile-lastName"
          v-model="editMe.lastName"
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
          {{ $t("settings.profile.update") }}
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
      return (
        !!this.editMe.firstName && !!this.editMe.lastName && !!this.editMe.email
      );
    }
  },
  methods: {
    update() {
      if (!validEmail(this.editMe.email)) return;
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
        this.editMe.email = this.me.email;
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
