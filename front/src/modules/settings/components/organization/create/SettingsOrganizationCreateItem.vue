<template>
  <div
    class="SettingsOrganizationCreateItem"
    :class="{ 'is-selected': selected }"
    @click="$emit('selected')"
  >
    <h2 class="SettingsOrganizationCreateItem-title title is-5">{{ title }}</h2>
    <div class="SettingsOrganizationCreateItem-price">{{ price }}</div>
    <BIcon :icon="getIcon" class="SettingsOrganizationCreateItem-icon"></BIcon>
  </div>
</template>

<script>
"use strict";

export default {
  name: "SettingsOrganizationCreateItem",
  props: {
    title: {
      type: String,
      default: ""
    },
    price: {
      type: String,
      default: ""
    },
    selected: {
      type: Boolean,
      default: false
    }
  },
  computed: {
    getIcon() {
      return this.selected ? "check-circle-outline" : "";
    }
  }
};
</script>

<style lang="scss">
.SettingsOrganizationCreateItem {
  cursor: pointer;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

  margin: 1rem 1rem;

  padding: 1rem;
  border: 1px solid $grey;
  width: 250px;

  border-radius: 10px;
  &.is-selected {
    border-color: $primary;
    background-color: $background-price;
    > .SettingsOrganizationCreateItem-icon {
      color: $primary;
    }
  }
  &-icon {
    margin-top: 1rem;
  }
}
</style>
