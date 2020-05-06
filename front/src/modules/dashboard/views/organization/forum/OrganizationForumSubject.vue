<template>
  <TemplateOrganization :loading="loading">
    <div class="DashboardOrganizationForumSubject">
      <header class="DashboardOrganizationForumSubject-header">
        <div class="DashboardOrganizationForumSubject-header-left">
          <h2
            class="DashboardOrganizationForumSubject-header-left-title text is-size-5"
          >{{ subject.title }}</h2>
          <span
            class="DashboardOrganizationForumSubject-header-left-category text is-size-7"
          >{{ subject.category && subject.category.title }}</span>
        </div>
        <div class="DashboardOrganizationForumSubject-header-right">
          <BButton
            type="is-primary"
            icon-left="plus"
            @click.native="addTalk"
          >{{ $t('dashboard.organization.subjectForum.add.title') }}</BButton>
        </div>
      </header>
      <div class="DashboardOrganizationForumSubject-talks">
        <OrganizationForumTalkItem v-for="talk in subject.talks" :key="talk.id" :talk="talk" />
      </div>
    </div>
  </TemplateOrganization>
</template>

<script>
"use strict";

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
      subject: "dashboard/organizationForumSubject"
    })
  },
  methods: {
    addTalk() {
      this.$store.dispatch("core/openSideBar", {
        component: OrganizationForumTalkAdd
      });
    }
  },
  mounted() {
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
  &-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    border: 1px solid $white-ter;
    padding: 0.5rem;
    border-radius: 5px;
  }
}
</style>