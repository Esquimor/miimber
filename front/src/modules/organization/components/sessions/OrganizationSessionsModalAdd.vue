<template>
  <TemplateModal
    :title="$t('organization.sessions.add.title')"
    :loading="loading"
    hasPadding
    @cancel="$emit('close')"
    @confirm="confirm"
  >
    <div class="columns">
      <div class="column">
        <BField label="Select timepicker">
          <div class="OrganizationSessionModalAdd-date">
            <BDatepicker
              v-model="session.startDate"
              icon="calendar-today"
              trap-focus
            ></BDatepicker>
            <BTimepicker v-model="session.startHour" icon="clock"></BTimepicker>
          </div>
        </BField>
      </div>
      <div class="column">{{ startHour }}</div>
    </div>
  </TemplateModal>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

import TemplateModal from "@core/template/TemplateModal";

export default {
  name: "OrganizationSessionsModalAdd",
  components: {
    TemplateModal
  },
  data() {
    return {
      loading: false,
      session: {
        startDate: null,
        endDate: null,
        startHour: null,
        endHour: null,
        typeSession: null
      }
    };
  },
  computed: {
    ...mapGetters({
      typeSessions: "organization/typeSessions"
    })
  },
  methods: {
    confirm() {}
  }
};
</script>

<style lang="scss">
.OrganizationSessionModalAdd {
  &-date {
    display: flex;
  }
}
</style>
