<template>
  <TemplateDefault>
    <slot>
      <div class="TemplateError">
        <h1 class="TemplateError-title title is-1">
          {{ $t("error.title", { error }) }}
        </h1>
        <span class="TemplateError-message is-size-4">
          {{ $t("error.message") }}
        </span>
      </div>
    </slot>
  </TemplateDefault>
</template>

<script>
"use strict";

import TemplateDefault from "@core/template/TemplateDefault";

export default {
  name: "TemplateError",
  components: {
    TemplateDefault
  },
  props: {
    error: {
      type: [Number, String],
      default: ""
    }
  }
};
</script>

<style lang="scss">
.TemplateError {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  width: 100%;
  height: 100vh;
}
</style>
