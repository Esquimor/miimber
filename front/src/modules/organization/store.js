import api from "@/utils/api";
import * as types from "@/utils/types";

export default {
  namespaced: true,
  state: {
    organization: null,
    typeSessions: []
  },
  getters: {
    organization: state => state.organization,
    organizationMembers: state => state.organization.members,
    typeSessions: state => state.typeSessions
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
    editOrganization({ commit, state }, name) {
      return api
        .put(`organization/${state.organization.id}`, { name })
        .then(({ data }) => {
          commit(types.EDIT_ORGANIZATION, data);
        })
        .catch(e => {
          return Promise.reject(e);
        });
    },
    deleteOrganization({ state }) {
      return api
        .delete(`organization/${state.organization.id}`)
        .then(() => {
          return Promise.resolve();
        })
        .catch(e => {
          return Promise.reject(e);
        });
    },
    updateCardOrganization({ state }, { token }) {
      return api
        .put(`organization/${state.organization.id}/card`, {
          token
        })
        .then(() => {
          return Promise.resolve();
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
    addMember({ commit }, { organizationId, userId }) {
      return api
        .post("member/", {
          idOrganization: organizationId,
          idUser: userId
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
    },
    setTypeSession({ commit, state }) {
      return api
        .get(`organization/${state.organization.id}/type-sessions/`)
        .then(({ data }) => {
          commit(types.SET_TYPE_SESSIONS, data);
        })
        .catch(e => {
          return Promise.reject(e);
        });
    },
    addTypeSession({ commit, state }, { name }) {
      return api
        .post("type-session/", {
          name,
          organizationId: state.organization.id
        })
        .then(({ data }) => {
          commit(types.ADD_TYPE_SESSION, data);
        })
        .catch(e => {
          return Promise.reject(e);
        });
    },
    editTypeSession({ commit }, { name, id }) {
      return api
        .put(`type-session/${id}`, {
          name
        })
        .then(({ data }) => {
          commit(types.EDIT_TYPE_SESSION, data);
        })
        .catch(e => {
          return Promise.reject(e);
        });
    },
    deleteTypeSession({ commit }, id) {
      return api
        .put(`type-session/${id}`, {
          name
        })
        .then(() => {
          commit(types.DELETE_TYPE_SESSION, id);
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
    [types.EDIT_ORGANIZATION](state, organization) {
      state.organization.name = organization.name;
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
    },
    [types.SET_TYPE_SESSIONS](state, typeSessions) {
      state.typeSessions = typeSessions;
    },
    [types.ADD_TYPE_SESSION](state, typeSession) {
      state.typeSessions.push(typeSession);
    },
    [types.EDIT_TYPE_SESSION](state, { name, id }) {
      const editedTypeSession = state.typeSessions.find(t => t.id === id);
      editedTypeSession.name = name;
    },
    [types.DELETE_TYPE_SESSION](state, id) {
      state.typeSessions = state.typeSessions.filter(t => t.id !== id);
    }
  }
};
