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
          password: password,
        })
        .then((response) => {
          if (response.status !== 200) return Promise.reject();
          localStorage.setItem("token", response.data.jwttoken);
          return dispatch("core/getMe", null, { root: true }).then(() => {
            return Promise.resolve();
          });
        })
        .catch((e) => {
          return Promise.reject(e);
        });
    },
    register(_, { email, firstName, lastName, password }) {
      return api
        .postNoAuth("register", {
          email: email,
          password: password,
          lastName,
          firstName,
        })
        .then(({ data }) => {
          return Promise.resolve(data);
        })
        .catch((e) => {
          return Promise.reject(e);
        });
    },
    passwordForgotten(_, email) {
      return api
        .postNoAuth(
          "password-forgotten",
          {
            email,
          },
          { errorMessage: true }
        )
        .then(({ data }) => {
          return Promise.resolve(data);
        })
        .catch((e) => {
          return Promise.reject(e);
        });
    },
    resetPassword(_, { idUser, token, password }) {
      return api
        .postNoAuth(
          "reset-password",
          {
            id: idUser,
            token,
            password,
          },
          { errorMessage: true }
        )
        .then(() => {
          return Promise.resolve();
        })
        .catch((e) => {
          return Promise.reject(e);
        });
    },
    registerValidated(_, { id, token }) {
      return api
        .postNoAuth("register-validated", {
          id,
          token,
        })
        .then(() => {
          return Promise.resolve();
        })
        .catch((e) => {
          return Promise.reject(e);
        });
    },
  },
  mutations: {},
};
