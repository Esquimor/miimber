<template>
  <TemplateModal
    :title="$t('organization.members.add.title')"
    :loading="loading"
    @cancel="$emit('close')"
    @confirm="confirm"
  >
    <div class="OrganizationMembersAdd">
      <BField :label="$t('organization.members.add.label.email')">
        <BInput
          type="email"
          v-model="email"
          @blur="verifyMember"
          @input="emptyMember"
          required
        ></BInput>
      </BField>
      <div v-if="noMember" class="OrganizationMembersAdd-noMember">
        <BNotification
          class="OrganizationMembersAdd-noMember-notification"
          type="is-info"
          aria-close-label="Close notification"
          >{{ $t("organization.members.add.noMember") }}</BNotification
        >
        <div class="columns">
          <div class="column">
            <BField :label="$t('organization.members.add.label.firstName')">
              <BInput v-model="firstName" required></BInput>
            </BField>
          </div>
          <div class="column">
            <BField :label="$t('organization.members.add.label.lastName')">
              <BInput v-model="lastName" required></BInput>
            </BField>
          </div>
        </div>
      </div>
      <BNotification
        v-else-if="alreadyExist"
        class="OrganizationMembersAdd-alreadyExist"
        type="is-danger"
        aria-close-label="Close notification"
        >{{ $t("organization.members.add.alreadyExist") }}</BNotification
      >
      <div
        v-else-if="!noMember && !!member"
        class="OrganizationMembersAdd-member"
      >
        <div class="columns">
          <div class="column">
            <BField :label="$t('organization.members.add.label.firstName')">
              <span>{{ member.firstName }}</span>
            </BField>
          </div>
          <div class="column">
            <BField :label="$t('organization.members.add.label.lastName')">
              <span>{{ member.lastName }}</span>
            </BField>
          </div>
        </div>
      </div>
    </div>
  </TemplateModal>
</template>

<script>
"use strict";

import api from "@/utils/api";

import { mapGetters } from "vuex";

import TemplateModal from "@core/template/TemplateModal";

export default {
  name: "OrganizationMembersAdd",
  components: {
    TemplateModal
  },
  data() {
    return {
      loading: false,
      email: "",
      firstName: "",
      lastName: "",
      member: null,
      noMember: false,
      alreadyExist: false
    };
  },
  computed: {
    ...mapGetters({
      organization: "organization/organization"
    })
  },
  methods: {
    confirm() {
      if (this.noMember) {
        if (!this.firstName || !this.lastName) return;
        this.$store
          .dispatch("organization/addMemberAndUser", {
            organizationId: this.organization.id,
            email: this.email,
            firstName: this.firstName,
            lastName: this.lastName
          })
          .then(() => {
            this.$buefy.toast.open({
              message: this.$t("organization.members.add.success"),
              type: "is-success"
            });
            this.$emit("close");
          })
          .catch(() => {
            this.$emit("close");
          });
      } else {
        this.$store
          .dispatch("organization/addMember", {
            organizationId: this.organization.id,
            userId: this.member.id
          })
          .then(() => {
            this.$buefy.toast.open({
              message: this.$t("organization.members.add.success"),
              type: "is-success"
            });
            this.$emit("close");
          })
          .catch(() => {
            this.$emit("close");
          });
      }
    },
    emptyMember() {
      (this.member = null), (this.noMember = false);
    },
    verifyMember() {
      if (!this.email) return;
      this.emptyMember();
      api
        .get(`user/email/${this.email}/${this.organization.id}`)
        .then(({ data }) => {
          this.member = data;
        })
        .catch(e => {
          if (e.response) {
            if (e.response.status === 404) this.noMember = true;
            else if (e.response.status === 409) {
              this.alreadyExist = true;
              this.email = "";
            }
          }
        });
    }
  }
};
</script>

<style lang="scss">
.OrganizationMembersAdd {
  padding: 0.75rem;
  &-noMember {
    margin-bottom: 0.5rem;
  }
  &-member {
    margin-bottom: 0.5rem;
  }
}
</style>
