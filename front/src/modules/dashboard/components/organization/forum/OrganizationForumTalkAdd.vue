<template>
  <TemplateSidePanelRight
    :title="$t('dashboard.organization.subjectForum.add.title')"
    :loading="loading"
    @confirm="confirm"
    :disabled="!canConfirm"
  >
    <div class="columns">
      <div class="column">
        <BField :label="$t('dashboard.organization.subjectForum.label.title')">
          <BInput v-model.trim="title" required></BInput>
        </BField>
      </div>
    </div>
    <div class="columns">
      <div class="column">
        <BField
          :label="$t('dashboard.organization.subjectForum.label.message')"
        >
          <EditorContent
            :editor="message"
            ref="editor"
            class="DashboardOrganizationForumTalkAdd-editor"
            @click.native="setFocusMessage"
          />
        </BField>
      </div>
    </div>
  </TemplateSidePanelRight>
</template>

<script>
"use strict";

import { Editor, EditorContent } from "tiptap";

import { Placeholder } from "tiptap-extensions";

import TemplateSidePanelRight from "@core/template/TemplateSidePanelRight";

export default {
  name: "DashboardOrganizationForumTalkAdd",
  components: {
    TemplateSidePanelRight,
    EditorContent
  },
  data() {
    return {
      title: "",
      message: null,
      loading: false
    };
  },
  computed: {
    canConfirm() {
      if (!this.message) return;
      return this.title !== "" && this.message.getHTML() !== "<p></p>";
    }
  },
  methods: {
    confirm() {
      if (this.loading) return;
      this.loading = true;
      this.$store
        .dispatch("dashboard/addTalk", {
          title: this.title,
          message: this.message.getHTML(),
          idSubject: this.$route.params.idSubject
        })
        .then(() => {
          this.$buefy.toast.open({
            message: this.$t("dashboard.organization.subjectForum.add.success"),
            type: "is-success"
          });
          this.loading = false;
          this.$store.dispatch("core/closeSideBar");
        })
        .catch(() => {
          this.loading = false;
        });
    },
    setFocusMessage() {
      this.message.focus();
    }
  },
  mounted() {
    this.message = new Editor({
      content: "",
      extensions: [
        new Placeholder({
          emptyEditorClass: "is-editor-empty",
          emptyNodeClass: "is-empty",
          emptyNodeText: this.$t(
            "dashboard.organization.subjectForum.label.placeholder"
          ),
          showOnlyWhenEditable: true,
          showOnlyCurrent: true
        })
      ]
    });
  },
  beforeDestroy() {
    this.message.destroy();
  }
};
</script>

<style lang="scss">
.DashboardOrganizationForumTalkAdd {
  &-editor {
    cursor: text;
    box-shadow: inset 0 1px 2px rgba(10, 10, 10, 0.1);
    max-width: 100%;
    width: 100%;
    padding: 0.5rem;
    min-height: 200px;
    border: 1px solid #b5b5b5;
    border-radius: 5px;

    p.is-editor-empty:first-child::before {
      content: attr(data-empty-text);
      float: left;
      color: #aaa;
      pointer-events: none;
      height: 0;
      font-style: italic;
    }
  }
}
</style>
