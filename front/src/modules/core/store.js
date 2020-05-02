import api from "@/utils/api";
import * as types from "@/utils/types";

import { ROLE } from "@/utils/consts";

const message = () => {
  return {
    show: false,
    title: "",
    message: "",
    type: ""
  };
};

const error = () => {
  return {
    show: false,
    status: ""
  };
};

export default {
  namespaced: true,
  state: {
    me: null,
    member: null,
    sideBar: {
      open: false
    },
    message: message(),
    error: error()
  },
  getters: {
    isConnected: state => !!state.me,
    me: state => state.me,
    member: state => state.member,
    memberIsOwner: state => !!state.member && state.member.role === ROLE.OWNER,
    canChangeOrganization: state => {
      return (
        !!state.member &&
        [ROLE.OWNER, ROLE.OFFICE, ROLE.OFFICE_INSTRUCTOR].includes(
          state.member.role
        )
      );
    },
    sideBar: state => state.sideBar,
    sideBarProps: state => state.sideBar.open && state.sideBar.props,
    message: state => state.message,
    error: state => state.error
  },
  actions: {
    getMe({ commit }) {
      return api
        .get("me", {}, { errorRedirect: true })
        .then(({ data }) => {
          commit(types.COR_SET_ME, data);
          return Promise.resolve();
        })
        .catch(e => {
          return Promise.reject(e);
        });
    },
    updateMeByProfile({ commit }, payload) {
      commit(types.COR_UPDATE_ME_BY_PROFILE, payload);
    },
    getMember({ commit }, idOrganization) {
      return api
        .get(`member/me/${idOrganization}`, {}, { errorMessage: true })
        .then(({ data }) => {
          commit(types.COR_SET_MEMBER_ME, data);
          return Promise.resolve();
        })
        .catch(e => {
          return Promise.reject(e);
        });
    },
    updateLang({ state }, { lang }) {
      return api.put(`user/${state.me.id}/lang`, { lang });
    },
    logout({ commit }) {
      commit(types.COR_LOGOUT);
    },
    openSideBar({ commit }, payload) {
      commit(types.COR_OPEN_SIDE_BAR, payload);
    },
    closeSideBar({ commit }) {
      commit(types.COR_CLOSE_SIDE_BAR);
    },
    setMessage({ commit }, message) {
      commit(types.COR_SET_MESSAGE, message);
    },
    setMessageError({ commit }, status = 404) {
      commit(types.CORE_SET_MESSAGE_ERROR_STATUS, status);
    },
    setError({ commit }) {
      commit(types.CORE_SET_MESSAGE_ERROR);
    },
    emptyMessage({ commit }) {
      commit(types.COR_EMPTY_MESSAGE);
    },
    emptyError({ commit }) {
      commit(types.COR_EMPTY_ERROR);
    }
  },
  mutations: {
    [types.COR_SET_ME](state, user) {
      state.me = user;
    },
    [types.COR_UPDATE_ME_BY_PROFILE](state, { firstName, lastName }) {
      state.me.lastName = lastName;
      state.me.firstName = firstName;
    },
    [types.COR_SET_MEMBER_ME](state, member) {
      state.member = member;
    },
    [types.COR_OPEN_SIDE_BAR](state, { component, props }) {
      state.sideBar = {
        component,
        open: true,
        props
      };
    },
    [types.COR_CLOSE_SIDE_BAR](state) {
      state.sideBar.open = false;
    },
    [types.COR_SET_MESSAGE](state, { type, message, title }) {
      state.message = {
        type,
        message,
        title,
        show: true
      };
    },
    [types.CORE_SET_MESSAGE_ERROR_STATUS](state, status) {
      state.error = {
        show: true,
        status
      };
    },
    [types.CORE_SET_MESSAGE_ERROR](state) {
      state.error = {
        show: true
      };
    },
    [types.COR_EMPTY_MESSAGE](state) {
      state.message = message();
    },
    [types.COR_EMPTY_ERROR](state) {
      state.error = error();
    },
    [types.COR_LOGOUT](state) {
      state.me = null;
      state.member = null;
      localStorage.removeItem("token");
    }
  }
};
