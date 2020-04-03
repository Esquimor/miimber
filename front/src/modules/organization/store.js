import api from "@/utils/api";
import * as types from "@/utils/types";

import dayjs from "dayjs";
import customParseFormat from "dayjs/plugin/customParseFormat";
dayjs.extend(customParseFormat);

export default {
  namespaced: true,
  state: {
    organization: null,
    typeSessions: [],
    sessions: [],
  },
  getters: {
    organization: (state) => state.organization,
    organizationMembers: (state) => state.organization.members,
    typeSessions: (state) => state.typeSessions,
    sessions: (state) => state.sessions,
  },
  actions: {
    setOrganization({ commit, dispatch }, id) {
      return api
        .get(`organization/${id}/manage`)
        .then((organization) => {
          dispatch("core/getMember", organization.data.id, { root: true }).then(
            () => {
              commit(types.ORG_SET_ORGANIZATION, organization.data);
            }
          );
        })
        .catch((e) => {
          return Promise.reject(e);
        });
    },
    editOrganization({ commit, state }, name) {
      return api
        .put(`organization/${state.organization.id}`, { name })
        .then(({ data }) => {
          commit(types.ORG_EDIT_ORGANIZATION, data);
        })
        .catch((e) => {
          return Promise.reject(e);
        });
    },
    deleteOrganization({ state }) {
      return api
        .delete(`organization/${state.organization.id}`)
        .then(() => {
          return Promise.resolve();
        })
        .catch((e) => {
          return Promise.reject(e);
        });
    },
    updateCardOrganization({ state }, { token }) {
      return api
        .put(`organization/${state.organization.id}/card`, {
          token,
        })
        .then(() => {
          return Promise.resolve();
        })
        .catch((e) => {
          return Promise.reject(e);
        });
    },
    changeRight({ commit }, { id, role }) {
      return api
        .put(`member/${id}`, {
          role,
        })
        .then(({ data }) => {
          commit(types.ORG_CHANGE_MEMBER_ROLE, data);
        })
        .catch((e) => {
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
          role,
        })
        .then(({ data }) => {
          commit(types.ORG_ADD_MEMBER, data);
        })
        .catch((e) => {
          return Promise.reject(e);
        });
    },
    addMember({ commit }, { organizationId, userId }) {
      return api
        .post("member/", {
          idOrganization: organizationId,
          idUser: userId,
        })
        .then(({ data }) => {
          commit(types.ORG_ADD_MEMBER, data);
        })
        .catch((e) => {
          return Promise.reject(e);
        });
    },
    removeMember({ commit }, id) {
      return api
        .delete(`member/${id}`)
        .then(() => {
          commit(types.ORG_REMOVE_MEMBER, id);
        })
        .catch((e) => {
          return Promise.reject(e);
        });
    },
    setTypeSessions({ commit, state }) {
      return api
        .get(`organization/${state.organization.id}/type-session/`)
        .then(({ data }) => {
          commit(types.ORG_SET_TYPE_SESSIONS, data);
        })
        .catch((e) => {
          return Promise.reject(e);
        });
    },
    addTypeSession({ commit, state }, { name }) {
      return api
        .post("type-session/", {
          name,
          organizationId: state.organization.id,
        })
        .then(({ data }) => {
          commit(types.ORG_ADD_TYPE_SESSION, data);
        })
        .catch((e) => {
          return Promise.reject(e);
        });
    },
    editTypeSession({ commit }, { name, id }) {
      return api
        .put(`type-session/${id}`, {
          name,
        })
        .then(({ data }) => {
          commit(types.ORG_EDIT_TYPE_SESSION, data);
        })
        .catch((e) => {
          return Promise.reject(e);
        });
    },
    deleteTypeSession({ commit }, id) {
      return api
        .put(`type-session/${id}`, {
          name,
        })
        .then(() => {
          commit(types.ORG_DELETE_TYPE_SESSION, id);
        })
        .catch((e) => {
          return Promise.reject(e);
        });
    },
    setSessions({ commit, state }, { minDate, maxDate }) {
      return api
        .get(`organization/${state.organization.id}/session/`, {
          minDate: dayjs(minDate).format("YYYY-MM-DDTHH:mm:ssZ"),
          maxDate: dayjs(maxDate).format("YYYY-MM-DDTHH:mm:ssZ"),
        })
        .then(({ data }) => {
          commit(types.ORG_SET_SESSIONS, data);
        })
        .catch((e) => {
          return Promise.reject(e);
        });
    },
    addSession(
      { commit, state },
      {
        title,
        description,
        start,
        end,
        typeSessionId,
        startDate,
        endDate,
        periodicity,
        days,
        repeat,
        limit,
      }
    ) {
      return api
        .post("session/", {
          title,
          description,
          start,
          end,
          typeSessionId,
          startDate,
          endDate,
          periodicity,
          days,
          repeat,
          organizationId: state.organization.id,
          limit,
        })
        .then(({ data }) => {
          commit(types.ORG_ADD_SESSIONS, data);
        })
        .catch((e) => {
          return Promise.reject(e);
        });
    },
    editSession(
      { commit },
      { title, description, start, end, typeSessionId, limit, id }
    ) {
      return api
        .put(`session/${id}`, {
          title,
          description,
          start,
          end,
          typeSessionId,
          limit,
        })
        .then(({ data }) => {
          commit(types.ORG_EDIT_SESSION, data);
        })
        .catch((e) => {
          return Promise.reject(e);
        });
    },
    deleteSession({ commit }, id) {
      return api
        .delete(`session/${id}`)
        .then(() => {
          commit(types.ORG_DELETE_SESSION, id);
        })
        .catch((e) => {
          return Promise.reject(e);
        });
    },
  },
  mutations: {
    [types.ORG_SET_ORGANIZATION](state, organization) {
      state.organization = organization;
    },
    [types.ORG_EDIT_ORGANIZATION](state, organization) {
      state.organization.name = organization.name;
    },
    [types.ORG_CHANGE_MEMBER_ROLE](state, { id, role }) {
      const member = state.organization.members.find((m) => m.id === id);
      member.role = role;
    },
    [types.ORG_ADD_MEMBER](state, member) {
      state.organization.members = [...state.organization.members, member];
    },
    [types.ORG_REMOVE_MEMBER](state, id) {
      state.organization.members = state.organization.members.filter(
        (m) => m.id !== id
      );
    },
    [types.ORG_SET_TYPE_SESSIONS](state, typeSessions) {
      state.typeSessions = typeSessions;
    },
    [types.ORG_ADD_TYPE_SESSION](state, typeSession) {
      state.typeSessions.push(typeSession);
    },
    [types.ORG_EDIT_TYPE_SESSION](state, { name, id }) {
      const editedTypeSession = state.typeSessions.find((t) => t.id === id);
      editedTypeSession.name = name;
    },
    [types.ORG_DELETE_TYPE_SESSION](state, id) {
      state.typeSessions = state.typeSessions.filter((t) => t.id !== id);
    },
    [types.ORG_SET_SESSIONS](state, sessions) {
      state.sessions = sessions;
    },
    [types.ORG_ADD_SESSIONS](state, sessions) {
      state.sessions = state.sessions.concat(sessions);
    },
    [types.ORG_EDIT_SESSION](
      state,
      { title, description, start, end, typeSession, id }
    ) {
      const editedSession = state.sessions.find((s) => s.id === id);
      editedSession.title = title;
      editedSession.description = description;
      editedSession.start = start;
      editedSession.end = end;
      editedSession.typeSession = typeSession;
    },
    [types.ORG_DELETE_SESSION](state, id) {
      state.sessions = state.sessions.filter((s) => s.id !== id);
    },
  },
};
