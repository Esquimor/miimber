<template>
  <BDropdown aria-role="list" positio="is-bottom-left">
    <button class="button is-primary" slot="trigger">
      <span>{{ $t("core.utils.manage") }}</span>
      <BIcon icon="settings" size="is-small" />
    </button>
    <BDropdownItem
      class="CoreDropdownItem is-primary"
      aria-role="listitem"
      @click="$emit('edit')"
    >
      <BIcon icon="pencil" />
      <span class="is-size-6">{{ $t("core.utils.edit") }}</span>
    </BDropdownItem>
    <BDropdownItem
      class="CoreDropdownItem is-danger"
      aria-role="listitem"
      @click="$emit('delete')"
    >
      <BIcon icon="delete" />
      <span class="is-size-6">{{ $t("core.utils.delete") }}</span>
    </BDropdownItem>
  </BDropdown>
</template>

<script>
"use strict";

export default {
  name: "OrganizationTypeSessionsDropdown"
};
</script>

<style></style>
