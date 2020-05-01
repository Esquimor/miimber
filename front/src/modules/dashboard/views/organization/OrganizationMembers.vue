<template>
  <div v-if="!loading" class="DashboardOrganizationMembers">
    <div class="DashboardOrganizationMembers-members">
      <SessionUserItem v-for="member in members" :key="member.id" :user="member" />
    </div>
  </div>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

import SessionUserItem from "@dashboard/components/session/SessionUserItem";

export default {
  name: "DashboardOrganizationMembers",
  components: {
    SessionUserItem
  },
  data() {
    return {
      loading: true
    };
  },
  computed: {
    ...mapGetters({
      members: "dashboard/organizationMembers"
    })
  },
  mounted() {
    this.$store
      .dispatch("dashboard/setOrganizationMembers", this.$route.params.id)
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
.DashboardOrganizationMembers {
  width: 100%;
  padding: 1rem;
  background-color: $white;
  border: 1px solid $grey-lightest;
  min-height: 80vh;
  border-radius: 5px;
  &-members {
    display: flex;
    flex-wrap: wrap;
  }
}
</style>
