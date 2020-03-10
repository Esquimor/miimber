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
    setOrganization({ commit, dispatch }, id) {
      return api
        .get(`organization/${id}/manage`)
        .then(organization => {
          dispatch("core/getMember", organization.data.id, { root: true }).then(
            () => {
              commit(types.SET_ORGANIZATION, organization.data);
            }
          );
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
          commit(types.CHANGE_MEMBER_ROLE, data);
        })
        .catch(e => {
          return Promise.reject(e);
        });
    },
    addMemberAndUser(
      { commit },
      { organizationId, email, firstName, lastName, role }
    ) {
      return api
        .post("member/user", {
          idOrganization: organizationId,
          email,
          firstName,
          lastName,
          role
        })
        .then(({ data }) => {
          commit(types.ADD_MEMBER, data);
        })
        .catch(e => {
          return Promise.reject(e);
        });
    },
    removeMember({ commit }, id) {
      return api
        .delete(`member/${id}`)
        .then(() => {
          commit(types.REMOVE_MEMBER, id);
        })
        .catch(e => {
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
    },
    [types.ADD_MEMBER](state, member) {
      state.organization.members = [...state.organization.members, member];
    },
    [types.REMOVE_MEMBER](state, id) {
      state.organization.members = state.organization.members.filter(
        m => m.id !== id
      );
    }
  }
};
