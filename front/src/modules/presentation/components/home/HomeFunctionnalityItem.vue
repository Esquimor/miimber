<template>
  <article class="PresentationHomeFunctionnalityItem">
    <h3 class="PresentationHomeFunctionnalityItem-title title is-4">
      {{ title }}
    </h3>
    <p class="PresentationHomeFunctionnalityItem-message is-size-5">
      {{ message }}
    </p>
  </article>
</template>

<script>
"use strict";

export default {
  name: "PresentationHomeFunctionnalityItem",
  props: {
    title: {
      type: String,
      default: ""
    },
    message: {
      type: String,
      default: ""
    }
  }
};
</script>

<style lang="scss">
.PresentationHomeFunctionnalityItem {
  margin: 0.5rem;
  box-shadow: 0 4px 6px 0 $grey;
  border-radius: 5px;
}
</style>
