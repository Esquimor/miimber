<template>
  <div
    class="OrganizationMembersRightItem"
    :class="{ 'is-selected': selected }"
  >
    <div class="OrganizationMembersRightItem-right">
      <BIcon :icon="getIcon" />
    </div>
    <div class="OrganizationMembersRightItem-left">
      <h4 class="has-text-weight-bold">{{ title }}</h4>
      <p>{{ description }}</p>
    </div>
  </div>
</template>

<script>
"use strict";

export default {
  name: "OrganizationMembersRightItem",
  props: {
    title: {
      type: String,
      default: ""
    },
    description: {
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
.OrganizationMembersRightItem {
  cursor: pointer;
  display: flex;
  padding: 0.5rem;
  &.unselectable {
    cursor: not-allowed;
  }
  &.is-selected {
    background-color: $background-price;
    .OrganizationMembersRightItem-right {
      color: $primary;
    }
  }
  &:hover {
    background-color: $grey-lightest;
  }
  &-right {
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 0.75rem;
  }
  &-left {
    display: flex;
    flex-direction: column;
  }
}
</style>
