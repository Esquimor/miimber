<template>
  <div class="OrganizationTemplateList">
    <header class="OrganizationTemplateList-header">
      <h2 class="title is-4">{{ title }}</h2>
      <BButton
        id="OrganizationTemplateList-add"
        type="is-primary"
        icon-left="plus"
        @click="$emit('add')"
        >{{ $t(addLabel) }}</BButton
      >
    </header>
    <main class="OrganizationTemplateList-main" ref="template">
      <slot />
    </main>
  </div>
</template>

<script>
"use strict";

export default {
  name: "OrganizationTemplateList",
  props: {
    title: {
      type: String,
      default: ""
    },
    loading: {
      type: Boolean,
      default: true
    },
    addLabel: {
      type: String,
      default: "core.utils.add"
    }
  },
  data() {
    return {
      loadingComponent: this.loading
    };
  },
  methods: {
    startLoading() {
      this.loadingComponent = this.$buefy.loading.open({
        container: this.$refs.template.$el
      });
    },
    endLoading() {
      if (this.loadingComponent) {
        this.loadingComponent.close();
        this.loadingComponent = null;
      }
    }
  },
  watch: {
    loading: {
      immediate: true,
      handler(newValue) {
        if (newValue) {
          this.$nextTick(() => {
            this.startLoading();
          });
        } else {
          this.endLoading();
        }
      }
    }
  }
};
</script>

<style lang="scss">
.OrganizationTemplateList {
  &-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
}
</style>
