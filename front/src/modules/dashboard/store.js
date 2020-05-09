import api from "@/utils/api";
import * as types from "@/utils/types";

import { ROLE, STATUS_REGISTERED, STATE_ORGANIZATION } from "@/utils/consts";

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
    organizationsCategoriesForum: [],
    organizationForumSubject: {},
    organizationForumTalk: {},
    sessions: [],
    session: null,
    sessionUsers: []
  },
  getters: {
    organizations: state => state.organizations,
    organization: state => state.organization,
    organizationSessions: state => state.organizationSessions,
    organizationMembers: state => state.organizationMembers,
    organizationsCategoriesForum: state => state.organizationsCategoriesForum,
    organizationForumSubject: state => state.organizationForumSubject,
    organizationForumTalk: state => state.organizationForumTalk,
    sessions: state => state.sessions,
    stateOrganization: state =>
      state.organization
        ? state.organization.state
        : STATE_ORGANIZATION.UNDEFINED,
    isOrganizationArchive: (state, getters) =>
      getters.stateOrganization === STATE_ORGANIZATION.ARCHIVE,
    isOrganizationActive: (state, getters) =>
      getters.stateOrganization === STATE_ORGANIZATION.ACTIVE,
    isOrganizationSuspended: (state, getters) =>
      getters.stateOrganization === STATE_ORGANIZATION.SUSPENDED,
    isMemberOrganization: state =>
      state.organization ? state.organization.member !== null : false,
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
    getUserForSession: state => !!state.session && state.session.me,
    sessionComments: state => (state.session ? state.session.comments : [])
  },
  actions: {
    setOrganizations({ commit }) {
      return api
        .get(`user/organization`, {}, { errorRedirect: true })
        .then(({ data }) => {
          commit(types.DASH_SET_ORGANIZATIONS, data);
        });
    },
    setOrganization({ commit }, id) {
      return api
        .get(`organization/${id}`, {}, { errorRedirect: true })
        .then(({ data }) => {
          commit(types.DASH_SET_ORGANIZATION, data);
        });
    },
    setOrganizationSessions({ commit }, { id, minDate, maxDate }) {
      return api
        .get(
          `organization/${id}/session/`,
          {
            minDate: dayjs(minDate).format("YYYY-MM-DDTHH:mm:ssZ"),
            maxDate: dayjs(maxDate).format("YYYY-MM-DDTHH:mm:ssZ")
          },
          { errorMessage: true }
        )
        .then(({ data }) => {
          commit(types.DASH_SET_ORGANIZATION_SESSIONS, data);
        });
    },
    setOrganizationMembers({ commit }, id) {
      return api
        .get(`organization/${id}/member/`, {}, { errorRedirect: true })
        .then(({ data }) => {
          commit(types.DASH_SET_ORGANIZATION_MEMBERS, data);
        });
    },
    setSessions({ commit }, { minDate, maxDate }) {
      return api
        .get(
          "user/session/",
          {
            minDate: dayjs(minDate).format("YYYY-MM-DDTHH:mm:ssZ"),
            maxDate: dayjs(maxDate).format("YYYY-MM-DDTHH:mm:ssZ")
          },
          { errorMessage: true }
        )
        .then(({ data }) => {
          commit(types.DASH_SET_SESSIONS, data);
        });
    },
    setSession({ commit }, id) {
      return api
        .get(`session/${id}`, {}, { errorRedirect: true })
        .then(({ data }) => {
          commit(types.DASH_SET_SESSION, data);
        });
    },
    setSessionUser({ commit }, id) {
      return api
        .get(`session/${id}/user/`, {}, { errorRedirect: true })
        .then(({ data }) => {
          commit(types.DASH_SET_SESSION_USERS, data);
        });
    },
    setUserPresentSession({ commit, state }, id) {
      return api
        .post(
          `attendee/`,
          {
            userId: id,
            sessionId: state.session.id,
            dateCheck: dayjs().format("YYYY-MM-DDTHH:mm:ssZ")
          },
          { errorMessage: true }
        )
        .then(({ data }) => {
          commit(types.DASH_SET_USER_PRESENT_SESSION, data);
        })
        .catch(e => {
          return Promise.reject(e);
        });
    },
    removeUserPresentSession({ commit }, id) {
      return api
        .delete(`attendee/${id}`, { errorMessage: true })
        .then(() => {
          commit(types.DASH_REMOVE_USER_PRESENT_SESSION, id);
        })
        .catch(e => {
          return Promise.reject(e);
        });
    },
    registered({ commit }, id) {
      return api
        .post(
          "registered/",
          {
            sessionId: id
          },
          { errorMessage: true }
        )
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
        .then(
          ({ data }) => {
            commit(types.DASH_REMOVE_REGISTERED, id);
            console.log(data.id);
            if (data.id) {
              console.log("a");
              commit(types.DASH_PUSH_TAKEN_REGISTERED, data.id);
            }
          },
          { errorMessage: true }
        )
        .catch(e => {
          return Promise.reject(e);
        });
    },
    addCommment({ commit, state }, { comment }) {
      return api
        .post(
          "comment/",
          {
            comment,
            dateComment: dayjs().format("YYYY-MM-DDTHH:mm:ssZ"),
            sessionId: state.session.id
          },
          { errorMessage: true }
        )
        .then(({ data }) => {
          commit(types.DASH_ADD_COMMENT, data);
        })
        .catch(e => {
          return Promise.reject(e);
        });
    },
    setForum({ commit, state }) {
      return api
        .get(
          `organization/${state.organization.id}/forum/category/`,
          {},
          { errorRedirect: true }
        )
        .then(({ data }) => {
          commit(types.DASH_SET_FORUM, data);
        });
    },
    setForumSubject({ commit, state }, id) {
      return api
        .get(
          `organization/${state.organization.id}/forum/subject/${id}`,
          {},
          { errorRedirect: true }
        )
        .then(({ data }) => {
          commit(types.DASH_SET_FORUM_SUBJECT, data);
        });
    },
    addTalk({ commit, state }, { title, message, idSubject }) {
      return api
        .post(
          `organization/${state.organization.id}/forum/talk/`,
          {
            idSubject,
            title,
            message
          },
          { errorMessage: true }
        )
        .then(({ data }) => {
          commit(types.DASH_ADD_FORUM_SUBJECT_TALK, data);
        });
    },
    setForumTalk({ commit, state }, id) {
      return api
        .get(
          `organization/${state.organization.id}/forum/talk/${id}`,
          {},
          { errorRedirect: true }
        )
        .then(({ data }) => {
          commit(types.DASH_SET_FORUM_TALK, data);
        });
    },
    addMessage({ commit, state }, { message }) {
      return api
        .post(
          `organization/${state.organization.id}/forum/message/`,
          {
            message,
            talkId: state.organizationForumTalk.id
          },
          {
            errorMessage: true
          }
        )
        .then(({ data }) => {
          commit(types.DASH_ADD_FORUM_TALK_MESSAGE, data);
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
    },
    [types.DASH_PUSH_TAKEN_REGISTERED](state, id) {
      const registeredTaken = state.session.registereds.find(r => r.id === id);
      registeredTaken.status = STATUS_REGISTERED.TAKEN;
    },
    [types.DASH_ADD_COMMENT](state, comment) {
      state.session.comments.push(comment);
    },
    [types.DASH_SET_FORUM](state, forum) {
      state.organizationsCategoriesForum = forum;
    },
    [types.DASH_SET_FORUM_SUBJECT](state, subject) {
      state.organizationForumSubject = subject;
    },
    [types.DASH_ADD_FORUM_SUBJECT_TALK](state, talk) {
      state.organizationForumSubject.talks.push(talk);
    },
    [types.DASH_SET_FORUM_TALK](state, talk) {
      state.organizationForumTalk = talk;
    },
    [types.DASH_ADD_FORUM_TALK_MESSAGE](state, message) {
      state.organizationForumTalk.messages.push(message);
    }
  }
};
