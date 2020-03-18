<template>
  <OrganizationTemplateList :title="$t('organization.members.title')" @add="add" :loading="false">
    <BTable :data="members" striped paginated :per-page="25">
      <template v-slot="{ row }">
        <BTableColumn
          field="firstName"
          :label="$t('organization.members.table.firstName')"
          width="250"
          sortable
        >{{ row.firstName }}</BTableColumn>
        <BTableColumn
          field="lastName"
          :label="$t('organization.members.table.lastName')"
          width="250"
          sortable
        >{{ row.lastName }}</BTableColumn>
        <BTableColumn
          field="role"
          :label="$t('organization.members.table.role')"
          width="250"
        >{{ $t(`core.role.${row.role}`) }}</BTableColumn>
        <BTableColumn class="OrganizationMembers-column-manage">
          <BDropdown aria-role="list">
            <button class="button is-primary" slot="trigger">
              <span>{{ $t("organization.members.manage") }}</span>
              <BIcon icon="settings" size="is-small" />
            </button>

            <BDropdownItem
              class="OrganizationMembers-right OrganizationMembers-dropdown-item"
              aria-role="listitem"
              @click="changeRight(row)"
            >
              <BIcon icon="account-key" />
              <span class="is-size-6">
                {{
                $t("organization.members.right")
                }}
              </span>
            </BDropdownItem>
            <BDropdownItem
              class="OrganizationMembers-delete OrganizationMembers-dropdown-item"
              aria-role="listitem"
              @click="remove(row.id)"
            >
              <BIcon icon="delete" />
              <span class="is-size-6">{{ $t("core.utils.remove") }}</span>
            </BDropdownItem>
          </BDropdown>
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

export default {
  name: "OrganizationMembers",
  components: {
    OrganizationTemplateList
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
      this.$buefy.modal.open({
        parent: this,
        component: OrganizationMembersAdd,
        canCancel: false
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
