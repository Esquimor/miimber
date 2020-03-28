<template>
  <div class="TemplateDashboard">
    <header class="TemplateDashboard-header" :class="{'has-not-Nav': !hasNav}">
      <h1 class="title is-5">{{ title }}</h1>
      <slot name="header" />
    </header>
    <nav v-if="hasNav" class="TemplateDashboard-nav">
      <slot name="nav" />
    </nav>
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
    },
    hasNav: {
      type: Boolean,
      default: false
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
    background-color: $white-ter;
    &.has-not-Nav {
      padding-bottom: 4rem;
      border-bottom: 1px solid $grey-lighter;
    }
  }
  &-nav {
    height: 2rem;
    padding: 0 2rem;
    background-color: $white-ter;
    &-link {
      border-top: 3px solid $white-ter;
      padding: 0.7rem;
      background-color: $white;
      height: 100%;
      color: $black;
      font-weight: bold;
      margin: 0 0.3rem;
      &.router-link-active {
        border-top-color: $primary;
      }
    }
  }
  &-main {
    padding: 1rem;
  }
}
</style>