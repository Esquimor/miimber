<template>
  <TemplateSidePanelRight
    :title="$t('organization.typeSessions.edit.title')"
    :loading="loading"
    hasPadding
    @confirm="confirm"
    :disabled="!canConfirm"
  >
    <div class="columns">
      <div class="column is-half">
        <BField :label="$t('organization.typeSessions.label.name')">
          <BInput v-model.trim="typeSession.name" required />
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
  name: "OrganizationTypeSessionsEdit",
  components: {
    TemplateSidePanelRight
  },
  data() {
    return {
      loading: false,
      typeSession: {
        id: 0,
        name: ""
      }
    };
  },
  computed: {
    ...mapGetters({
      props: "core/sideBarProps"
    }),
    canConfirm() {
      return this.typeSession.name;
    }
  },
  methods: {
    confirm() {
      if (!this.canConfirm) return;
      this.loading = true;
      this.$store
        .dispatch("organization/editTypeSession", this.typeSession)
        .then(() => {
          this.$buefy.toast.open({
            message: this.$t("organization.typeSessions.edit.success"),
            type: "is-primary"
          });
          this.$store.dispatch("core/closeSideBar");
        });
    }
  },
  watch: {
    "props.element": {
      immediate: true,
      handler(newVal) {
        this.typeSession = newVal;
      }
    }
  }
};
</script>

<style lang="scss"></style>
