<template>
  <section class="PresentationHomeFunctionnality">
    <div class="PresentationHomeFunctionnality-wrapper">
      <h2 class="title is-3">Fonctionnalités :</h2>
      <div class="columns">
        <HomeFunctionnalityItem
          title="Créer des sessions"
          message="Créer des sessions pour votre club."
          class="column"
        />
        <HomeFunctionnalityItem
          title="Liste d'attente"
          message="Limiter le nombre de participants à une session avec une liste d'attente."
          class="column"
        />
      </div>
      <h2 class="PresentationHomeFunctionnality-next title is-3">
        Prochainement :
      </h2>
      <div class="columns">
        <HomeFunctionnalityItem
          title="Commentaires"
          message="Ajouter des commentaires aux sessions."
          class="column"
        />
        <HomeFunctionnalityItem
          title="Session privée"
          message="Créer des sessions accèssibles qu'à vos membres."
          class="column"
        />
      </div>
    </div>
  </section>
</template>

<script>
"use strict";

import HomeFunctionnalityItem from "@presentation/components/home/HomeFunctionnalityItem";

export default {
  name: "PresentationHomeFunctionnality",
  components: {
    HomeFunctionnalityItem
  }
};
</script>

<style lang="scss">
.PresentationHomeFunctionnality {
  background-color: $white-bis;
  &-wrapper {
    width: 100%;
    max-width: 960px;
    margin: 0 auto !important;
    padding: 4rem 0.75rem;
  }
  &-next {
    margin-top: 2rem;
  }
}
</style>
