<template>
  <BDropdown aria-role="list" positio="is-bottom-left">
    <button class="button is-primary" slot="trigger">
      <span>{{ $t("core.utils.manage") }}</span>
      <BIcon icon="settings" size="is-small" />
    </button>
    <BDropdownItem
      id="OrganizationMembersDropdown-right"
      class="CoreDropdownItem is-warning"
      aria-role="listitem"
      @click="$emit('changeRight')"
    >
      <BIcon icon="pencil" />
      <span class="is-size-6">{{ $t("organization.members.right") }}</span>
    </BDropdownItem>
    <BDropdownItem
      id="OrganizationMembersDropdown-delete"
      class="CoreDropdownItem is-danger"
      aria-role="listitem"
      @click="$emit('remove')"
    >
      <BIcon icon="delete" />
      <span class="is-size-6">{{ $t("core.utils.delete") }}</span>
    </BDropdownItem>
  </BDropdown>
</template>

<script>
"use strict";

export default {
  name: "OrganizationMembersDropdown"
};
</script>

<style lang="scss"></style>
