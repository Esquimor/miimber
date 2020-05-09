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
    <draggable class="OrganizationForumCategory-subjects" v-model="myList">
      <OrganizationForumSubject
        v-for="subject in myList"
        :key="subject.id"
        :subject="subject"
        :idCategory="category.id"
      />
    </draggable>
    <div class="OrganizationForumCategory-addSubject">
      <BButton icon-left="plus" type="is-primary" @click.native="add">{{
        $t("organization.subjectforum.label.add")
      }}</BButton>
    </div>
  </div>
</template>

<script>
"use strict";

import draggable from "vuedraggable";

import OrganizationForumSubject from "@organization/components/forum/OrganizationForumSubject";

import OrganizationForumSubjectAdd from "@organization/components/forum/OrganizationForumSubjectAdd";

export default {
  name: "OrganizationForumCategory",
  components: {
    draggable,
    OrganizationForumSubject
  },
  props: {
    category: {
      type: Object,
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
  computed: {
    myList: {
      get() {
        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        return this.category.subjects.sort((a, b) => a.position - b.position);
      },
      set(value) {
        let loading = this.$buefy.loading.open({});
        this.$store
          .dispatch("organization/setPositionForumSubject", {
            subjects: value,
            idCategory: this.category.id
          })
          .then(() => {
            loading.close();
            loading = null;
          })
          .catch(() => {
            loading.close();
            loading = null;
          });
      }
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
          this.$buefy.toast.open({
            message: this.$t("organization.forum.edit.success"),
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
        title: this.$t("organization.forum.delete.title"),
        message: this.$t("organization.forum.delete.message"),
        confirmText: this.$t("core.utils.delete"),
        cancelText: this.$t("core.utils.cancel"),
        type: "is-danger",
        hasIcon: true,
        onConfirm: () => {
          this.$store
            .dispatch("organization/deleteForumCategory", this.category.id)
            .then(() => {
              this.$buefy.toast.open({
                message: this.$t("organization.forum.delete.success"),
                type: "is-primary"
              });
            });
        }
      });
    },
    add() {
      this.$store.dispatch("core/openSideBar", {
        component: OrganizationForumSubjectAdd,
        props: { idCat: this.category.id }
      });
    }
  }
};
</script>

<style lang="scss">
.OrganizationForumCategory {
  border: 1px solid $grey-lightest;
  border-radius: 5px;
  &-category {
    display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: $white-ter;
    padding: 0.5rem;
    &-buttons {
      &-edit {
        margin-right: 1rem;
      }
    }
  }
  &-addSubject {
    display: flex;
    justify-content: flex-end;
    padding: 1rem 0.5rem;
  }
}
</style>
