import Vue from "vue";
import VueRouter from "vue-router";
import Home from "@/views/Home";
import Login from "@/views/Login";
import Register from "@/views/Register";
import Dashboard from "@/views/dashboard/Dashboard";
import DashboardHome from "@/views/dashboard/DashboardHome";
import DashboardSession from "@/views/dashboard/DashboardSession";
import Error404 from "@/views/Error404";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: Home
  },
  {
    path: "/login",
    name: "login",
    component: Login
  },
  {
    path: "/register",
    name: "register",
    component: Register
  },
  {
    path: "/dashboard",
    name: "dashboard",
    component: Dashboard,
    children: [
      {
        path: "",
        name: "dashboard-home",
        component: DashboardHome
      },
      {
        path: "session",
        name: "dashboard-session",
        component: DashboardSession
      }
    ]
  },
  {
    path: "*",
    name: "error_404",
    component: Error404
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
