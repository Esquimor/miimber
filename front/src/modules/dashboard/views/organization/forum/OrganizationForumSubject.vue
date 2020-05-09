<template>
  <TemplateOrganization :loading="loading">
    <div class="DashboardOrganizationForumSubject">
      <header class="DashboardOrganizationForumSubject-header">
        <div class="DashboardOrganizationForumSubject-header-left">
          <h2
            class="DashboardOrganizationForumSubject-header-left-title text is-size-5"
          >
            {{ subject.title }}
          </h2>
          <span
            class="DashboardOrganizationForumSubject-header-left-category text is-size-7"
            >{{ subject.category && subject.category.title }}</span
          >
        </div>
        <div class="DashboardOrganizationForumSubject-header-right">
          <BButton type="is-primary" icon-left="plus" @click.native="addTalk">{{
            $t("dashboard.organization.subjectForum.add.title")
          }}</BButton>
        </div>
      </header>
      <div class="DashboardOrganizationForumSubject-talks">
        <OrganizationForumTalkItem
          v-for="talk in orderTalks"
          :key="talk.id"
          :talk="talk"
        />
      </div>
    </div>
  </TemplateOrganization>
</template>

<script>
"use strict";

import dayjs from "dayjs";

import { mapGetters } from "vuex";

import TemplateOrganization from "@dashboard/template/TemplateOrganization";

import OrganizationForumTalkItem from "@dashboard/components/organization/forum/OrganizationForumTalkItem";

import OrganizationForumTalkAdd from "@dashboard/components/organization/forum/OrganizationForumTalkAdd";

export default {
  name: "DashboardOrganizationForumSubject",
  components: {
    TemplateOrganization,
    OrganizationForumTalkItem
  },
  data() {
    return {
      loading: true
    };
  },
  computed: {
    ...mapGetters({
      subject: "dashboard/organizationForumSubject",
      isMemberOrganization: "dashboard/isMemberOrganization",
      isOrganizationArchived: "dashboard/isOrganizationArchived",
      isOrganizationSuspended: "dashboard/isOrganizationSuspended"
    }),
    orderTalks() {
      if (!this.subject.talks) return [];
      // eslint-disable-next-line vue/no-side-effects-in-computed-properties
      return this.subject.talks.sort((a, b) =>
        dayjs(a.dateTalk).isBefore(dayjs(b.dateTalk))
      );
    }
  },
  methods: {
    addTalk() {
      this.$store.dispatch("core/openSideBar", {
        component: OrganizationForumTalkAdd
      });
    }
  },
  mounted() {
    if (!this.isMemberOrganization) {
      this.$router.push({ name: "error_404" });
      this.loading = false;
      return;
    }
    if (this.isOrganizationArchived) {
      this.$router.push({
        name: "dashboard-organization-archived",
        params: { id: this.$route.params.id }
      });
      this.loading = false;
      return;
    }
    if (this.isOrganizationSuspended) {
      this.$router.push({
        name: "dashboard-organization-suspended",
        params: { id: this.$route.params.id }
      });
      this.loading = false;
      return;
    }
    this.$store
      .dispatch("dashboard/setForumSubject", this.$route.params.idSubject)
      .then(() => {
        this.loading = false;
      });
  }
};
</script>

<style lang="scss">
.DashboardOrganizationForumSubject {
  display: flex;
  flex-direction: column;
  border: 1px solid $white-ter;
  border-radius: 5px;
  &-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-bottom: 1px solid $grey-lightest;
    background-color: $white-bis;
    padding: 0.5rem;
  }
  &-talks {
    display: flex;
    flex-direction: column;
  }
}
</style>
