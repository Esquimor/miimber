<template>
  <TemplateSidePanelRight
    :title="$t('organization.subjectforum.add.title')"
    :loading="loading"
    @confirm="confirm"
    :disabled="!canConfirm"
  >
    <div class="columns">
      <div class="column">
        <BField :label="$t('organization.subjectforum.label.title')">
          <BInput v-model.trim="title" required></BInput>
        </BField>
      </div>
    </div>
  </TemplateSidePanelRight>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

import TemplateSidePanelRight from "@core/template/TemplateSidePanelRight";

export default {
  name: "OrganizationForumSubjectAdd",
  components: {
    TemplateSidePanelRight
  },
  data() {
    return {
      loading: false,
      title: ""
    };
  },
  computed: {
    ...mapGetters({
      sideBarProps: "core/sideBarProps"
    }),
    canConfirm() {
      return this.title !== "";
    }
  },
  methods: {
    confirm() {
      if (this.loading) return;
      if (!this.canConfirm) return;
      this.loading = true;
      this.$store
        .dispatch("organization/addSubjectForum", {
          title: this.title,
          idCat: this.sideBarProps.idCat
        })
        .then(() => {
          this.$buefy.toast.open({
            message: this.$t("organization.forum.subjectforum.success"),
            type: "is-primary"
          });
          this.$store.dispatch("core/closeSideBar");
        })
        .catch(() => {
          this.loading = false;
        });
    }
  }
};
</script>

<style lang="scss"></style>
