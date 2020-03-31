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
    session: null
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
        [ROLE.OWNER, ROLE.OFFICE_INSTRUCTOR, ROLE.INSCTRUCTOR].includes(
          state.session.member.role
        )
      );
    },
    sessionUsers: state => (state.session ? state.session.users : [])
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
    }
  }
};
