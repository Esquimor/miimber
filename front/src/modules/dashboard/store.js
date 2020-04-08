import api from "@/utils/api";
import * as types from "@/utils/types";

import { ROLE } from "@/utils/consts";

import dayjs from "dayjs";
import customParseFormat from "dayjs/plugin/customParseFormat";
dayjs.extend(customParseFormat);

export default {
  namespaced: true,
  state: {
    organization: null,
    organizationSessions: [],
    organizationMembers: [],
    organizations: [],
    sessions: [],
    session: null,
    sessionUsers: []
  },
  getters: {
    organizations: state => state.organizations,
    organization: state => state.organization,
    organizationSessions: state => state.organizationSessions,
    organizationMembers: state => state.organizationMembers,
    sessions: state => state.sessions,
    canChangeOrganization: state => {
      return (
        !!state.organization &&
        [ROLE.OWNER, ROLE.OFFICE_INSTRUCTOR, ROLE.OFFICE].includes(
          state.organization.user.role
        )
      );
    },
    session: state => state.session,
    isInsctructorOrganization: state => {
      return (
        !!state.session &&
        !!state.session.me.member &&
        [ROLE.OWNER, ROLE.OFFICE_INSTRUCTOR, ROLE.INSCTRUCTOR].includes(
          state.session.me.member.role
        )
      );
    },
    sessionUsers: state => state.sessionUsers,
    sessionRegistereds: state =>
      state.session ? state.session.registereds : [],
    userRegistered: state => !!state.session && !!state.session.me.registered,
    getUserForSession: state => !!state.session && state.session.me
  },
  actions: {
    setOrganizations({ commit }) {
      return api.get(`user/organization`).then(({ data }) => {
        commit(types.DASH_SET_ORGANIZATIONS, data);
      });
    },
    setOrganization({ commit }, id) {
      return api.get(`organization/${id}`).then(({ data }) => {
        commit(types.DASH_SET_ORGANIZATION, data);
      });
    },
    setOrganizationSessions({ commit }, { id, minDate, maxDate }) {
      return api
        .get(`organization/${id}/session/`, {
          minDate: dayjs(minDate).format("YYYY-MM-DDTHH:mm:ssZ"),
          maxDate: dayjs(maxDate).format("YYYY-MM-DDTHH:mm:ssZ")
        })
        .then(({ data }) => {
          commit(types.DASH_SET_ORGANIZATION_SESSIONS, data);
        });
    },
    setOrganizationMembers({ commit }, id) {
      return api.get(`organization/${id}/member/`).then(({ data }) => {
        commit(types.DASH_SET_ORGANIZATION_MEMBERS, data);
      });
    },
    setSessions({ commit }, { minDate, maxDate }) {
      return api
        .get("user/session/", {
          minDate: dayjs(minDate).format("YYYY-MM-DDTHH:mm:ssZ"),
          maxDate: dayjs(maxDate).format("YYYY-MM-DDTHH:mm:ssZ")
        })
        .then(({ data }) => {
          commit(types.DASH_SET_SESSIONS, data);
        });
    },
    setSession({ commit }, id) {
      return api.get(`session/${id}`).then(({ data }) => {
        commit(types.DASH_SET_SESSION, data);
      });
    },
    setSessionUser({ commit }, id) {
      return api.get(`session/${id}/user/`).then(({ data }) => {
        commit(types.DASH_SET_SESSION_USERS, data);
      });
    },
    setUserPresentSession({ commit, state }, id) {
      return api
        .post(`attendee/`, {
          userId: id,
          sessionId: state.session.id,
          dateCheck: Date.now()
        })
        .then(({ data }) => {
          commit(types.DASH_SET_USER_PRESENT_SESSION, data);
        })
        .catch(e => {
          return Promise.reject(e);
        });
    },
    removeUserPresentSession({ commit }, id) {
      return api
        .delete(`attendee/${id}`)
        .then(() => {
          commit(types.DASH_REMOVE_USER_PRESENT_SESSION, id);
        })
        .catch(e => {
          return Promise.reject(e);
        });
    },
    registered({ commit }, id) {
      return api
        .post("registered/", {
          sessionId: id,
          dateRegistered: dayjs(new Date()).format("YYYY-MM-DDTHH:mm:ssZ")
        })
        .then(({ data }) => {
          commit(types.DASH_ADD_REGISTERED, data);
        })
        .catch(e => {
          return Promise.reject(e);
        });
    },
    unsubscribe({ commit }, id) {
      return api
        .delete(`registered/${id}`)
        .then(() => {
          commit(types.DASH_REMOVE_REGISTERED, id);
        })
        .catch(e => {
          return Promise.reject(e);
        });
    }
  },
  mutations: {
    [types.DASH_SET_ORGANIZATIONS](state, organizations) {
      state.organizations = organizations;
    },
    [types.DASH_SET_ORGANIZATION](state, organization) {
      state.organization = organization;
    },
    [types.DASH_SET_ORGANIZATION_SESSIONS](state, sessions) {
      state.organizationSessions = sessions;
    },
    [types.DASH_SET_ORGANIZATION_MEMBERS](state, members) {
      state.organizationMembers = members;
    },
    [types.DASH_SET_SESSIONS](state, sessions) {
      state.sessions = sessions;
    },
    [types.DASH_SET_SESSION](state, session) {
      state.session = session;
    },
    [types.DASH_SET_SESSION_USERS](state, { id, title, users }) {
      state.session = { id, title };
      state.sessionUsers = users;
    },
    [types.DASH_SET_USER_PRESENT_SESSION](state, attendee) {
      const userEdited = state.sessionUsers.find(u => u.id === attendee.userId);
      userEdited.attendeeId = attendee.id;
    },
    [types.DASH_REMOVE_USER_PRESENT_SESSION](state, id) {
      const userEdited = state.sessionUsers.find(u => u.attendeeId === id);
      userEdited.attendeeId = null;
    },
    [types.DASH_ADD_REGISTERED](state, registered) {
      state.session.registereds.push(registered);
      state.session.me.registered = registered;
    },
    [types.DASH_REMOVE_REGISTERED](state, id) {
      state.session.registereds = state.session.registereds.filter(
        r => r.id !== id
      );
      state.session.me.registered = null;
    }
  }
};
