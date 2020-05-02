<template>
  <TemplateInfo :size="CONTENT_SIZE.SMALLER">
    <slot name="header">
      <header v-if="title !== ''" class="TemplateForm-header">
        <h1 class="title is-4">{{ title }}</h1>
      </header>
    </slot>
    <main>
      <slot />
    </main>
    <slot name="footer">
      <footer class="TemplateForm-footer">
        <button
          @click="$emit('click')"
          class="button is-primary"
          :disabled="disabled"
          :class="{ 'is-loading': loading }"
        >
          {{ button }}
        </button>
      </footer>
    </slot>
  </TemplateInfo>
</template>

<script>
"use strict";

import { CONTENT_SIZE } from "@/utils/consts";

import TemplateInfo from "@core/template/TemplateInfo";

export default {
  name: "TemplateForm",
  components: {
    TemplateInfo
  },
  props: {
    title: {
      type: String,
      default: ""
    },
    button: {
      type: String,
      default: ""
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
  data() {
    return {
      CONTENT_SIZE
    };
  }
};
</script>

<style lang="scss">
.TemplateForm {
  &-header {
    margin-bottom: 1rem;
  }
  &-footer {
    display: flex;
    justify-content: flex-end;
    margin-top: 1rem;
  }
}
</style>
