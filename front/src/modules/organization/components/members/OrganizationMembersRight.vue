<template>
  <TemplateModal
    :title="title"
    :size="size"
    :customSize="true"
    :loading="loading"
    @cancel="$emit('close')"
    @confirm="confirm"
  >
    <div class="OrganizationMembersRight">
      <BNotification
        v-if="error"
        id="OrganizationMembersRight-error"
        type="is-danger"
        aria-close-label="Close notification"
        role="alert"
        @close="error = false"
        >{{ $t("organization.members.rightModal.error") }}</BNotification
      >
      <OrganizationMembersRightItem
        id="OrganizationMembersRight-owner"
        :class="{ unselectable: !memberIsOwner }"
        :title="$t('core.role.OWNER')"
        :description="$t('organization.members.rightModal.OWNER.description')"
        :selected="ROLE.OWNER === editRole"
        @click.native="changeRole(ROLE.OWNER)"
      />
      <OrganizationMembersRightItem
        id="OrganizationMembersRight-officeInsctructor"
        :title="$t('core.role.OFFICE_INSTRUCTOR')"
        :description="
          $t('organization.members.rightModal.OFFICE_INSTRUCTOR.description')
        "
        :selected="ROLE.OFFICE_INSTRUCTOR === editRole"
        @click.native="changeRole(ROLE.OFFICE_INSTRUCTOR)"
      />
      <OrganizationMembersRightItem
        id="OrganizationMembersRight-office"
        :title="$t('core.role.OFFICE')"
        :description="$t('organization.members.rightModal.OFFICE.description')"
        :selected="ROLE.OFFICE === editRole"
        @click.native="changeRole(ROLE.OFFICE)"
      />
      <OrganizationMembersRightItem
        id="OrganizationMembersRight-instructor"
        :title="$t('core.role.INSTRUCTOR')"
        :description="
          $t('organization.members.rightModal.INSTRUCTOR.description')
        "
        :selected="ROLE.INSTRUCTOR === editRole"
        @click.native="changeRole(ROLE.INSTRUCTOR)"
      />
      <OrganizationMembersRightItem
        id="OrganizationMembersRight-member"
        :title="$t('core.role.MEMBER')"
        :description="$t('organization.members.rightModal.MEMBER.description')"
        :selected="ROLE.MEMBER === editRole"
        @click.native="changeRole(ROLE.MEMBER)"
      />
    </div>
  </TemplateModal>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

import { ROLE } from "@/utils/consts";

import TemplateModal from "@core/template/TemplateModal";

import OrganizationMembersRightItem from "@organization/components/members/OrganizationMembersRightItem";

export default {
  name: "OrganizationMembersRight",
  components: {
    TemplateModal,
    OrganizationMembersRightItem
  },
  props: {
    member: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      error: false,
      loading: false,
      ROLE: ROLE,
      editRole: ROLE.MEMBER,
      size: {
        width: "450px",
        height: "auto"
      }
    };
  },
  computed: {
    ...mapGetters({
      memberIsOwner: "core/memberIsOwner"
    }),
    title() {
      return `${this.$t("organization.members.rightModal.title")} ${
        this.member.firstName
      } ${this.member.lastName} `;
    }
  },
  methods: {
    changeRole(role) {
      if (!this.memberIsOwner && role === ROLE.OWNER) return;
      this.editRole = role;
    },
    confirm() {
      this.loading = true;
      this.$store
        .dispatch("organization/changeRight", {
          id: this.member.id,
          role: this.editRole
        })
        .then(() => {
          this.$emit("close");
        })
        .catch(() => {
          this.loading = false;
          this.error = true;
        });
    }
  },
  watch: {
    member: {
      immediate: true,
      handler(newVal) {
        this.editRole = newVal.role;
      }
    }
  }
};
</script>

<style lang="scss">
.OrganizationMembersRight {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-content: center;
  height: 100%;
}
</style>
