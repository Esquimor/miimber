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
