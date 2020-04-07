import api from "@/utils/api";
import * as types from "@/utils/types";

import { ROLE } from "@/utils/consts";

const message = () => {
  return {
    show: false,
    title: "",
    message: "",
    type: "",
  };
};

export default {
  namespaced: true,
  state: {
    me: null,
    member: null,
    sideBar: {
      open: false,
    },
    message: message(),
  },
  getters: {
    me: (state) => state.me,
    member: (state) => state.member,
    memberIsOwner: (state) =>
      !!state.member && state.member.role === ROLE.OWNER,
    canChangeOrganization: (state) => {
      return (
        !!state.member &&
        [ROLE.OWNER, ROLE.OFFICE, ROLE.OFFICE_INSTRUCTOR].includes(
          state.member.role
        )
      );
    },
    sideBar: (state) => state.sideBar,
    sideBarProps: (state) => state.sideBar.open && state.sideBar.props,
  },
  actions: {
    getMe({ commit }) {
      return api.get("me").then(({ data }) => {
        commit(types.COR_SET_ME, data);
        return Promise.resolve();
      });
    },
    updateMeByProfile({ commit }, payload) {
      commit(types.COR_UPDATE_ME_BY_PROFILE, payload);
    },
    getMember({ commit }, idOrganization) {
      return api
        .get(`member/me/${idOrganization}`)
        .then(({ data }) => {
          commit(types.COR_SET_MEMBER_ME, data);
          return Promise.resolve();
        })
        .catch((e) => {
          return Promise.reject(e);
        });
    },
    openSideBar({ commit }, payload) {
      commit(types.COR_OPEN_SIDE_BAR, payload);
    },
    closeSideBar({ commit }) {
      commit(types.COR_CLOSE_SIDE_BAR);
    },
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
        props,
      };
    },
    [types.COR_CLOSE_SIDE_BAR](state) {
      state.sideBar.open = false;
    },
  },
};
