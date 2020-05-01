import Vue from "vue";
import VueRouter from "vue-router";
import store from "./store";

Vue.use(VueRouter);

const routerCore = () => import("@core/router.js");
const routerDashboard = () => import("@dashboard/router.js");
const routerError = () => import("@error/router.js");
const routerPresentation = () => import("@presentation/router.js");
const routerSign = () => import("@sign/router.js");
const routerSettings = () => import("@settings/router.js");
const routerOrganization = () => import("@organization/router.js");

const routes = [];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes: routes.concat(
    routerCore,
    routerDashboard,
    routerPresentation,
    routerSign,
    routerSettings,
    routerOrganization,
    routerError
  ),
});

router.beforeEach((to, from, next) => {
  if (
    to.path.search("/settings") !== -1 ||
    to.path.search("/dashboard") !== -1 ||
    to.path.search("/organization") !== -1 ||
    to.path.search("/session") !== -1 ||
    to.path.search("/organization-manage") !== -1
  ) {
    if (store.state.core.me) {
      next();
    } else if (localStorage.getItem("token")) {
      store
        .dispatch("core/getMe")
        .then(() => {
          next();
        })
        .catch(() => {
          next({ name: "login" });
        });
    } else {
      next({ name: "login" });
    }
  } else {
    next();
  }
});

export default router;
