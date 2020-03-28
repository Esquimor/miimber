import Dashboard from "@dashboard/views/Dashboard";
import Home from "@dashboard/views/Home";
import Sessions from "@dashboard/views/session/Sessions";
import Organizations from "@dashboard/views/organization/Organizations";
import Organization from "@dashboard/views/organization/Organization";
import OrganizationSessions from "@dashboard/views/organization/OrganizationSessions";
import OrganizationMembers from "@dashboard/views/organization/OrganizationMembers";

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
        path: "organization",
        name: "dashboard-organizations",
        component: Organizations
      },
      {
        path: "organization/:id",
        component: Organization,
        children: [
          {
            path: "sessions",
            name: "dashboard-organization-sessions",
            component: OrganizationSessions
          },
          {
            path: "members",
            name: "dashboard-organization-members",
            component: OrganizationMembers
          }
        ]
      }
    ]
  }
];
