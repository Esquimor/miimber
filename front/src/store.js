import Vue from "vue";
import Vuex from "vuex";

import sign from "@sign/store.js";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    sign
  }
});
