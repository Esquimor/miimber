<template>
  <TemplateError :error="404" />
</template>

<script>
"use strict";

import TemplateError from "@error/template/TemplateError";

export default {
  name: "Error404",
  components: {
    TemplateError
  }
};
</script>

<style></style>
