<template>
  <TemplateDefault></TemplateDefault>
</template>

<script>
"use strict";

import { mapGetters } from "vuex";

import TemplateDefault from "@core/template/TemplateDefault";

export default {
  name: "DashboardSessionEmerge",
  components: {
    TemplateDefault
  },
  data() {
    return {
      loadingComponent: null,
      loading: true
    };
  },
  computed: {
    ...mapGetters({
      users: "dashboard/sessionUsers"
    })
  },
  methods: {
    setUser(user) {
      if (user.attendeeId === 0 || user.attendeeId === null)
        this.$store.dispatch("dashboard/setUserPresentSession", user.id);
      else
        this.$store.dispatch(
          "dashboard/removeUserPresentSession",
          user.attendeeId
        );
    },

    startLoading() {
      this.loading = true;
      this.loadingComponent = this.$buefy.loading.open({});
    },
    endLoading() {
      if (this.loadingComponent) {
        this.loadingComponent.close();
        this.loadingComponent = null;
        this.loading = false;
      }
    }
  },
  mounted() {
    this.loading = true;
    this.$nextTick(() => {
      this.startLoading();
      this.$store
        .dispatch("dashboard/setSessionUser", this.$route.params.id)
        .then(() => {
          this.endLoading();
        });
    });
  }
};
</script>

<style lang="scss">
.DashboardSessionEmerge {
  &-column {
    &-checkbox {
      display: flex;
      justify-content: center;
    }
  }
}
</style>
