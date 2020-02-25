import Dashboard from "@dashboard/views/Dashboard";
import DashboardHome from "@dashboard/views/DashboardHome";
import DashboardSession from "@dashboard/views/DashboardSession";

export default [
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
  }
];
