import Vue from "vue";
import VueI18n from "vue-i18n";

Vue.use(VueI18n);

const core = () => import("@core/locales/index.js");
const dashboard = () => import("@dashboard/locales/index.js");
const presentation = () => import("@presentation/locales/index.js");
const sign = () => import("@sign/locales/index.js");
const settings = () => import("@settings/locales/index.js");
const organization = () => import("@organization/locales/index.js");
const error = () => import("@error/locales/index.js");

const fr = Object.assign(
  {},
  core.fr,
  dashboard.fr,
  presentation.fr,
  sign.fr,
  settings.fr,
  organization.fr,
  error.fr
);
const en = Object.assign(
  {},
  core.en,
  dashboard.en,
  presentation.en,
  sign.en,
  settings.en,
  organization.en,
  error.en
);

const messages = {
  fr,
  en,
};

export default new VueI18n({
  locale:
    localStorage.getItem("lang") ||
    window.navigator.language ||
    process.env.VUE_APP_I18N_LOCALE ||
    "en",
  fallbackLocale: process.env.VUE_APP_I18N_FALLBACK_LOCALE || "en",
  messages: messages,
});
