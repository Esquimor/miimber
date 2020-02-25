import Vue from "vue";
import VueI18n from "vue-i18n";

Vue.use(VueI18n);

const dashboard = () => import("@dashboard/locales/index.js");
const home = () => import("@home/locales/index.js");
const sign = () => import("@sign/locales/index.js");

const messages = Object.assign({}, home, dashboard, sign);

export default new VueI18n({
  locale: window.navigator.language || process.env.VUE_APP_I18N_LOCALE || "en",
  fallbackLocale: process.env.VUE_APP_I18N_FALLBACK_LOCALE || "en",
  messages: messages
});
