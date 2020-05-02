<template>
  <TemplateSidePanelRight
    :title="$t('organization.members.add.title')"
    :loading="loading"
    @confirm="confirm"
    :disabled="!canConfirm"
  >
    <div class="OrganizationMembersAdd">
      <BField :label="$t('organization.members.add.label.email')">
        <BInput
          id="OrganizationOrganizationMembersAdd-email"
          type="email"
          v-model.trim="email"
          :placeholder="$t('core.label.email.placeholder')"
          @blur="verifyMember"
          @input="emptyMember"
          required
        ></BInput>
      </BField>
      <div v-if="noMember" class="OrganizationMembersAdd-noMember">
        <BNotification
          id="OrganizationOrganizationMembers-notification-noMember"
          class="OrganizationMembersAdd-noMember-notification"
          type="is-info"
          aria-close-label="Close notification"
          >{{ $t("organization.members.add.noMember") }}</BNotification
        >
        <div class="columns">
          <div class="column">
            <BField :label="$t('core.label.firstName.label')">
              <BInput
                id="OrganizationOrganizationMembers-firstName"
                v-model.trim="firstName"
                :placeholder="$t('core.label.firstName.placeholder')"
                required
              ></BInput>
            </BField>
          </div>
          <div class="column">
            <BField :label="$t('core.label.lastName.label')">
              <BInput
                id="OrganizationOrganizationMembers-lastName"
                v-model.trim="lastName"
                :placeholder="$t('core.label.lastName.placeholder')"
                required
              ></BInput>
            </BField>
          </div>
        </div>
      </div>
      <BNotification
        v-else-if="alreadyExist"
        id="OrganizationOrganizationMembers-notification-alreadyExist"
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
            <BField :label="$t('core.label.firstName.label')">
              <span>{{ member.firstName }}</span>
            </BField>
          </div>
          <div class="column">
            <BField :label="$t('core.label.lastName.label')">
              <span>{{ member.lastName }}</span>
            </BField>
          </div>
        </div>
      </div>
    </div>
  </TemplateSidePanelRight>
</template>

<script>
"use strict";

import api from "@/utils/api";

import { mapGetters } from "vuex";

import TemplateSidePanelRight from "@core/template/TemplateSidePanelRight";

export default {
  name: "OrganizationMembersAdd",
  components: {
    TemplateSidePanelRight
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
    }),
    canConfirm() {
      if (!this.email) return false;
      if (this.noMember) {
        if (!this.firstName || !this.lastName) return false;
        return true;
      }
      return true;
    }
  },
  methods: {
    confirm() {
      if (this.loading) return;
      if (this.noMember) {
        if (!this.firstName || !this.lastName) return;
        this.loading = true;
        this.$store
          .dispatch("organization/addMemberAndUser", {
            organizationId: this.organization.id,
            email: this.email,
            firstName: this.firstName,
            lastName: this.lastName,
            lang: this.$i18n.locale.toUpperCase()
          })
          .then(() => {
            this.$buefy.toast.open({
              message: this.$t("organization.members.add.success"),
              type: "is-success"
            });
            this.$store.dispatch("core/closeSideBar");
            this.loading = false;
          })
          .catch(() => {
            this.$store.dispatch("core/closeSideBar");
            this.loading = false;
          });
      } else {
        if (!this.member) return;
        this.loading = true;
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
            this.$store.dispatch("core/closeSideBar");
            this.loading = false;
          })
          .catch(() => {
            this.$store.dispatch("core/closeSideBar");
            this.loading = false;
          });
      }
    },
    emptyMember() {
      (this.member = null), (this.noMember = false);
    },
    verifyMember() {
      if (!this.email) return;
      this.loading = true;
      this.emptyMember();
      api
        .get(`user/email/${this.email}/${this.organization.id}`)
        .then(({ data }) => {
          this.member = data;
          this.loading = false;
        })
        .catch(e => {
          if (e.response) {
            if (e.response.status === 404) this.noMember = true;
            else if (e.response.status === 409) {
              this.alreadyExist = true;
              this.email = "";
            } else {
              this.$store.dispatch("core/setError");
              this.$store.dispatch("core/closeSideBar");
            }
          } else {
            this.$store.dispatch("core/setError");
            this.$store.dispatch("core/closeSideBar");
          }
          this.loading = false;
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
