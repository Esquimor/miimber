export default [
  {
    path: "/session",
    name: "dashboard-sessions",
    component: () => import("@dashboard/views/session/Sessions")
  },
  {
    path: "/session/:id",
    name: "dashboard-session",
    component: () => import("@dashboard/views/session/Session")
  },
  {
    path: "/session/:id/emerge",
    name: "dashboard-session-emerge",
    component: () => import("@dashboard/views/session/SessionEmerge")
  },
  {
    path: "/organization",
    name: "dashboard-organizations",
    component: () => import("@dashboard/views/organization/Organizations")
  },
  {
    path: "/organization/:id",
    component: () => import("@dashboard/views/organization/Organization"),
    children: [
      {
        path: "sessions",
        name: "dashboard-organization-sessions",
        component: () =>
          import("@dashboard/views/organization/OrganizationSessions")
      },
      {
        path: "forum",
        name: "dashboard-organization-forum",
        component: () =>
          import("@dashboard/views/organization/forum/OrganizationForum")
      },
      {
        path: "forum/:idSubject",
        name: "dashboard-organization-forum-subject",
        component: () =>
          import("@dashboard/views/organization/forum/OrganizationForumSubject")
      },
      {
        path: "forum/:idSubject/:idTalk",
        name: "dashboard-organization-forum-talk",
        component: () =>
          import("@dashboard/views/organization/forum/OrganizationForumTalk")
      },
      {
        path: "members",
        name: "dashboard-organization-members",
        component: () =>
          import("@dashboard/views/organization/OrganizationMembers")
      },
      {
        path: "archived",
        name: "dashboard-organization-archived",
        component: () =>
          import("@dashboard/views/organization/OrganizationArchived")
      },
      {
        path: "suspended",
        name: "dashboard-organization-suspended",
        component: () =>
          import("@dashboard/views/organization/OrganizationSuspended")
      }
    ]
  }
];
