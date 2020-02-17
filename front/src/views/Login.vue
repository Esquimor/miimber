<template>
    <TemplateSign>
        <template v-slot:rigth>
            rigth
        </template>
        <template v-slot:left>
            <div class="Login-img"/>
        </template>
    </TemplateSign>
</template>

<script>
"use strict";

import TemplateSign from "@/template/TemplateSign"

export default {
  name: "Login",
  components: {
      TemplateSign
  }
};
</script>

<style lang="scss">
.Login {
  &-form {
    display: flex;
    flex-direction: column;
  }
  &-img {
    width: 100%;
    height: 100%;
    background: linear-gradient(#1565c0, #42a5f5);
  }
}
</style>
