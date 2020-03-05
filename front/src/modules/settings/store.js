import api from "@/utils/api";
import * as types from "@/utils/types";

export default {
  namespaced: true,
  state: {
    organizations: []
  },
  getters: {
    organizations: state => state.organizations
  },
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
    },
    getOrganizationOwnered({ commit }) {
      return api
        .get("organization/ownered")
        .then(({ data }) => {
          commit(types.SET_ORGANIZATION_OWNERED, data);
          return Promise.resolve();
        })
        .catch(e => {
          return Promise.reject(e);
        });
    },
    createOrganization(_, { token, name, subscription }) {
      return api
        .post("organization/", {
          tokenId: token,
          name,
          subscription
        })
        .then(data => {
          console.log(data);
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mutations: {
    [types.SET_ORGANIZATION_OWNERED](state, organizations) {
      state.organizations = organizations;
    }
  }
};
