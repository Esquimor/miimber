<template>
  <div class="OrganizationForumSubject">
    <template v-if="mode === 'view'">
      <span>{{ subject.title }}</span>
      <div class="OrganizationForumSubject-buttons">
        <BButton
          class="OrganizationForumSubject-buttons-edit"
          icon-left="pencil"
          type="is-primary"
          @click.native="passToEdit"
          outlined
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
      <div class="OrganizationForumSubject-buttons">
        <BButton
          class="OrganizationForumSubject-buttons-edit"
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
</template>

<script>
"use strict";

export default {
  name: "OrganizationForumSubject",
  props: {
    subject: {
      type: Object,
      required: true
    },
    idCategory: {
      type: Number,
      required: true
    }
  },
  data() {
    return {
      mode: "view",
      titleEdit: "",
      loading: false
    };
  },
  methods: {
    passToEdit() {
      this.titleEdit = this.subject.title;
      this.mode = "edit";
    },
    passToView() {
      this.mode = "view";
    },
    save() {
      if (this.loading) return;
      this.loading = true;
      this.$store
        .dispatch("organization/editForumSubject", {
          id: this.subject.id,
          title: this.titleEdit,
          position: this.subject.position
        })
        .then(() => {
          this.$buefy.toast.open({
            message: this.$t("organization.subjectforum.edit.success"),
            type: "is-primary"
          });
          this.loading = false;
          this.mode = "view";
        })
        .catch(() => {
          this.loading = false;
        });
    },
    remove() {
      this.$buefy.dialog.confirm({
        title: this.$t("organization.subjectforum.delete.title"),
        message: this.$t("organization.subjectforum.delete.message"),
        confirmText: this.$t("core.utils.delete"),
        cancelText: this.$t("core.utils.cancel"),
        type: "is-danger",
        hasIcon: true,
        onConfirm: () => {
          this.$store
            .dispatch("organization/deleteForumSubject", {
              idSubject: this.subject.id,
              idCategory: this.idCategory
            })
            .then(() => {
              this.$buefy.toast.open({
                message: this.$t("organization.subjectforum.delete.success"),
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
.OrganizationForumSubject {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0.5rem 0.5rem 0.5rem 3rem;
  border-bottom: 1px solid $grey-lightest;
  &-buttons {
    &-edit {
      margin-right: 1rem;
    }
  }
}
</style>
