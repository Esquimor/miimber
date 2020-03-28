<template>
  <div class="TemplateDashboard">
    <header class="TemplateDashboard-header">
      <h1 class="title is-5">{{ title }}</h1>
      <slot name="header" />
    </header>
    <main class="TemplateDashboard-main" ref="template">
      <template v-if="!loading">
        <slot />
      </template>
    </main>
  </div>
</template>

<script>
"use str";

export default {
  name: "TemplateDashboard",
  props: {
    title: {
      type: String,
      default: ""
    },
    loading: {
      type: Boolean,
      default: true
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
.TemplateDashboard {
  display: flex;
  flex-direction: column;
  width: 100%;
  &-header {
    display: flex;
    justify-content: space-between;
    padding: 2rem;
    border-bottom: 1px solid $grey-lighter;
    background-color: $white-bis;
  }
  &-main {
    padding: 1rem;
  }
}
</style>