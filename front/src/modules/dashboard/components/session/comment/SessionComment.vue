<template>
  <div class="DashboardSessionComment">
    <h2 class="title is-5">
      {{ comments.length }} {{ $t("dashboard.session.comment.title") }}
    </h2>
    <SessionCommentAdd />
    <div class="DashboardSessionComment-comments">
      <SessionCommentItem
        class="DashboardSessionComment-comments-item"
        v-for="comment in comments"
        :key="comment.item"
        :comment="comment"
      />
    </div>
  </div>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

import SessionCommentAdd from "@dashboard/components/session/comment/SessionCommentAdd";
import SessionCommentItem from "@dashboard/components/session/comment/SessionCommentItem";

export default {
  name: "DashboardSessionComment",
  components: {
    SessionCommentAdd,
    SessionCommentItem
  },
  computed: {
    ...mapGetters({
      comments: "dashboard/sessionComments"
    })
  }
};
</script>

<style lang="scss">
.DashboardSessionComment {
  padding-top: 1rem;
  &-comments {
    padding: 2rem 0rem;
    &-item {
      &:nth-child(odd) {
        background-color: $white;
      }
    }
  }
}
</style>
