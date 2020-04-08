<template>
  <TemplateError :error="500" />
</template>

<script>
"use strict";

import TemplateError from "@error/template/TemplateError";

export default {
  name: "Error500",
  components: {
    TemplateError
  }
};
</script>

<style></style>
