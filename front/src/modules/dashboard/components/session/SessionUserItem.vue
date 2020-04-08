<template>
  <div class="DashboardSessionUserItem">
    <img :src="image" class="DashboardSessionUserItem-image" />
    <span class="DashboardSessionUserItem-name">
      {{ user.firstName }}
      <br />
      {{ user.lastName }}
    </span>
  </div>
</template>

<script>
"use strict";

export default {
  name: "DashboardSessionUserItem",
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
  }
};
</script>

<style lang="scss">
.DashboardSessionUserItem {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  background-color: $white;
  width: 125px;
  height: 170px;
  padding: 0.75rem 0.25rem;
  box-shadow: 0 2px 3px 0 $grey;
  margin: 0.5rem 0.75rem;
  border-radius: 5px;
  text-overflow: ellipsis;
  &-image {
    width: 80px;
    border-radius: 5px;
  }
  &-name {
    text-align: center;
    max-width: calc(125px - 0.5rem);
    height: 48px;
    overflow: hidden;
  }
}
</style>
