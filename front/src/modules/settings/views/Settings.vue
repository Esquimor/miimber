<template>
  <TemplateDefault>
    <main class="Settings">
      <router-view></router-view>
    </main>
  </TemplateDefault>
</template>

<script>
"use strict";

import TemplateDefault from "@core/template/TemplateDefault";

export default {
  name: "Settings",
  components: {
    TemplateDefault
  }
};
</script>

<style lang="scss">
</style>