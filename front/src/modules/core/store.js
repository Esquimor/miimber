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
    },
    updateMeByProfile({ commit }, payload) {
      commit(types.UPDATE_ME_BY_PROFILE, payload);
    }
  },
  mutations: {
    [types.SET_ME](state, user) {
      state.me = user;
    },
    [types.UPDATE_ME_BY_PROFILE](state, { firstName, lastName }) {
      state.me.lastName = lastName;
      state.me.firstName = firstName;
    }
  }
};
