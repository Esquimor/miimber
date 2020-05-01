export default [
  {
    path: "/session",
    name: "dashboard-sessions",
    component: () => import("@dashboard/views/session/Sessions"),
  },
  {
    path: "/session/:id",
    name: "dashboard-session",
    component: () => import("@dashboard/views/session/Session"),
  },
  {
    path: "/session/:id/emerge",
    name: "dashboard-session-emerge",
    component: () => import("@dashboard/views/session/SessionEmerge"),
  },
  {
    path: "/organization",
    name: "dashboard-organizations",
    component: () => import("@dashboard/views/organization/Organizations"),
  },
  {
    path: "/organization/:id",
    component: () => import("@dashboard/views/organization/Organization"),
    children: [
      {
        path: "sessions",
        name: "dashboard-organization-sessions",
        component: () =>
          import("@dashboard/views/organization/OrganizationSessions"),
      },
      {
        path: "members",
        name: "dashboard-organization-members",
        component: () =>
          import("@dashboard/views/organization/OrganizationMembers"),
      },
    ],
  },
];
