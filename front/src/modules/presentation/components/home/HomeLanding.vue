<template>
  <section class="PresentationHomeLanding">
    <div class="PresentationHomeLanding-wrapper">
      <h1 class="PresentationHomeLanding-title title is-2">Me'ember</h1>
      <span class="PresentationHomeLanding-subtitle is-size-5">Connecte les clubs et les adhérents.</span>
      <div v-if="!isConnected" class="PresentationHomeLanding-cta">
        <router-link
          :to="{name: 'register'}"
          class="button is-primary is-outlined is-medium"
        >Démarrer</router-link>
        <router-link :to="{name: 'login'}" class="button is-primary is-medium">Se connecter</router-link>
      </div>
    </div>
  </section>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

export default {
  name: "PresentationHomeLanding",

  computed: {
    ...mapGetters({
      isConnected: "core/isConnected"
    })
  }
};
</script>

<style lang="scss">
.PresentationHomeLanding {
  display: flex;
  flex-direction: column;
  justify-content: center;
  background-image: url("~@/assets/HomeLanding.png");
  background-position: 85% 50%;
  background-repeat: no-repeat;
  background-size: contain;
  width: 100%;
  height: 70vh;
  @media (max-width: $tablet) {
    background: none;
    height: 50vh;
  }
  &-wrapper {
    width: 100%;
    max-width: 960px;
    margin: 0 auto;
    padding: 0.75rem;
    @media (max-width: $tablet) {
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
    }
  }
  &-cta {
    display: flex;
    justify-content: space-between;
    margin-top: 2rem;
    width: 300px;
  }
}
</style>