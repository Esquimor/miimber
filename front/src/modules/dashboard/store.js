import api from "@/utils/api";
import * as types from "@/utils/types";

import dayjs from "dayjs";
import customParseFormat from "dayjs/plugin/customParseFormat";
dayjs.extend(customParseFormat);

export default {
  namespaced: true,
  state: {
    organization: {},
    organizationSessions: [],
    organizationMembers: [],
    organizations: []
  },
  getters: {
    organizations: state => state.organizations,
    organization: state => state.organization,
    organizationSessions: state => state.organizationSessions,
    organizationMembers: state => state.organizationMembers
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
    }
  }
};
