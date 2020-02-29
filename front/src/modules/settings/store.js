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
    },
    updatePassword(_, { oldPassword, newPassword, id }) {
      return api
        .put(`user/${id}`, {
          oldPassword,
          newPassword
        })
        .then(() => {
          return Promise.resolve();
        })
        .catch(e => {
          return Promise.reject(e);
        });
    }
  },
  mutations: {}
};
