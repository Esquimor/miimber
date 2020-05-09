<template>
  <TemplateOrganization :loading="loading">
    <div class="DashboardOrganizationForumTalk">
      <router-link
        :to="{
          name: 'dashboard-organization-forum-subject',
          params: { idSubject: $route.params.idSubject }
        }"
        class="DashboardOrganizationForumTalk-header title is-6"
        >{{ talk.title }}</router-link
      >
      <div class="DashboardOrganizationForumTalk-messages">
        <OrganizationForumMessageItem
          v-for="message in orderMessageByDate"
          :key="message.id"
          :message="message"
        />
      </div>
      <div class="DashboardOrganizationForumTalk-add">
        <span class="DashboardOrganizationForumTalk-add-title"
          >Ajouter un message</span
        >
        <EditorContent
          :editor="message"
          ref="editor"
          @click.native="setFocusMessage"
          class="DashboardOrganizationForumTalk-add-editor"
        />
        <div v-if="hasFocus" class="DashboardOrganizationForumTalk-add-buttons">
          <BButton
            type="is-normal"
            @click.native="cancel"
            class="DashboardOrganizationForumTalk-add-buttons-cancel"
            :disabled="loadingMessage"
            >{{ $t("core.utils.cancel") }}</BButton
          >
          <BButton
            type="is-primary"
            @click.native="add"
            :loading="loadingMessage"
            >{{ $t("core.utils.add") }}</BButton
          >
        </div>
      </div>
    </div>
  </TemplateOrganization>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

import { Editor, EditorContent } from "tiptap";

import { Placeholder } from "tiptap-extensions";

import dayjs from "dayjs";

import TemplateOrganization from "@dashboard/template/TemplateOrganization";

import OrganizationForumMessageItem from "@dashboard/components/organization/forum/OrganizationForumMessageItem";

export default {
  name: "DashboardOrganizationForumTalk",
  components: {
    TemplateOrganization,
    OrganizationForumMessageItem,
    EditorContent
  },
  data() {
    return {
      loading: true,
      hasFocus: false,
      message: "",
      loadingMessage: false
    };
  },
  computed: {
    ...mapGetters({
      talk: "dashboard/organizationForumTalk",
      isMemberOrganization: "dashboard/isMemberOrganization",
      isOrganizationArchived: "dashboard/isOrganizationArchived",
      isOrganizationSuspended: "dashboard/isOrganizationSuspended"
    }),
    orderMessageByDate() {
      if (!this.talk.messages) return [];
      // eslint-disable-next-line vue/no-side-effects-in-computed-properties
      return this.talk.messages.sort((a, b) =>
        dayjs(a.dateMessage).isAfter(dayjs(b.dateMessage))
      );
    }
  },
  methods: {
    add() {
      if (this.$refs.editor.$el.textContent === "") {
        this.hasFocus = false;
        return;
      }
      if (this.loadingMessage) return;
      this.loadingMessage = true;
      this.$store
        .dispatch("dashboard/addMessage", {
          message: this.message.getHTML()
        })
        .then(() => {
          this.$buefy.toast.open({
            message: this.$t("dashboard.session.comment.success"),
            type: "is-success"
          });
          this.message.setContent("");
          this.loadingMessage = false;
          this.hasFocus = false;
        })
        .catch(() => {
          this.message.setContent("");
          this.loadingMessage = false;
          this.hasFocus = false;
        });
    },
    cancel() {
      this.message.setContent("");
      this.hasFocus = false;
    },
    setFocusMessage() {
      this.message.focus();
    }
  },
  mounted() {
    if (!this.isMemberOrganization) {
      this.$router.push({ name: "error_404" });
      this.loading = false;
      return;
    }
    if (this.isOrganizationArchived) {
      this.$router.push({
        name: "dashboard-organization-archived",
        params: { id: this.$route.params.id }
      });
      this.loading = false;
      return;
    }
    if (this.isOrganizationSuspended) {
      this.$router.push({
        name: "dashboard-organization-suspended",
        params: { id: this.$route.params.id }
      });
      this.loading = false;
      return;
    }
    this.$store
      .dispatch("dashboard/setForumTalk", this.$route.params.idTalk)
      .then(() => {
        this.message = new Editor({
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
              emptyNodeText: this.$t(
                "dashboard.organization.subjectForum.label.placeholder"
              ),
              showOnlyWhenEditable: true,
              showOnlyCurrent: true
            })
          ]
        });
        this.loading = false;
      });
  },
  beforeDestroy() {
    if (this.message) this.message.destroy();
  }
};
</script>

<style lang="scss">
.DashboardOrganizationForumTalk {
  &-add {
    padding: 0.5rem;
    &-editor {
      cursor: text;
      box-shadow: inset 0 1px 2px rgba(10, 10, 10, 0.1);
      max-width: 100%;
      width: 100%;
      padding: 0.5rem;
      min-height: 100px;
      border: 1px solid #b5b5b5;
      border-radius: 5px;
      margin-top: 0.5rem;

      p.is-editor-empty:first-child::before {
        content: attr(data-empty-text);
        float: left;
        color: #aaa;
        pointer-events: none;
        height: 0;
        font-style: italic;
      }
    }
    &-buttons {
      display: flex;
      justify-content: flex-end;
      padding: 0.5rem;
      &-cancel {
        margin-right: 1rem;
      }
    }
  }
}
</style>
