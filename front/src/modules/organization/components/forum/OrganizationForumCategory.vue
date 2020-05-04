<template>
  <div class="OrganizationForumCategory">
    <div class="OrganizationForumCategory-category">
      <template v-if="mode === 'view'">
        <span>{{ category.title }}</span>
        <div class="OrganizationForumCategory-category-buttons">
          <BButton
            class="OrganizationForumCategory-category-buttons-edit"
            icon-left="pencil"
            type="is-primary"
            @click.native="passToEdit"
            >Modifier</BButton
          >
          <BButton
            @click.native="remove"
            icon-left="delete"
            type="is-danger"
            outlined
          />
        </div>
      </template>
      <template v-else>
        <BInput v-model="titleEdit" />
        <div class="OrganizationForumCategory-category-buttons">
          <BButton
            class="OrganizationForumCategory-category-buttons-edit"
            icon-left="content-save"
            type="is-primary"
            :loading="loading"
            @click.native="save"
            >Enregistrer</BButton
          >
          <BButton
            @click.native="passToView"
            icon-left="close"
            type="is-danger"
            outlined
          />
        </div>
      </template>
    </div>
    <div class="OrganizationForumCategory-subjects">
      <OrganizationForumSubject
        v-for="subject in category.subjects"
        :key="subject.id"
        :subject="subject"
      />
    </div>
  </div>
</template>

<script>
"use strict";

import OrganizationForumSubject from "@organization/components/forum/OrganizationForumSubject";

export default {
  name: "OrganizationForumCategory",
  components: {
    OrganizationForumSubject
  },
  data() {
    return {
      mode: "view",
      titleEdit: "",
      loading: false
    };
  },
  props: {
    category: {
      type: Object,
      required: true
    }
  },
  methods: {
    passToEdit() {
      this.titleEdit = this.category.title;
      this.mode = "edit";
    },
    passToView() {
      this.mode = "view";
    },
    save() {
      if (this.loading) return;
      this.loading = true;
      this.$store
        .dispatch("organization/editForumCategory", {
          id: this.category.id,
          title: this.titleEdit,
          position: this.category.position
        })
        .then(() => {
          this.loading = false;
          this.mode = "view";
        })
        .catch(() => {
          this.loading = false;
        });
    },
    remove() {
      this.$buefy.dialog.confirm({
        title: this.$t("organization.sessions.delete.title"),
        message: this.$t("organization.sessions.delete.message"),
        confirmText: this.$t("core.utils.delete"),
        type: "is-danger",
        hasIcon: true,
        onConfirm: () => {
          this.$store
            .dispatch("organization/deleteForumCategory", this.category.id)
            .then(() => {
              this.$buefy.toast.open({
                message: this.$t("organization.sessions.delete.success"),
                type: "is-primary"
              });
            });
        }
      });
    }
  }
};
</script>

<style lang="scss">
.OrganizationForumCategory {
  &-category {
    display: flex;
    justify-content: space-between;
    align-items: center;
    border: 1px solid $grey-lightest;
    border-radius: 5px;
    padding: 0.5rem;
    &-buttons {
      &-edit {
        margin-right: 1rem;
      }
    }
  }
}
</style>
