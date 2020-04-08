<template>
  <TemplateError :error="409" />
</template>

<script>
"use strict";

import TemplateError from "@error/template/TemplateError";

export default {
  name: "Error409",
  components: {
    TemplateError
  }
};
</script>

<style></style>
