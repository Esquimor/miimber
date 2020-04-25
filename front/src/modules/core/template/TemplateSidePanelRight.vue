<template>
  <div id="TemplateSidePanelRight" class="TemplateSidePanelRight">
    <div class="TemplateSidePanelRight-background" @click="closeSideBar" />
    <main
      id="TemplateSidePanelRight-main"
      class="TemplateSidePanelRight-content"
      :style="contentStyle"
    >
      <slot name="header">
        <header class="TemplateSidePanelRight-content-header">
          <h2 class="title is-5">{{ title }}</h2>
        </header>
      </slot>
      <slot name="main">
        <main class="TemplateSidePanelRight-content-main">
          <slot />
        </main>
      </slot>
      <slot name="footer">
        <footer class="TemplateSidePanelRight-content-footer">
          <button
            id="TemplateSidePanelRight-cancel"
            class="button is-outlined is-danger"
            @click="closeSideBar"
            :disabled="loading"
          >
            {{ $t("core.utils.cancel") }}
          </button>
          <button
            id="TemplateSidePanelRight-confirm"
            class="button is-primary"
            :class="{ 'is-loading': loading }"
            :disabled="disabled"
            @click="confirm"
          >
            {{ $t("core.utils.confirm") }}
          </button>
        </footer>
      </slot>
    </main>
  </div>
</template>

<script>
"use strict";

import { PANEL_SIZE } from "@/utils/consts";

export default {
  name: "TemplateSidePanelRight",
  props: {
    title: {
      type: String,
      default: ""
    },
    size: {
      type: String,
      default: PANEL_SIZE.MEDIUM
    },
    loading: {
      type: Boolean,
      default: false
    },
    disabled: {
      type: Boolean,
      default: false
    }
  },
  computed: {
    contentStyle() {
      return {
        maxWidth: this.size
      };
    }
  },
  methods: {
    closeSideBar() {
      this.$store.dispatch("core/closeSideBar");
    },
    confirm() {
      if (this.loading) return;
      if (this.disabled) return;
      this.$emit("confirm");
    }
  }
};
</script>

<style lang="scss">
.TemplateSidePanelRight {
  position: fixed;
  display: flex;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  z-index: 1500;
  &-background {
    cursor: pointer;
    flex-grow: 1;
    background-color: $grey;
    opacity: 0.5;
  }
  &-content {
    display: flex;
    flex-direction: column;
    width: 100%;
    height: 100%;
    background-color: $white;
    &-header {
      background-color: $grey-lightest;
      padding: 1rem;
    }
    &-main {
      flex-grow: 1;
      padding: 1rem;
      overflow-y: auto;
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
}
</style>
