export default [
  {
    path: "/organization-manage/:id",
    component: () => import("@organization/views/Organization"),
    children: [
      {
        path: "members",
        name: "organization-manage-members",
        component: () => import("@organization/views/Members")
      },
      {
        path: "settings",
        name: "organization-manage-settings",
        component: () => import("@organization/views/Settings")
      },
      {
        path: "sessions",
        name: "organization-manage-sessions",
        component: () => import("@organization/views/Sessions/Sessions")
      },
      {
        path: "sessions/categories",
        name: "organization-manage-sessions-types",
        component: () => import("@organization/views/Sessions/TypeSessions")
      },
      {
        path: "forum",
        name: "organization-manage-forum",
        component: () => import("@organization/views/Forum")
      }
    ]
  }
];
