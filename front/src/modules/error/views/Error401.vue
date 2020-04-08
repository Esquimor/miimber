<template>
  <TemplateError :error="401" />
</template>

<script>
"use strict";

import TemplateError from "@error/template/TemplateError";

export default {
  name: "Error401",
  components: {
    TemplateError
  }
};
</script>

<style></style>
