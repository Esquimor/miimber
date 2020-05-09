<template>
  <div class="DashboardSessionCommentItem">
    <div
      class="DashboardSessionCommentItem-head text is-size-7 has-text-weight-medium"
    >
      {{ comment.user.firstName }} {{ comment.user.lastName }}
    </div>
    <div class="DashboardSessionCommentItem-content" v-html="comment.comment" />
  </div>
</template>

<script>
"use strict";

export default {
  name: "DahboardSessionCommentItem",
  props: {
    comment: {
      type: Object,
      required: true
    }
  }
};
</script>

<style lang="scss">
.DashboardSessionCommentItem {
  padding: 1rem 0.5rem;
  border-radius: 5px;
}
</style>
