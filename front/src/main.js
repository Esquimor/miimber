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

new Vue({
  router,
  store,
  i18n,
  render: h => h(App)
}).$mount("#app");
