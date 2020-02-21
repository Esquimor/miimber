<template>
  <div class="TemplateSign">
    <div class="TemplateSign-rigth">
      <slot name="rigth" />
    </div>
    <div class="TemplateSign-left">
      <slot name="left" />
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
  }
}
</style>
