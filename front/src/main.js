import Vue from "vue";
import App from "./App.vue";
import "./registerServiceWorker";
import router from "./router";
import store from "./store";

import Buefy from "buefy";

import "@/style/app.scss";

Vue.use(Buefy);

import i18n from "./i18n";

Vue.config.productionTip = false;

import Cleave from "cleave.js";

Vue.directive("cleave", {
  inserted: (el, binding) => {
    el.cleave = new Cleave(el, binding.value || {});
  },
  update: el => {
    const event = new Event("input", { bubbles: true });
    setTimeout(function() {
      el.value = el.cleave.properties.result;
      el.dispatchEvent(event);
    }, 100);
  }
});

import dayjs from "dayjs";

Vue.filter("formatHour", function(value) {
  if (!value) return "";
  const date = new Date(value);
  return dayjs(date).format("HH:mm");
});

Vue.filter("formatDate", function(value) {
  if (!value) return "";
  const date = new Date(value);
  return dayjs(date).format("DD/MM/YYYY");
});

new Vue({
  router,
  store,
  i18n,
  render: h => h(App)
}).$mount("#app");
