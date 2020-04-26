<template>
  <section class="PresentationHomeFunctionnality">
    <div class="PresentationHomeFunctionnality-wrapper">
      <h2 class="title is-3">
        {{ $t("presentation.home.functionnality.title") }} :
      </h2>
      <div class="columns">
        <HomeFunctionnalityItem
          :title="$t('presentation.home.functionnality.createSession.title')"
          :message="
            $t('presentation.home.functionnality.createSession.description')
          "
          class="column"
        />
        <HomeFunctionnalityItem
          :title="$t('presentation.home.functionnality.waitingList.title')"
          :message="
            $t('presentation.home.functionnality.waitingList.description')
          "
          class="column"
        />
      </div>
      <h2 class="PresentationHomeFunctionnality-next title is-3">
        {{ $t("presentation.home.functionnality.next") }} :
      </h2>
      <div class="columns">
        <HomeFunctionnalityItem
          :title="$t('presentation.home.functionnality.commentary.title')"
          :message="
            $t('presentation.home.functionnality.commentary.description')
          "
          class="column"
        />
        <HomeFunctionnalityItem
          :title="$t('presentation.home.functionnality.privateSession.title')"
          :message="
            $t('presentation.home.functionnality.privateSession.description')
          "
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
