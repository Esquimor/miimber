<template>
  <div id="TemplateModal" class="TemplateModal" :style="modalStyle">
    <slot name="header">
      <header class="TemplateModal-header">
        <h2 class="title is-6">{{ title }}</h2>
      </header>
    </slot>
    <slot name="main">
      <main class="TemplateModal-main" :class="{ 'has-padding': hasPadding }">
        <slot />
      </main>
    </slot>
    <slot name="footer">
      <footer class="TemplateModal-footer">
        <button
          id="TemplateModal-cancel"
          class="button is-outlined is-danger"
          @click="$emit('cancel')"
          :disabled="loading"
        >
          {{ $t("core.utils.cancel") }}
        </button>
        <button
          id="TemplateModal-confirm"
          class="button is-primary"
          :class="{ 'is-loading': loading }"
          @click="$emit('confirm')"
        >
          {{ $t("core.utils.confirm") }}
        </button>
      </footer>
    </slot>
  </div>
</template>

<script>
"use strict";

import { MODAL_SIZE } from "@/utils/consts";

export default {
  name: "TempateModal",
  props: {
    title: {
      type: String,
      default: ""
    },
    size: {
      type: [String, Object],
      default: MODAL_SIZE.MEDIUM
    },
    customSize: {
      type: Boolean,
      default: false
    },
    loading: {
      type: Boolean,
      default: false
    },
    hasPadding: {
      type: Boolean,
      default: false
    }
  },
  computed: {
    modalStyle() {
      return Object.assign({}, this.sizeModal);
    },
    sizeModal() {
      if (this.customSize) {
        return this.size;
      }
      switch (this.size) {
        case MODAL_SIZE.SMALL:
          return {
            height: "350px",
            width: "350px"
          };
        case MODAL_SIZE.BIG:
          return {
            height: "80vh",
            width: "200px"
          };
        default:
          return {
            height: "60vh",
            width: "600px"
          };
      }
    }
  }
};
</script>

<style lang="scss">
.TemplateModal {
  display: flex;
  flex-direction: column;
  background-color: $white;
  z-index: 100;
  margin: 0 auto;
  border-radius: 10px;
  &-header {
    border-top-left-radius: 10px;
    border-top-right-radius: 10px;
    background-color: $grey-lightest;
    padding: 0.5rem;
  }
  &-main {
    overflow: auto;
    flex-grow: 1;
    &.has-padding {
      padding: 0.75rem;
    }
  }
  &-footer {
    display: flex;
    justify-content: flex-end;
    background-color: $grey-lightest;
    padding: 0.5rem;
    border-bottom-left-radius: 10px;
    border-bottom-right-radius: 10px;
    > button {
      margin-left: 0.5rem;
    }
  }
}
</style>
