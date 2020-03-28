import Vue from "vue";
import VueI18n from "vue-i18n";

Vue.use(VueI18n);

import core from "@core/locales/index.js";
import dashboard from "@dashboard/locales/index.js";
import home from "@home/locales/index.js";
import sign from "@sign/locales/index.js";
import settings from "@settings/locales/index.js";
import organization from "@organization/locales/index.js";

const fr = Object.assign(
  {},
  core.fr,
  dashboard.fr,
  home.fr,
  sign.fr,
  settings.fr,
  organization.fr
);
const en = Object.assign(
  {},
  core.en,
  dashboard.en,
  home.en,
  sign.en,
  settings.en,
  organization.rn
);

const messages = {
  fr,
  en
};

export default new VueI18n({
  locale: window.navigator.language || process.env.VUE_APP_I18N_LOCALE || "en",
  fallbackLocale: process.env.VUE_APP_I18N_FALLBACK_LOCALE || "en",
  messages: messages
});
