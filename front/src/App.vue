<template>
  <div class="App">
    <router-view></router-view>
  </div>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

export default {
  name: "App",
  methods: {
    showMessage(message) {
      this.$buefy.dialog.alert({
        title: message.title,
        message: message.message,
        type: message.type,
        confirmText: this.$t("core.utils.close"),
        confirm: () => {
          this.$store.dispatch("core/emptyMessage");
        }
      });
    },
    showMessageError(error) {
      this.$buefy.dialog.alert({
        title: error.status
          ? this.$t("core.error.titleStatus", { status: error.status })
          : this.$t("core.error.title"),
        message: this.$t("core.error.message"),
        type: "is-danger",
        confirmText: this.$t("core.utils.close"),
        confirm: () => {
          this.$store.dispatch("core/emptyError");
        }
      });
    }
  },
  computed: {
    ...mapGetters({
      message: "core/message",
      error: "core/error"
    })
  },
  watch: {
    message(newVal) {
      if (newVal && newVal.show) {
        this.showMessage(newVal);
      }
    },
    error(newVal) {
      if (newVal && newVal.show) {
        this.showMessageError(newVal);
      }
    }
  }
};
</script>
