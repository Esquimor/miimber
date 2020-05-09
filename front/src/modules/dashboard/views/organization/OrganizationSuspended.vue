<template>
  <TemplateOrganization :loading="false">
    <div class="DashboardOrganizationSuspended">
      <span class="text is-size-5">{{
        $t("dashboard.organization.suspended.title")
      }}</span>
    </div>
  </TemplateOrganization>
</template>

<script>
"use strict";

import TemplateOrganization from "@dashboard/template/TemplateOrganization";

export default {
  name: "DashboardOrganizationSuspended",
  components: {
    TemplateOrganization
  }
};
</script>

<style lang="scss">
.DashboardOrganizationSuspended {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 50%;
}
</style>
