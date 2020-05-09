<template>
  <TemplateOrganization :loading="false">
    <div class="DashboardOrganizationArchived">
      <span class="text is-size-5">{{
        $t("dashboard.organization.archived.title")
      }}</span>
    </div>
  </TemplateOrganization>
</template>

<script>
"use strict";

import TemplateOrganization from "@dashboard/template/TemplateOrganization";

export default {
  name: "DashboardOrganizationArchived",
  components: {
    TemplateOrganization
  }
};
</script>

<style lang="scss">
.DashboardOrganizationArchived {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 50%;
}
</style>
