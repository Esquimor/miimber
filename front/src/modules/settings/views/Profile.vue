<template>
  <TemplateSettings :title="$t('settings.profile.title')">
    <form @submit.prevent>
      <BField :label="$t('settings.profile.email')">
        <BInput v-model="editMe.email" type="email" required />
      </BField>
      <BField :label="$t('settings.profile.firstName')">
        <BInput v-model="editMe.firstName" />
      </BField>
      <BField :label="$t('settings.profile.lastName')">
        <BInput v-model="editMe.lastName" />
      </BField>
      <div class="SettingsProfile-form-button">
        <button
          class="button is-primary"
          :class="{ 'is-loading': loading }"
          @click="update"
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
    })
  },
  methods: {
    update() {
      this.loading = true;
      this.$store
        .dispatch("settings/updateProfile", {
          firstName: this.editMe.firstName,
          lastName: this.editMe.lastName,
          id: this.me.id
        })
        .then(() => {
          this.loading = false;
          this.$buefy.toast.open({
            message: this.$t("settings.profile.success"),
            type: "is-success"
          });
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
