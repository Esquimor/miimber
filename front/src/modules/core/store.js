import api from "@/utils/api";
import * as types from "@/utils/types";

export default {
  namespaced: true,
  state: {
    me: null
  },
  getters: {
    me: state => state.me
  },
  actions: {
    getMe({ commit }) {
      return api.get("me").then(({ data }) => {
        commit(types.SET_ME, data);
        return Promise.resolve();
      });
    }
  },
  mutations: {
    [types.SET_ME](state, user) {
      state.me = user;
    }
  }
};
