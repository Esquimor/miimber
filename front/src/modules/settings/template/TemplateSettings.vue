<template>
  <div class="TemplateSettings">
    <h1 class="is-size-3 TemplateSettings-title">{{ title }}</h1>
    <slot class="TemplateSettings-main" />
  </div>
</template>

<script>
"use strict";

export default {
  name: "TemplateSettings",
  props: {
    title: {
      type: String,
      required: true
    }
  }
};
</script>

<style lang="scss">
.TemplateSettings {
  padding: 0 2rem 0 2rem;
  min-height: 100vh;
  &-title {
    width: 100%;
    padding-bottom: 0.3rem;
    margin-bottom: 0.5rem;
    border-bottom: 1px solid $grey-lighter;
  }
  &-main {
    min-height: 100vh;
  }
}
</style>
