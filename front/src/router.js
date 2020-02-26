import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

import routerCore from "@core/router.js";
import routerDashboard from "@dashboard/router.js";
import routerError from "@error/router.js";
import routerHome from "@home/router.js";
import routerSign from "@sign/router.js";
import routerSettings from "@settings/router.js";

const routes = [];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes: routes.concat(
    routerCore,
    routerDashboard,
    routerHome,
    routerSign,
    routerSettings,
    routerError
  )
});

export default router;
