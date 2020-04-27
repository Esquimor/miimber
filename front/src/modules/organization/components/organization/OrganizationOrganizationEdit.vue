<template>
  <TemplateSidePanelRight
    :title="$t('organization.organization.edit.title')"
    :loading="loading"
    hasPadding
    :disabled="!canConfirm"
    @confirm="confirm"
  >
    <div class="columns">
      <div class="column">
        <BField :label="$t('organization.organization.label.name')">
          <BInput
            id="OrganizationOrganizationEdit-name"
            v-model.trim="editName"
            required
          ></BInput>
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
  name: "OrganizationOrganizationEdit",
  components: {
    TemplateSidePanelRight
  },
  data() {
    return {
      loading: false,
      editName: this.name
    };
  },
  computed: {
    ...mapGetters({
      props: "core/sideBarProps"
    }),
    canConfirm() {
      return this.editName;
    }
  },
  methods: {
    confirm() {
      if (!this.editName) return;
      this.$store
        .dispatch("organization/editOrganization", this.editName)
        .then(() => {
          this.$buefy.toast.open({
            message: this.$t("organization.organization.edit.success"),
            type: "is-success"
          });
          this.$store.dispatch("core/closeSideBar");
        })
        .catch(() => {
          this.$store.dispatch("core/closeSideBar");
        });
    }
  },
  watch: {
    "props.name": {
      immediate: true,
      handler(newVal) {
        this.editName = newVal;
      }
    }
  }
};
</script>

<style lang="scss"></style>
