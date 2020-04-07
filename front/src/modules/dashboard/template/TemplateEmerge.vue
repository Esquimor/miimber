<template>
  <TemplateDefault>
    <div class="TemplateEmerge">
      <main class="TemplateEmerge-main" ref="template">
        <template v-if="!loading">
          <slot />
        </template>
      </main>
    </div>
  </TemplateDefault>
</template>

<script>
"use strict";

import TemplateDefault from "@core/template/TemplateDefault";

export default {
  name: "TemplateEmerge",
  components: {
    TemplateDefault
  },
  props: {
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
.TemplateEmerge {
  display: flex;
  flex-direction: column;
  background-color: $white-bis;
  min-height: 100vh;
  padding: 1.5rem 0.5rem;
  &-main {
    padding: 1rem 2rem;
    max-width: 500px;
    width: 100%;
    margin: 0 auto;
    background-color: $white;
    border: 1px solid $grey-lightest;
    min-height: 80vh;
    border-radius: 5px;
  }
}
</style>
