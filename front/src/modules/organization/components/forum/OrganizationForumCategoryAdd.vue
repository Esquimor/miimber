<template>
  <TemplateSidePanelRight
    :title="$t('organization.forum.add.title')"
    :loading="loading"
    @confirm="confirm"
    :disabled="!canConfirm"
  >
    <div class="columns">
      <div class="column">
        <BField :label="$t('organization.forum.label.title')">
          <BInput v-model.trim="title" required></BInput>
        </BField>
      </div>
    </div>
  </TemplateSidePanelRight>
</template>

<script>
"use strict";

import TemplateSidePanelRight from "@core/template/TemplateSidePanelRight";

export default {
  name: "OrganizationForumCategoryAdd",
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
        .dispatch("organization/addCategoryForum", {
          title: this.title
        })
        .then(() => {
          this.$buefy.toast.open({
            message: this.$t("organization.forum.add.success"),
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
