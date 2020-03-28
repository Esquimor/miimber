<template>
  <TemplateModal
    :title="$t('organization.organization.edit.title')"
    :loading="loading"
    hasPadding
    @cancel="$emit('close')"
    @confirm="confirm"
  >
    <div class="columns">
      <div class="column">
        <BField :label="$t('organization.organization.label.name')">
          <BInput v-model="editName" required></BInput>
        </BField>
      </div>
    </div>
  </TemplateModal>
</template>

<script>
"use strict";

import TemplateModal from "@core/template/TemplateModal";

export default {
  name: "OrganizationOrganizationEdit",
  components: {
    TemplateModal
  },
  props: {
    name: {
      type: String,
      default: ""
    }
  },
  data() {
    return {
      loading: false,
      editName: this.name
    };
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
          this.$emit("close");
        })
        .catch(() => {
          this.$emit("close");
        });
    }
  },
  watch: {
    name(newVal) {
      this.editName = newVal;
    }
  }
};
</script>

<style lang="scss"></style>
