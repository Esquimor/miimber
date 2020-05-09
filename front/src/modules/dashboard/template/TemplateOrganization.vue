<template>
  <div class="TemplateOrganization" ref="template">
    <template v-if="!loading">
      <slot />
    </template>
  </div>
</template>

<script>
"use strict";

import { CONTENT_SIZE } from "@/utils/consts";

export default {
  name: "TemplateWrapper",
  props: {
    loading: {
      type: Boolean,
      default: true
    }
  },
  data() {
    return {
      loadingComponent: null
    };
  },
  computed: {
    styleMain() {
      return {
        maxWidth: CONTENT_SIZE.NORMAL
      };
    }
  },
  methods: {
    startLoading() {
      if (this.$refs.template) {
        this.loadingComponent = this.$buefy.loading.open({
          container: this.$refs.template.$el
        });
      }
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
          this.startLoading();
        } else {
          this.endLoading();
        }
      }
    }
  }
};
</script>

<style lang="scss">
.TemplateOrganization {
  width: 100%;
  padding: 1rem;
  background-color: $white;
  border: 1px solid $grey-lightest;
  min-height: 80vh;
  border-radius: 5px;
}
</style>
