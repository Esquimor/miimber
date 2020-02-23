import api from "@/utils/api";
//import * as types from "./../types";

export default {
  state: {
    user: {}
  },
  getters: {},
  actions: {
    login(_, { email, password }) {
      return api
        .postNoAuth("login", {
          email: email,
          password: password
        })
        .then(response => {
          if (response.status !== 200) return Promise.reject();
          localStorage.setItem("token", response.data.token);
          return Promise.resolve();
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
