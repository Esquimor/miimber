<template>
  <div class="DashboardSessionUserItem">
    <img :src="image" class="DashboardSessionUserItem-image" />
    <span class="DashboardSessionUserItem-firstName">{{ user.firstName }}</span>
    <span class="DashboardSessionUserItem-lastName">{{ user.lastName }}</span>
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
  justify-content: center;
  align-items: center;
  background-color: $white;
  width: 100px;
  padding: 0.5rem;
  box-shadow: 0 3px 4px 0 $grey;
  margin: 0.5rem 0.75rem;
  border-radius: 5px;
  &-image {
    max-width: 75px;
    border-radius: 5px;
  }
}
</style>