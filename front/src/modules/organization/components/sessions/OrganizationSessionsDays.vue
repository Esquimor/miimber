<template>
  <div class="OrganizationSessionsDay">
    <div
      v-for="day in DAYS"
      :key="day.item"
      class="OrganizationSessionsDay-item"
      :class="{ 'is-selected': days.some(d => d === day.value) }"
      @click="$emit('click', day.value)"
    >
      {{ $t(`core.daysShort.${day.label}`) }}
    </div>
  </div>
</template>

<script>
"use strict";

import { DAYS } from "@/utils/consts";

export default {
  name: "OrganizationSessionsDay",
  props: {
    days: {
      type: Array,
      default: () => []
    }
  },
  data() {
    return {
      DAYS: DAYS
    };
  }
};
</script>

<style lang="scss">
.OrganizationSessionsDay {
  display: flex;
  justify-content: space-around;
  &-item {
    cursor: pointer;
    padding: 0.5rem 0.75rem;
    background-color: $grey-lightest;
    border-radius: 5px;
    &:hover {
      background-color: $grey-lighter;
    }
    &.is-selected {
      background-color: $primary;
      color: $white;
    }
  }
}
</style>
