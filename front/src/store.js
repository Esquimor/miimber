import Vue from "vue";
import Vuex from "vuex";

import sign from "@sign/store.js";
import core from "@core/store.js";
import settings from "@settings/store.js";
import organization from "@organization/store.js";
import dashboard from "@dashboard/store.js";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    core,
    sign,
    settings,
    organization,
    dashboard
  }
});
