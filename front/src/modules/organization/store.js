import api from "@/utils/api";
import * as types from "@/utils/types";

export default {
  namespaced: true,
  state: {
    organization: null
  },
  getters: {
    organization: state => state.organization,
    organizationMembers: state => state.organization.members
  },
  actions: {
    setOrganization({ commit }, id) {
      return api
        .get(`organization/${id}/manage`)
        .then(({ data }) => {
          commit(types.SET_ORGANIZATION, data);
        })
        .catch(e => {
          return Promise.reject(e);
        });
    }
  },
  mutations: {
    [types.SET_ORGANIZATION](state, organization) {
      state.organization = organization;
    }
  }
};
