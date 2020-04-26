<template>
  <section class="PresentationHomePrice">
    <div class="PresentationHomePrice-wrapper">
      <img src="~@/assets/add.png" />
      <div class="PresentationHomePrice-info">
        <h2 class="title is-3">
          5 {{ $t("presentation.home.price.licence") }}
        </h2>
        <p class="is-size-4">{{ $t("presentation.home.price.licenceNext") }}</p>
      </div>
    </div>
  </section>
</template>

<script>
"use strict";

export default {
  name: "PresentationHomePrice"
};
</script>

<style lang="scss">
.PresentationHomePrice {
  background-color: $white-bis;
  &-wrapper {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    max-width: 960px;
    margin: 0 auto !important;
    padding: 4rem 0;
  }
  &-info {
    margin-left: 2rem;
  }
}
</style>
