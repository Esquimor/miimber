<template>
  <OrganizationTemplateList
    :title="$t('organization.members.title')"
    @add="add"
    :loading="false"
  >
    <BTable :data="members" striped paginated :per-page="25">
      <template v-slot="{ row }">
        <BTableColumn
          field="firstName"
          :label="$t('organization.members.table.firstName')"
          sortable
          >{{ row.firstName }}</BTableColumn
        >
        <BTableColumn
          field="lastName"
          :label="$t('organization.members.table.lastName')"
          sortable
          >{{ row.lastName }}</BTableColumn
        >
        <BTableColumn
          field="role"
          :label="$t('organization.members.table.role')"
          >{{ $t(`core.role.${row.role}`) }}</BTableColumn
        >
        <BTableColumn class="OrganizationMembers-column-manage" :width="200">
          <OrganizationMembersDropdown
            :id="`OrganizationMembersDropdown-${row.id}`"
            @changeRight="changeRight(row)"
            @remove="remove(row.id)"
          />
        </BTableColumn>
      </template>
    </BTable>
  </OrganizationTemplateList>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

import OrganizationTemplateList from "@organization/templates/OrganizationTemplateList";

import OrganizationMembersAdd from "@organization/components/members/OrganizationMembersAdd";
import OrganizationMembersRight from "@organization/components/members/OrganizationMembersRight";

import OrganizationMembersDropdown from "@organization/components/members/OrganizationMembersDropdown";

export default {
  name: "OrganizationMembers",
  components: {
    OrganizationTemplateList,
    OrganizationMembersDropdown
  },
  computed: {
    ...mapGetters({
      members: "organization/organizationMembers",
      canChangeOrganization: "core/canChangeOrganization"
    })
  },
  methods: {
    changeRight(member) {
      this.$buefy.modal.open({
        parent: this,
        component: OrganizationMembersRight,
        canCancel: false,
        props: {
          member
        }
      });
    },
    remove(id) {
      this.$store.dispatch("organization/removeMember", id).then(() => {
        this.$buefy.toast.open({
          message: this.$t("organization.members.remove.success"),
          type: "is-success"
        });
      });
    },
    add() {
      this.$store.dispatch("core/openSideBar", {
        component: OrganizationMembersAdd
      });
    }
  }
};
</script>

<style lang="scss">
.OrganizationMembers {
  &-right {
    &:hover {
      background-color: $warning !important;
    }
  }
  &-delete {
    &:hover {
      background-color: $danger !important;
    }
  }
  &-dropdown {
    &-item {
      display: flex !important;
      align-content: center;
      justify-content: flex-start;
      > .icon {
        width: 35px;
      }
    }
  }
  &-column {
    &-manage {
      justify-content: center !important;
    }
  }
}
</style>
