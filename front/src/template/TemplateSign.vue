<template>
  <div class="TemplateSign">
      <div class="TemplateSign-rigth">
          <div class="TemplateSign-rigth-form">
              <slot name="rigth"/>
          </div>
      </div>
      <div class="TemplateSign-left">
          <slot name="left"/>
      </div>
  </div>
</template>

<script>
"use strict"

export default {
    name: "TemplateSign"
}
</script>

<style lang="scss">
.TemplateSign {
    display: flex;
    height: 100vh;
    &-rigth {
        width: 60vw;
    }
    &-left {
        width: 40vw;
    }
}
</style>