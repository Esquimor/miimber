<template>
  <router-link
    class="SettingsOrganizationItem"
    :to="{
      name: 'organization-manage-members',
      params: { id: organization.id }
    }"
  >
    <div class="SettingsOrganizationItem-left">
      <img :src="image" class="SettingsOrganizationItem-image" />
      <span
        class="SettingsOrganizationItem-info is-size-6 has-text-weight-semibold"
        >{{ organization.name }}</span
      >
    </div>
    <button class="SettingsOrganizationItem-button button is-primary">
      {{ $t("core.utils.manage") }}
    </button>
  </router-link>
</template>

<script>
"use strict";

export default {
  name: "SettingsOrganizationItem",
  props: {
    organization: {
      type: Object,
      required: true
    }
  },
  computed: {
    image() {
      return this.organization.image
        ? this.organization.image
        : require("@/assets/imagePlaceholder.png");
    }
  }
};
</script>

<style lang="scss">
.SettingsOrganizationItem {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0.5rem 0;
  margin: 0.5rem 0.4rem;
  &:hover {
    background-color: $white-bis;
  }
  &:nth-child(even) {
    background-color: $grey-lightest;
  }
  &-left {
    display: flex;
    align-items: center;
  }
  &-image {
    max-width: 50px;
  }
  &-info {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    align-items: flex-start;
    padding-left: 0.5rem;
    color: $black-ter;
    &-name {
      text-overflow: ellipsis;
    }
  }
  &-button {
    margin-right: 2rem;
  }
}
</style>
