<template>
  <div class="OrganizationGeneral">
    <div class="columns">
      <div class="column">
        <BField :label="$t('organization.information.label.name')">
          <span>{{ organization.name }}</span>
        </BField>
      </div>
      <div class="column"></div>
    </div>
  </div>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

export default {
  name: "OrganizationGeneral",
  computed: {
    ...mapGetters({
      organization: "organization/organization"
    })
  }
};
</script>

<style lang="scss"></style>
