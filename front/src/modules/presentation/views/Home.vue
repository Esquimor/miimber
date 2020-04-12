<template>
  <TemplateDefault>
    <HomeLanding />
    <HomeDescription />
    <HomePrice />
    <HomeFunctionnality />
  </TemplateDefault>
</template>

<script>
"use strict";

import TemplateDefault from "@core/template/TemplateDefault";

import HomeDescription from "@presentation/components/home/HomeDescription";
import HomeFunctionnality from "@presentation/components/home/HomeFunctionnality";
import HomeLanding from "@presentation/components/home/HomeLanding";
import HomePrice from "@presentation/components/home/HomePrice";

export default {
  name: "PresentationHome",
  components: {
    TemplateDefault,
    HomeDescription,
    HomeFunctionnality,
    HomeLanding,
    HomePrice
  }
};
</script>

<style lang="scss"></style>
