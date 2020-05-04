<template>
  <TemplateEmerge :loading="loading">
    <div v-if="!loading" class="DashboardSessionEmerge">
      <router-link
        :to="{ name: 'dashboard-session', params: { id: session.id } }"
        class="DashboardSessionEmerge-title title is-4"
      >
        <BIcon icon="arrow-left" />
        {{ session.title }}
      </router-link>
      <BField
        :label="$t('dashboard.session.label.search')"
        class="DashboardSessionEmerge-search"
      >
        <BInput
          :placeholder="$t('dashboard.session.label.search')"
          type="search"
          icon="magnify"
          v-model="search"
        ></BInput>
      </BField>
      <div v-if="filteredUser.length > 0" class="DashboardSessionEmerge-users">
        <SessionEmergeItem
          v-for="user in filteredUser"
          :key="user.id"
          :user="user"
          @check="setUser"
        />
      </div>
      <div v-else class="DashboardSessionEmerge-empty">
        <span>{{ $t("dashboard.session.label.emptyUsers") }}</span>
      </div>
    </div>
  </TemplateEmerge>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

import TemplateEmerge from "@dashboard/template/TemplateEmerge";

import SessionEmergeItem from "@dashboard/components/session/emerge/SessionEmergeItem";

export default {
  name: "DashboardSessionEmerge",
  components: {
    TemplateEmerge,
    SessionEmergeItem
  },
  data() {
    return {
      loading: true,
      search: ""
    };
  },
  computed: {
    ...mapGetters({
      users: "dashboard/sessionUsers",
      session: "dashboard/session"
    }),
    reorderUser() {
      // eslint-disable-next-line vue/no-side-effects-in-computed-properties
      const reorderUser = this.users.sort((a, b) => a.firstName - b.firstName);
      return reorderUser;
    },
    filteredUser() {
      if (this.search === "") return this.reorderUser;

      const lowerCaseSearch = this.search.toLowerCase();
      return this.reorderUser.filter(p => {
        const nameConcat = `${p.firstName} ${p.lastName}`;
        return nameConcat.toLowerCase().search(lowerCaseSearch) !== -1;
      });
    }
  },
  methods: {
    setUser(user) {
      if (user.attendeeId === 0 || user.attendeeId === null)
        this.$store.dispatch("dashboard/setUserPresentSession", user.id);
      else
        this.$store.dispatch(
          "dashboard/removeUserPresentSession",
          user.attendeeId
        );
    }
  },
  mounted() {
    this.$store
      .dispatch("dashboard/setSessionUser", this.$route.params.id)
      .then(() => {
        this.loading = false;
      })
      .catch(() => {
        this.loading = false;
      });
  }
};
</script>

<style lang="scss">
.DashboardSessionEmerge {
  display: flex;
  flex-direction: column;
  &-empty {
    margin-top: 1rem;
    text-align: center;
  }
}
</style>
