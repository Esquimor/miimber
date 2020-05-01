<template>
  <TemplateDefault>
    <div class="TemplateInfo">
      <div class="TemplateInfo-wrapper" :style="styleWrapper">
        <slot />
      </div>
    </div>
  </TemplateDefault>
</template>

<script>
"use strict";

import { CONTENT_SIZE } from "@/utils/consts";

import TemplateDefault from "@core/template/TemplateDefault";

export default {
  name: "TemplateInfo",
  components: {
    TemplateDefault
  },
  props: {
    size: {
      type: String,
      default: CONTENT_SIZE.NORMAL
    }
  },
  computed: {
    styleWrapper() {
      return {
        maxWidth: this.size
      };
    }
  }
};
</script>

<style lang="scss">
.TemplateInfo {
  display: flex;
  justify-content: center;
  align-items: center;
  &-wrapper {
    padding: 1rem;
    width: 100%;
    margin: 2rem auto;
    background-color: $white;
    border: 1px solid $grey-lightest;
    box-shadow: 0 4px 6px 0 $grey;
    border-radius: 5px;
  }
}
</style>
