<template>
  <div class="TemplateSign">
    <div class="TemplateSign-rigth">
      <slot name="rigth" />
    </div>
    <div class="TemplateSign-left">
      <slot name="left"><div class="TemplateSign-left-img"/></slot>
    </div>
  </div>
</template>

<script>
"use strict";

export default {
  name: "TemplateSign"
};
</script>

<style lang="scss">
.TemplateSign {
  display: flex;
  height: 100vh;
  &-rigth {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    position: relative;
    width: 40vw;
  }
  &-left {
    width: 60vw;
    &-img {
      width: 100%;
      height: 100%;
      background: linear-gradient(#1565c0, #42a5f5);
    }
  }
}
</style>
