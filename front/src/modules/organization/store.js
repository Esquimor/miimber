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
    },
    changeRight({ commit }, { id, role }) {
      return api
        .put(`member/${id}`, {
          role
        })
        .then(({ data }) => {
          console.log(data);
          commit(types.CHANGE_MEMBER_ROLE, data);
        })
        .catch(e => {
          console.log(e);
          return Promise.reject(e);
        });
    }
  },
  mutations: {
    [types.SET_ORGANIZATION](state, organization) {
      state.organization = organization;
    },
    [types.CHANGE_MEMBER_ROLE](state, { id, role }) {
      const member = state.organization.members.find(m => m.id === id);
      member.role = role;
    }
  }
};
