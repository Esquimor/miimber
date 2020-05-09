<template>
  <div class="DashboardSessionCommentAdd">
    <editor-content
      class="DashboardSessionCommentAdd-editor"
      :editor="editor"
      ref="editor"
    />
    <div v-if="hasFocus" class="DashboardSessionCommentAdd-button">
      <BButton
        class="DashboardSessionCommentAdd-button-cancel"
        type="is-normal"
        @click.native="cancel"
        :disabled="loading"
        >{{ $t("core.utils.cancel") }}</BButton
      >
      <BButton type="is-primary" @click.native="add" :loading="loading">{{
        $t("dashboard.session.comment.add")
      }}</BButton>
    </div>
  </div>
</template>

<script>
"use strict";

import { Editor, EditorContent } from "tiptap";

import { Placeholder } from "tiptap-extensions";

export default {
  name: "DashboardSessionCommentAdd",
  components: {
    EditorContent
  },
  data() {
    return {
      editor: null,
      hasFocus: false,
      loading: false
    };
  },
  methods: {
    cancel() {
      this.editor.setContent("");
      this.hasFocus = false;
    },
    add() {
      if (this.$refs.editor.$el.textContent === "") {
        this.hasFocus = false;
        return;
      }
      if (this.loading) return;
      this.loading = true;
      this.$store
        .dispatch("dashboard/addCommment", {
          comment: this.editor.getHTML()
        })
        .then(() => {
          this.$buefy.toast.open({
            message: this.$t("dashboard.session.comment.success"),
            type: "is-success"
          });
          this.editor.setContent("");
          this.loading = false;
          this.hasFocus = false;
        })
        .catch(() => {
          this.editor.setContent("");
          this.loading = false;
          this.hasFocus = false;
        });
    }
  },
  mounted() {
    this.editor = new Editor({
      content: "",
      onFocus: () => {
        this.hasFocus = true;
      },
      onBlur: () => {
        if (this.$refs.editor.$el.textContent === "") {
          this.hasFocus = false;
        }
      },
      extensions: [
        new Placeholder({
          emptyEditorClass: "is-editor-empty",
          emptyNodeClass: "is-empty",
          emptyNodeText: this.$t("dashboard.session.comment.add"),
          showOnlyWhenEditable: true,
          showOnlyCurrent: true
        })
      ]
    });
  },
  beforeDestroy() {
    this.editor.destroy();
  }
};
</script>

<style lang="scss">
.DashboardSessionCommentAdd {
  &-editor {
    border-bottom: 1px solid $grey;
    padding: 0.5rem;
  }
  &-button {
    display: flex;
    justify-content: flex-end;
    padding: 1rem 0rem;
    &-cancel {
      margin-right: 1rem;
    }
  }
  p.is-editor-empty:first-child::before {
    content: attr(data-empty-text);
    float: left;
    color: #aaa;
    pointer-events: none;
    height: 0;
    font-style: italic;
  }
}
</style>
