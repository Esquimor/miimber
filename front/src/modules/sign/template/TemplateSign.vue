<template>
  <TemplateDefault>
    <div class="TemplateSign">
      <div class="TemplateSign-rigth">
        <slot name="rigth" />
      </div>
      <div class="TemplateSign-left">
        <slot name="left">
          <img
            src="~@/assets/login_process.png"
            class="TemplateSign-left-img"
          />
        </slot>
      </div>
    </div>
  </TemplateDefault>
</template>

<script>
"use strict";

import TemplateDefault from "@core/template/TemplateDefault";

export default {
  name: "TemplateSign",
  components: {
    TemplateDefault
  }
};
</script>

<style lang="scss">
.TemplateSign {
  display: flex;
  height: 100vh;
  @media (max-width: $tablet) {
    justify-content: center;
  }
  &-rigth {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    position: relative;
    width: 40vw;
    @media (max-width: $tablet) {
      width: 250px;
      justify-content: flex-start;
      margin-top: 1rem;
    }
  }
  &-left {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 60vw;
    height: 100%;
    background: linear-gradient(#1565c0, #42a5f5);
    @media (max-width: $tablet) {
      display: none;
    }
    &-img {
      width: 70%;
    }
  }
}
</style>
