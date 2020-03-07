<template>
  <div class="OrganizationMembers">
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
              <span>{{ $t('organization.members.manage') }}</span>
              <BIcon icon="settings" size="is-small" />
            </button>

            <BDropdownItem
              class="OrganizationMembers-right OrganizationMembers-dropdown-item"
              aria-role="listitem"
              @click="changeRight(row)"
            >
              <BIcon icon="account-key" />
              <span class="is-size-6">{{ $t('organization.members.right') }}</span>
            </BDropdownItem>
            <BDropdownItem
              class="OrganizationMembers-delete OrganizationMembers-dropdown-item"
              aria-role="listitem"
            >
              <BIcon icon="delete" />
              <span class="is-size-6">{{ $t('core.utils.delete') }}</span>
            </BDropdownItem>
          </BDropdown>
        </BTableColumn>
      </template>
    </BTable>
  </div>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

import OrganizationMembersRight from "@organization/components/members/OrganizationMembersRight";

export default {
  name: "OrganizationMembers",
  computed: {
    ...mapGetters({
      members: "organization/organizationMembers"
    })
  },
  data() {
    return {};
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
