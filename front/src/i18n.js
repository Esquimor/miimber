import Vue from "vue";
import VueI18n from "vue-i18n";

Vue.use(VueI18n);

import dashboard from "@dashboard/locales/index.js";
import home from "@home/locales/index.js";
import sign from "@sign/locales/index.js";

const fr = Object.assign({}, dashboard.fr, home.fr, sign.fr);
const en = Object.assign({}, dashboard.en, home.en, sign.en);

const messages = Object.assign({}, { fr }, { en });

export default new VueI18n({
  locale: window.navigator.language || process.env.VUE_APP_I18N_LOCALE || "en",
  fallbackLocale: process.env.VUE_APP_I18N_FALLBACK_LOCALE || "en",
  messages: messages
});
