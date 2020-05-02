<template>
  <TemplateSign>
    <template v-slot:rigth>
      <form class="SignPasswordForgotten-form" @submit.prevent>
        <h1 class="SignPasswordForgotten-form-title is-size-3">
          {{ $t("sign.passwordForgotten.title") }}
        </h1>
        <BField :label="$t('core.label.email.label')">
          <BInput
            id="SignPasswordForgotten-email"
            type="email"
            v-model.trim="email"
            :placeholder="$t('core.label.email.placeholder')"
            required
          ></BInput>
        </BField>
        <div class="SignLogin-form-submit">
          <button
            id="SignLogin-submit"
            type="submit"
            class="button is-primary"
            :class="{ 'is-loading': loading }"
            @click="send"
            :disabled="!isSendable"
          >
            {{ $t("core.utils.send") }}
          </button>
        </div>
      </form>
    </template>
  </TemplateSign>
</template>

<script>
"use strict";

import TemplateSign from "@sign/template/TemplateSign";

export default {
  name: "SignPasswordForgotten",
  components: {
    TemplateSign
  },
  data() {
    return {
      email: "",
      loading: false
    };
  },
  computed: {
    isSendable() {
      return this.email !== "";
    }
  },
  methods: {
    send() {
      if (!this.isSendable) return;
      if (this.loading) return;
      this.loading = true;
      this.$store
        .dispatch("sign/passwordForgotten", {
          email: this.email,
          lang: this.$i18n.locale.toUpperCase()
        })
        .then(() => {
          this.loading = false;
        })
        .catch(() => {
          this.loading = false;
        });
    }
  }
};
</script>

<style lang="scss"></style>
