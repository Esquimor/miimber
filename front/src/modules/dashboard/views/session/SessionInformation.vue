<template>
  <div>
    <div class="columns">
      <div class="column">
        <BField :label="$t('dashboard.session.label.date')">
          <span>{{ session.start | formatDate }}</span>
        </BField>
      </div>
      <div class="column">
        <BField :label="$t('dashboard.session.label.typeSession')">
          <span>{{ session.typeSession.name }}</span>
        </BField>
      </div>
    </div>
    <div class="columns">
      <div class="column">
        <BField :label="$t('dashboard.session.label.start')">
          <span>{{ session.start | formatHour }}</span>
        </BField>
      </div>
      <div class="column">
        <BField :label="$t('dashboard.session.label.end')">
          <span>{{ session.end | formatHour }}</span>
        </BField>
      </div>
    </div>
    <div class="columns">
      <div class="column">{{ session.description }}</div>
    </div>
  </div>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

export default {
  name: "DashboardSessionInformation",
  computed: {
    ...mapGetters({
      session: "dashboard/session"
    })
  }
};
</script>

<style lang="scss"></style>
