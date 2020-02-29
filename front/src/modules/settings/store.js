import api from "@/utils/api";
//import * as types from "@/utils/types";

export default {
  namespaced: true,
  state: {},
  getters: {},
  actions: {
    updateProfile({ dispatch }, { lastName, firstName, id }) {
      return api
        .put(`user/${id}`, {
          lastName,
          firstName
        })
        .then(({ data }) => {
          dispatch("core/updateMeByProfile", data, { root: true });
        })
        .catch(e => {
          return Promise.reject(e);
        });
    }
  },
  mutations: {}
};
