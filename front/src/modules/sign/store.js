import api from "@/utils/api";
// import * as types from "@/utils/types";

export default {
  namespaced: true,
  getters: {},
  actions: {
    login({ dispatch }, { email, password }) {
      return api
        .postNoAuth("login", {
          email: email,
          password: password
        })
        .then(response => {
          if (response.status !== 200) return Promise.reject();
          localStorage.setItem("token", response.data.token);
          return dispatch("core/getMe", null, { root: true }).then(() => {
            return Promise.resolve();
          });
        })
        .catch(e => {
          return Promise.reject(e);
        });
    },
    register(_, { email, password }) {
      return api
        .postNoAuth("register", {
          email: email,
          password: password
        })
        .then(data => {
          console.log(data);
        })
        .catch(e => {
          return Promise.reject(e);
        });
    }
  },
  mutations: {}
};
