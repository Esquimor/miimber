<template>
  <v-app>
    <v-app-bar app color="primary" dark>
      <div class="d-flex align-center"></div>

      <v-spacer></v-spacer>
    </v-app-bar>

    <v-content>
      <slot />
    </v-content>
  </v-app>
</template>

<script>
"use strict";

export default {
  name: "TemplateDetault"
};
</script>

<style></style>
