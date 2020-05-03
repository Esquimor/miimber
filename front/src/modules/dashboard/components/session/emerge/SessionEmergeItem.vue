<template>
  <div class="DashboardSessionEmergeItem" @click="setUser">
    <img :src="image" class="DashboardSessionEmergeItem-image" />
    <div class="DashboardSessionEmergeItem-name">
      {{ user.firstName }} {{ user.lastName }}
    </div>
    <BCheckbox
      :value="!!user.attendeeId"
      class="DashboardSessionEmergeItem-check"
    />
  </div>
</template>

<script>
"use strict";

export default {
  name: "DashboardSessionEmergeItem",
  props: {
    user: {
      type: Object,
      required: true
    }
  },
  computed: {
    image() {
      return this.user.image
        ? this.user.image
        : require("@/assets/imagePlaceholder.png");
    }
  },
  methods: {
    setUser() {
      this.$emit("check", this.user);
    }
  }
};
</script>

<style lang="scss">
.DashboardSessionEmergeItem {
  display: flex;
  align-items: center;
  margin: 0.2rem;
  cursor: pointer;
  &:nth-child(odd) {
    background-color: $white-bis;
  }
  &-image {
    max-width: 40px;
    border-radius: 5px;
  }
  &-name {
    padding: 0.5rem;
    flex-grow: 1;
  }
  &-check {
    padding-right: 1rem;
  }
}
</style>
