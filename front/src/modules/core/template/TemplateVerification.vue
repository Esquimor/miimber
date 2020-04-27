<template>
  <TemplateInfo :size="CONTENT_SIZE.SMALL">
    <div v-if="STATE_VALIDATION.WAIT === state" class="TemplateVerification">
      <BIcon
        icon="loading"
        size="is-large"
        type="is-info"
        class="TemplateVerification-icon TemplateVerification-icon-loading"
      />
      <span class="TemplateVerification-text">
        <slot name="wait">{{ $t("core.verification.label") }}</slot>
      </span>
    </div>
    <div v-if="STATE_VALIDATION.SUCESS === state" class="TemplateVerification">
      <BIcon
        icon="thumb-up"
        size="is-large"
        type="is-success"
        class="TemplateVerification-icon"
      />
      <span class="TemplateVerification-text">
        <slot />
      </span>
    </div>
    <div v-if="STATE_VALIDATION.ERROR === state" class="TemplateVerification">
      <BIcon
        icon="alert-circle"
        size="is-large"
        type="is-danger"
        class="TemplateVerification-icon"
      />
      <span class="TemplateVerification-text">
        <slot name="error">{{ $t("core.error.message") }}</slot>
      </span>
    </div>
  </TemplateInfo>
</template>

<script>
"use strict";

import { CONTENT_SIZE, STATE_VALIDATION } from "@/utils/consts";

import TemplateInfo from "@core/template/TemplateInfo";

export default {
  name: "TemplateVerification",
  components: {
    TemplateInfo
  },
  props: {
    state: {
      type: String,
      default: STATE_VALIDATION.WAIT
    }
  },
  data() {
    return {
      CONTENT_SIZE: CONTENT_SIZE,
      STATE_VALIDATION: STATE_VALIDATION
    };
  }
};
</script>

<style lang="scss">
.TemplateVerification {
  display: flex;
  flex-direction: column;
  align-items: center;
  &-icon {
    margin: 1rem auto 2rem;
    &-loading {
      animation: spinAround 0.45s linear infinite;
    }
  }
  &-text {
    margin-bottom: 1rem;
  }
}
</style>
