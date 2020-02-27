import Dashboard from "@dashboard/views/Dashboard";
import Home from "@dashboard/views/Home";
import Sessions from "@dashboard/views/session/Sessions";
import Organizations from "@dashboard/views/organization/Organizations";

export default [
  {
    path: "/dashboard",
    component: Dashboard,
    children: [
      {
        path: "",
        name: "dashboard-home",
        component: Home
      },
      {
        path: "sessions",
        name: "dashboard-sessions",
        component: Sessions
      },
      {
        path: "organizations",
        name: "dashboard-organizations",
        component: Organizations
      }
    ]
  }
];
