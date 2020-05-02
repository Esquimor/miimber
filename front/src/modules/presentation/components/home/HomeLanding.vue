<template>
  <section class="PresentationHomeLanding">
    <div class="PresentationHomeLanding-wrapper">
      <h1 class="PresentationHomeLanding-title title is-2">Miimber</h1>
      <span class="PresentationHomeLanding-subtitle is-size-5">{{
        $t("presentation.home.landing.subtitle")
      }}</span>
      <div v-if="!isConnected" class="PresentationHomeLanding-cta">
        <router-link
          :to="{ name: 'register' }"
          class="PresentationHomeLanding-cta-register button is-primary is-outlined is-medium"
          >{{ $t("core.menu.register") }}</router-link
        >
        <router-link
          :to="{ name: 'login' }"
          class="button is-primary is-medium"
          >{{ $t("core.menu.login") }}</router-link
        >
      </div>
      <div v-else class="PresentationHomeLanding-cta">
        <router-link
          :to="{ name: 'dashboard-sessions' }"
          class="PresentationHomeLanding-cta-register button is-primary is-medium"
          >{{ $t("presentation.home.landing.sessions") }}</router-link
        >
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
    justify-content: flex-start;
    margin-top: 2rem;
    @media (max-width: $tablet) {
      justify-content: space-around;
    }
    @media (max-width: $mobile) {
      flex-direction: column;
    }
    > a {
      margin: 0 0.5rem;
      @media (max-width: $mobile) {
        margin: 0.75rem 0.5rem;
      }
    }
    &-register {
      margin-left: 0px !important;
      @media (max-width: $tablet) {
        margin-left: 0.5rem !important;
      }
    }
  }
}
</style>
