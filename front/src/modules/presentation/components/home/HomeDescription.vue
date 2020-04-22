<template>
  <section class="PresentationHomeDescription">
    <div class="PresentationHomeDescription-wrapper columns">
      <div class="column">
        <h2 class="title is-3">Gestion simplifié</h2>
        <p class="is-size-5">
          Connaissez directement les différentes séssions de votre club avec le
          nombre de places disponibles.
        </p>
      </div>
      <div class="column">
        <h2 class="title is-3">Communauté</h2>
        <p class="is-size-5">Renforcez le sens de communauté de votre club.</p>
      </div>
    </div>
  </section>
</template>

<script>
"use strict";

export default {
  name: "PresentationHomeDescription"
};
</script>

<style lang="scss">
.PresentationHomeDescription {
  background-color: $primary;
  color: $white;
  &-wrapper {
    width: 100%;
    max-width: 960px;
    margin: 0 auto !important;
    padding: 4rem 0;
  }
  h2 {
    color: $white;
  }
}
</style>
